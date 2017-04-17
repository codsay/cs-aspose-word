package com.cs.aspose.aspose.converter;

import com.cs.aspose.aspose.AsposeContext;

import java.lang.annotation.Annotation;

/**
 * @author Hoang DANG.
 */
public interface AsposeAnnotationConverter {

	Object convertObject(AsposeContext asposeContext, Object originalValue, Object value, Annotation annotation);

}