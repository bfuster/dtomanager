/**
 * 
 */
package br.com.dclick.dtomanager.exceptions;

/**
 * Coudn't create enum through string
 * 
 * @author bfuster
 * 
 */
public class IllegalStringIntoEnum extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalStringIntoEnum( Class clazz, Throwable ex ) {

		super( clazz.toString(), ex );
	}

}
