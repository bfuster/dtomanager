package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

import br.com.dclick.dtomanager.annotations.EnumToString;

/**
 * Enum to string handler
 * 
 * @author bfuster
 * 
 */
public class EnumToStringHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect
	 * .Field, java.lang.Object)
	 */
	public Object handle( Field f, Object value ) {

		value = ( (Enum) value ).name();

		/* capitalize? */
		if ( f.getAnnotation( EnumToString.class ).capitalize() )
			value = ( (String) value ).substring( 0, 1 ).toUpperCase().concat( ( (String) value ).substring( 1 ).toLowerCase() );

		return value;
	}
}
