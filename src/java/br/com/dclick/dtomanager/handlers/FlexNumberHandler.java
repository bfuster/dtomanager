package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

import br.com.dclick.dtomanager.annotations.FlexNumber;

/**
 * Flex number handler. 
 * @author bfuster
 *
 */
public class FlexNumberHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * @see br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect.Field, java.lang.Object)
	 */
	public Object handle( Field f, Object value ) {

		if ( f.isAnnotationPresent( FlexNumber.class ) && value != null && ( value.equals( 0l ) || value.equals( 0 ) ) )
			value = null;

		return value;

	}

}
