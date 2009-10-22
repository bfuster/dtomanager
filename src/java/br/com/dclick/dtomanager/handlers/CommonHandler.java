package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

/**
 * Common stuff for all copies go here. Right now there is just a helper for
 * java/flex serialization issues with double and float.
 * 
 * @author bfuster
 * 
 */
public class CommonHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect
	 * .Field, java.lang.Object)
	 */
	public Object handle( Field f, Object value ) {

		/* verify NaN values */
		if ( value instanceof Double && ( (Double) value ).equals( Double.NaN ) )
			value = null;

		if ( value instanceof Float && ( (Float) value ).equals( Float.NaN ) )
			value = null;

		return value;

	}

}
