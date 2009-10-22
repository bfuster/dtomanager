package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

import br.com.dclick.dtomanager.annotations.StringToEnum;
import br.com.dclick.dtomanager.exceptions.IllegalStringIntoEnum;

/**
 * String to enum handler
 * 
 * @author bfuster
 * 
 */
public class StringToEnumHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect
	 * .Field, java.lang.Object)
	 */
	@SuppressWarnings( "unchecked" )
	public Object handle( Field f, Object value ) {

		if ( ( (String) value ).trim().length() <= 0 )
			return null;

		if ( f.isAnnotationPresent( StringToEnum.class ) && value != null ) {

			Class e = f.getType();

			try {
				value = Enum.valueOf( e, ( (String) value ).toUpperCase() );
			} catch (IllegalArgumentException ex) {
				throw new IllegalStringIntoEnum( e, ex );
			}

		}

		return value;
	}

}
