package br.com.dclick.dtomanager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.log4j.Logger;

import br.com.dclick.dtomanager.annotations.Alias;
import br.com.dclick.dtomanager.annotations.Collect;
import br.com.dclick.dtomanager.annotations.Composition;
import br.com.dclick.dtomanager.annotations.EnumToString;
import br.com.dclick.dtomanager.annotations.FlexNumber;
import br.com.dclick.dtomanager.annotations.Ignore;
import br.com.dclick.dtomanager.annotations.StringToEnum;
import br.com.dclick.dtomanager.exceptions.IllegalArgumentRuntimeException;
import br.com.dclick.dtomanager.handlers.CommonHandler;
import br.com.dclick.dtomanager.handlers.CompositionHandler;
import br.com.dclick.dtomanager.handlers.DTOHandler;
import br.com.dclick.dtomanager.handlers.EnumToStringHandler;
import br.com.dclick.dtomanager.handlers.FlexNumberHandler;
import br.com.dclick.dtomanager.handlers.ListingHandler;
import br.com.dclick.dtomanager.handlers.StringToEnumHandler;

/**
 * Data transfer object implementation.
 * 
 * @author bfuster
 * 
 */
public class DataTransferObjectManager {

	private static final Logger logger = Logger.getLogger( DataTransferObjectManager.class );

	private static final Map< Class< ? extends Annotation >, DTOHandler > handlers = new HashMap< Class< ? extends Annotation >, DTOHandler >();

	static {

		handlers.put( Composition.class, new CompositionHandler() );
		handlers.put( EnumToString.class, new EnumToStringHandler() );
		handlers.put( FlexNumber.class, new FlexNumberHandler() );
		handlers.put( Collect.class, new ListingHandler() );
		handlers.put( StringToEnum.class, new StringToEnumHandler() );

	}

	/**
	 * Advanced copy with "only props", not equals @ignore
	 * 
	 * @param <T>
	 * @param to
	 * @param from
	 * @param props
	 * @return
	 */
	public < T > T doCopy( Class< T > to, Object from, String[] props ) {

		logger.debug( String.format( "Copying %1$s to new %2$s", from.getClass(), to ) );

		List< Field > fields = new Mirror().on( to ).reflectAll().fields();

		T newobj;

		/* create new instance */
		try {
			newobj = to.newInstance();
		} catch (Exception ex) {
			throw new IllegalArgumentRuntimeException( "Where is your public no args contructor?", ex );
		}

		/* for each property */
		for ( Field f : fields ) {

			if ( goAhead( f, props ) ) {

				String field;

				/* verify if there is an alias */
				if ( f.isAnnotationPresent( Alias.class ) )
					field = ( (Alias) f.getAnnotation( Alias.class ) ).value();
				else
					field = f.getName();

				/* get the value */
				Object value = new Mirror().on( from ).invoke().getterFor( field );

				if ( value != null ) {

					/* common handler */
					value = new CommonHandler().handle( f, value );

					/* check for annotations and call handlers */
					for ( Annotation a : f.getAnnotations() )
						if ( handlers.containsKey( a.annotationType() ) )
							value = ( (DTOHandler) handlers.get( a.annotationType() ) ).handle( f, value );

					/* set changed value */
					if ( value != null )
						new Mirror().on( newobj ).invoke().setterFor( f ).withValue( value );

				}

			}

		}

		return newobj;

	}

	/**
	 * Check if field can be copied
	 * 
	 * @param f
	 * @param props
	 * @return
	 */
	private boolean goAhead( Field f, String[] props ) {

		/* final with public or private and static. help me. */
		if ( f.getModifiers() >= Modifier.FINAL && f.getModifiers() < Modifier.SYNCHRONIZED )
			return false;

		/* check props */
		if ( props != null && props.length > 0 )
			return ( searchPropertiesArray( props, f.getName() ) );

		/* check ignore */
		return !( f.isAnnotationPresent( Ignore.class ) );
	}

	/**
	 * Search for some at the properties array.
	 * 
	 * @param props
	 * @param value
	 * @return
	 */
	private boolean searchPropertiesArray( String[] props, String value ) {

		for ( String p : props )
			if ( value.equals( p ) )
				return true;

		return false;

	}

	/**
	 * Copy annotated properties from one class to another
	 * 
	 * @param <T>
	 * @param to
	 * @param from
	 * @return
	 */
	public < T > T copy( Class< T > to, Object from ) {

		return doCopy( to, from, null );
	}

	/**
	 * Copies a list
	 * 
	 * @param <T>
	 * @param to
	 * @param from
	 * @return
	 */
	public < T > Collection< T > copyList( Class< T > to, Collection< ? extends Object > from ) {

		return copyList( to, from, null );

	}

	/**
	 * Copies a DTO list with defined properties
	 * 
	 * @param <T>
	 * @param to
	 * @param from
	 * @return
	 */
	public < T > Collection< T > copyList( Class< T > to, Collection< ? extends Object > from, String[] props ) {

		if ( from == null || from.size() == 0 )
			return new ArrayList< T >();

		List< T > list = new ArrayList< T >();

		for ( Object o : from )
			list.add( doCopy( to, o, props ) );

		return list;

	}

}
