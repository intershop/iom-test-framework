package com.intershop.oms.test.util;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marker Annotation - any functionality having this annotation is subject to
 * breaking changes or removals without further notice.
 *
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({ TYPE, METHOD, PACKAGE })
public @interface Experimental
{
    String value();
}
