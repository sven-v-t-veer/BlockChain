package com.sven.bc.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation determines the Serializer Class to be instantiated for the specific serializable class
 * @author sven
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SerializedClass {
	
	String serializer() default "com.sven.bc.serializer.BasicSerializer";

}
