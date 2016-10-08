package org.webdocdb.core.document.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IndexField {

	public enum IndexOrder {
		ASC(1),
		DESC(-1);
		private int value;
		private IndexOrder(int value) {
			this.value = value;
		}
		public int intValue() {
			return value;
		}
	}

	String name();
	IndexOrder order() default IndexOrder.ASC;
}
