package com.cs.aspose.aspose.util;

import com.aspose.words.Document;

/**
 * @author Hoang DANG.
 */
public interface IDocumentMergeCallback<T> {

    /**
     * Callback after merging document.
     *
     * @throws Exception
     */
    void apply(Document doc, T data) throws Exception;

}
