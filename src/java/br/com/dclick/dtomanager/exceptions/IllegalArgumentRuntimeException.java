/**
 * 
 */
package br.com.dclick.dtomanager.exceptions;

/**
 * Unchecked illegal argument exception.
 * 
 * @author bfuster
 * 
 */
public class IllegalArgumentRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -2395074791449434309L;

	public IllegalArgumentRuntimeException( String msg, Throwable ex ) {

		super( msg, ex );
	}

}
