package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;
import java.util.List;

import br.com.dclick.dtomanager.DataTransferObjectManager;
import br.com.dclick.dtomanager.annotations.Listing;

/**
 * Listing handler
 * @author bfuster
 *
 */
public class ListingHandler implements DTOHandler {

	/*
	 * (non-Javadoc)
	 * @see br.com.dclick.dtomanager.handlers.DTOHandler#handle(java.lang.reflect.Field, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public Object handle( Field f, Object value ) {

		if ( f.isAnnotationPresent( Listing.class ) && value != null )  {
			Class toClazz = f.getAnnotation( Listing.class ).value();
			value = new DataTransferObjectManager().copyUncheckedList( toClazz, (List< Object >) value );
		}

		return value;

	}
}
