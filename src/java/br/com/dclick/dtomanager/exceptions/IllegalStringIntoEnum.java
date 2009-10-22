/**
 * 
 */
package br.com.dclick.dtomanager.exceptions;

/**
 * @author bfuster
 * 
 */
public class IllegalStringIntoEnum extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalStringIntoEnum( Class clazz, Throwable t ) {

		super( clazz.toString(), t );
	}

}
