package br.com.dclick.dtomanager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.log4j.Logger;

import br.com.dclick.dtomanager.annotations.Alias;
import br.com.dclick.dtomanager.annotations.Composition;
import br.com.dclick.dtomanager.annotations.EnumToString;
import br.com.dclick.dtomanager.annotations.FlexNumber;
import br.com.dclick.dtomanager.annotations.Ignore;
import br.com.dclick.dtomanager.annotations.Listing;
import br.com.dclick.dtomanager.annotations.StringToEnum;
import br.com.dclick.dtomanager.handlers.CommonHandler;
import br.com.dclick.dtomanager.handlers.CompositionHandler;
import br.com.dclick.dtomanager.handlers.DTOHandler;
import br.com.dclick.dtomanager.handlers.EnumToStringHandler;
import br.com.dclick.dtomanager.handlers.FlexNumberHandler;
import br.com.dclick.dtomanager.handlers.ListingHandler;
import br.com.dclick.dtomanager.handlers.StringToEnumHandler;

/**
 * This class will create DTO's to send to flex.
 * 
 * @author fuster
 * 
 */
public class DataTransferObjectManager {

	private static final Logger logger = Logger.getLogger( DataTransferObjectManager.class );

	private static final Map< Class< ? extends Annotation >, DTOHandler > handlers = new HashMap< Class< ? extends Annotation >, DTOHandler >();

	static {

		handlers.put( Composition.class, new CompositionHandler() );
		handlers.put( EnumToString.class, new EnumToStringHandler() );
		handlers.put( FlexNumber.class, new FlexNumberHandler() );
		handlers.put( Listing.class, new ListingHandler() );
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
	@SuppressWarnings( "unchecked" )
	public static < T > T doCopy( Class< T > to, Object from, String[] props ) {

		logger.debug( String.format( "Copying %1$s to new %2$s", from.getClass(), to ) );

		List< Field > fields = new Mirror().on( to ).reflectAll().fields();
		HashMap propsMap = toMap( props );

		T newobj;

		/* create new instance */
		try {
			newobj = to.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException( "Where is your public no args contructor?", ex );
		}

		/* copy field values */
		for ( Field f : fields ) {

			if ( goAhead( f, propsMap ) ) {

				String field;

				/* verify if there is an alias */
				if ( f.isAnnotationPresent( Alias.class ) )
					field = ( (Alias) f.getAnnotation( Alias.class ) ).value();
				else
					field = f.getName();

				Object value = new Mirror().on( from ).invoke().getterFor( field );

				if ( value != null ) {

					/* common handler */
					value = new CommonHandler().handle( f, value );

					/* check for annotations and call handlers */
					for ( Annotation a : f.getAnnotations() )
						if ( handlers.containsKey( a.annotationType() ) )
							value = ( (DTOHandler) handlers.get( a.annotationType() ) ).handle( f, value );

					/* set value */
					if ( value != null )
						new Mirror().on( newobj ).invoke().setterFor( f ).withValue( value );

				}

			}

		}

		return newobj;

	}

	/**
	 * Verify if field can be copied
	 * 
	 * @param f
	 * @param props
	 * @return
	 */
	private static boolean goAhead( Field f, HashMap props ) {

		/* final with public or private and static */
		if ( f.getModifiers() >= Modifier.FINAL && f.getModifiers() < Modifier.SYNCHRONIZED )
			return false;
		
		if ( props != null && !props.isEmpty() )
			return ( props.containsKey( f.getName() ) );

		return !( f.isAnnotationPresent( Ignore.class ) );
	}

	/**
	 * String to map for declarative search (containsKey)
	 * 
	 * @param props
	 * @return
	 */
	private static HashMap toMap( String[] props ) {

		HashMap< String, String > map = new HashMap< String, String >();

		if ( props == null )
			return map;

		for ( String p : props )
			map.put( p, null );

		return map;
	}

	/**
	 * Copy annotated properties from one class to another
	 * 
	 * @param <T>
	 * @param to
	 * @param from
	 * @return
	 */
	public static < T > T copy( Class< T > to, Object from ) {

		return doCopy( to, from, new String[] {} );
	}

	/**
	 * Copies a DTO list
	 * 
	 * @param <T>
	 * @param to
	 * @param from
	 * @return
	 */
	public static < T > List< T > copyList( Class< T > to, List< ? extends Object > from ) {

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
	public static < T > List< T > copyList( Class< T > to, List< ? extends Object > from, String[] props ) {

		if ( from == null || from.size() == 0 )
			return new ArrayList< T >();

		List< T > list = new ArrayList< T >();

		for ( Object o : from )
			list.add( doCopy( to, o, props ) );

		return list;

	}

	/**
	 * Copies a DTO to list without type
	 * 
	 * @param <T>
	 * @param to
	 * @param from
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	public static List copyUncheckedList( Class c, List< ? extends Object > from ) {

		List list = new ArrayList();

		for ( Object o : from )
			list.add( copy( c, o ) );

		return list;

	}
}
