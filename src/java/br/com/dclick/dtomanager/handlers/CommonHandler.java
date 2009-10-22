package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

public class CommonHandler implements DTOHandler {

	public Object handle( Field f, Object value ) {

		/* verify NaN values */
		if ( value instanceof Double && ( (Double) value ).equals( Double.NaN ) )
			value = null;

		if ( value instanceof Float && ( (Float) value ).equals( Float.NaN ) )
			value = null;

		return value;

	}

}
