package br.com.dclick.dtomanager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * New enum from string (Enum.valueOf).
 * 
 * @author bfuster
 * 
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
public @interface StringToEnum {

}
