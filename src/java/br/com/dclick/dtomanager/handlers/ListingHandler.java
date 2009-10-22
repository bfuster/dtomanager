package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

import br.com.dclick.dtomanager.DataTransferObjectManager;
import br.com.dclick.dtomanager.annotations.Collect;

/**
 * Listing handler
 * 
 * @author bfuster
 * 
 */
public class ListingHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect
	 * .Field, java.lang.Object)
	 */
	@SuppressWarnings( "unchecked" )
	public Object handle( Field f, Object value ) {

		Class toClazz = f.getAnnotation( Collect.class ).value();
		return new DataTransferObjectManager().copyList( toClazz, (java.util.Collection< Object >) value );

	}
}
