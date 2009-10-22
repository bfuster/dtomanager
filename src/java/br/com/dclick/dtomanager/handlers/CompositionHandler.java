package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

import br.com.dclick.dtomanager.DataTransferObjectManager;
import br.com.dclick.dtomanager.annotations.Composition;

/**
 * Composition handler
 * @author bfuster
 *
 */
public class CompositionHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * @see br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect.Field, java.lang.Object)
	 */
	public Object handle( Field f, Object value ) {

		if ( f.isAnnotationPresent( Composition.class ) && value != null )
			value = DataTransferObjectManager.doCopy( f.getType(), value, f.getAnnotation( Composition.class ).props() );

		return value;
	}

}
