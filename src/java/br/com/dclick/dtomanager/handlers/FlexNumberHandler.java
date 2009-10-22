package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

/**
 * Flex number handler.
 * 
 * @author bfuster
 * 
 */
public class FlexNumberHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect
	 * .Field, java.lang.Object)
	 */
	public Object handle( Field f, Object value ) {

		if ( value.equals( 0l ) || value.equals( 0 ) )
			value = null;

		return value;

	}
}
