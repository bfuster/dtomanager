package br.com.dclick.dtomanager.handlers;

import java.lang.reflect.Field;

public interface DTOHandler {

	public abstract Object handle( Field f, Object value );

}
