package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

/**
 * Interface for value handlers.
 * 
 * @author bfuster
 * 
 */
public interface DTOHandler {

	/**
	 * Do anything with your object values.
	 * 
	 * @param field
	 *            {@link Field}
	 * @param value
	 *            {@link Object}
	 * @return {@link Object}
	 */
	public abstract Object handle( Field field, Object value );

}
