package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;
import java.util.List;

import br.com.dclick.dtomanager.DataTransferObjectManager;
import br.com.dclick.dtomanager.annotations.Listing;

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

		Class toClazz = f.getAnnotation( Listing.class ).value();
		return new DataTransferObjectManager().copyUncheckedList( toClazz, (List< Object >) value );

	}
}
