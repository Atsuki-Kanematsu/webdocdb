package org.webdocdb.core.document.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Index {

	public enum IndexOrder {
		ASC,
		DESC
	}
	
	IndexOrder order() default IndexOrder.ASC;
	String group() default "";
	boolean unique() default false;
}
