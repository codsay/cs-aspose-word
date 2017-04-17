package com.cs.aspose.aspose.converter;

import com.cs.aspose.aspose.AsposeContext;

/**
 * @author Hoang DANG.
 */
public interface AsposeConverter {

	/**
	 * Convert a value to a difference value.
	 * 
	 * @param asposeContext
	 * @param value
	 * @return converted value
	 */
	Object convertObject(AsposeContext asposeContext, Object value);

}