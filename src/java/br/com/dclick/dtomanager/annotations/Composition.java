package br.com.dclick.dtomanager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Call the api recursivily for nested object.
 * 
 * @author bfuster
 * 
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
public @interface Composition {

	String[] props() default {};

}
