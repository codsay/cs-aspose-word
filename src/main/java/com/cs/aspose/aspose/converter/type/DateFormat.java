package com.cs.aspose.aspose.converter.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.format.DateTimeFormatter;

/**
 * Time format.
 *
 * @author Hoang DANG.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {

    /**
     * @return true if the formatted value should have weekday name.
     */
    boolean hasWeekday() default false;

    String pattern() default "";

}
