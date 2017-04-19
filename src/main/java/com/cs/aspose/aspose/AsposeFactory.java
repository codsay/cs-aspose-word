package com.cs.aspose.aspose;

import com.cs.aspose.aspose.data.DocumentTemplate;
import com.cs.aspose.aspose.util.AsposeUtil;
import com.cs.aspose.aspose.util.IDocumentMergeCallback;

/**
 * Created by Hoang DANG.
 */
public class AsposeFactory {

    private AsposeContext context;

    public AsposeFactory(AsposeContext context) {
        this.context = context;
    }

    /**
     * Generate document.
     *
     * @param templateDto
     * @return
     */
    public <T> byte[] generateDocument(DocumentTemplate<T> templateDto) {
        return generateDocument(templateDto, null);
    }

    /**
     * Generate document.
     *
     * @param templateDto
     * @param callback
     * @return
     */
    public <T> byte[] generateDocument(DocumentTemplate<T> templateDto, IDocumentMergeCallback<T> callback) {
        return AsposeUtil.generateDocument(context, templateDto, callback);
    }

}
