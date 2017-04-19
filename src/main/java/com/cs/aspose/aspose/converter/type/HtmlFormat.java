package com.cs.aspose.aspose.converter.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTML format. <br />
 * Apply for {@code String} type.
 *
 * @author Hoang DANG.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlFormat {

    /**
     * @return allowable tags.
     */
    String[] allowTags() default {};

}
