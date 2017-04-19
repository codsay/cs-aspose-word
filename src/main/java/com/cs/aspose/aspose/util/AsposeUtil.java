package com.cs.aspose.aspose.util;

import com.cs.aspose.aspose.*;
import com.cs.aspose.aspose.data.DocumentTemplate;
import com.cs.aspose.aspose.data.TemplateFile;
import com.cs.aspose.aspose.handler.HandleMergeField;
import com.aspose.words.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provide Utility for Aspose.
 *
 * @author Hoang DANG.
 */
public class AsposeUtil {

    public static <T> void merge(Document doc, AsposeContext context, T object) throws Exception {
        MailMerge mailMerge = getMailMerge(doc);
        mailMerge.execute(new AsposeBean(context, object));
        mailMerge.executeWithRegions(new AsposeRegion(context, object));
    }

    public static MailMerge getMailMerge(Document doc) {
        MailMerge mailMerge = doc.getMailMerge();
        mailMerge.setTrimWhitespaces(false);
        mailMerge.setCleanupOptions(
                MailMergeCleanupOptions.REMOVE_UNUSED_FIELDS | MailMergeCleanupOptions.REMOVE_CONTAINING_FIELDS
                        | MailMergeCleanupOptions.REMOVE_EMPTY_PARAGRAPHS | MailMergeCleanupOptions.REMOVE_UNUSED_REGIONS);
        mailMerge.setFieldMergingCallback(new HandleMergeField());
        return mailMerge;
    }

    /**
     * Generate document.
     *
     * @param templateDto
     * @return
     */
    public static <T> byte[] generateDocument(AsposeContext context, DocumentTemplate<T> templateDto) {
        return generateDocument(context, templateDto, null);
    }

    /**
     * Generate document.
     *
     * @param templateDto
     * @param callback
     * @return
     */
    public static <T> byte[] generateDocument(AsposeContext context, DocumentTemplate<T> templateDto, IDocumentMergeCallback<T> callback) {
        List<Document> documents = new ArrayList<>(templateDto.getFiles().size());
        for (TemplateFile file : templateDto.getFiles()) {
            Object rootValue = templateDto.getData();
            if (StringUtils.isNotEmpty(file.getField())) {
                try {
                    Object value = PropertyUtils.getNestedProperty(templateDto.getData(), file.getField());
                    if (value == null || (value instanceof Iterable && CollectionUtils.sizeIsEmpty(value))) {
                        continue;
                    }
                    rootValue = value;
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new AsposeRTException("Cannot read field " + file.getField(), e);
                }
            }
            List<Object> values = new ArrayList<>();
            if (rootValue instanceof Iterable) {
                Iterator<Object> it = ((Iterable) rootValue).iterator();
                while (it.hasNext()) {
                    values.add(it.next());
                }
            } else {
                values.add(rootValue);
            }
            for (Object value : values) {
                try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file.getTemplate())) {
                    Document document = new Document(is);
                    merge(document, context, value);
                    if (callback != null) {
                        callback.apply(document, templateDto.getData());
                    }
                    documents.add(document);
                } catch (Exception e) {
                    throw new AsposeRTException("Technical error occurred while generating document", e);
                }
            }
        }

        try {
            Document rootDocument = documents.remove(0);
            combineDocuments(rootDocument, documents);
            rootDocument.updatePageLayout();
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                rootDocument.save(os, templateDto.getFormat());
                return os.toByteArray();
            }
        } catch (Exception e) {
            throw new AsposeRTException("Technical error occurred while combining documents", e);
        }
    }

    /**
     * Remove an empty block with marked with a name.
     *
     * @param doc
     * @param bookmarkName
     */
    public static void removeEmptyBlock(Document doc, String bookmarkName, int nodeType) throws Exception {
        Bookmark bookmark = doc.getRange().getBookmarks().get(bookmarkName);
        if (bookmark != null) {
            Node node = bookmark.getBookmarkStart().getAncestor(nodeType);
            if (node != null) {
                node.remove();
            }
            bookmark.remove();
        }
    }

    /**
     * Merge all documents to target document.
     *
     * @param target
     * @param documents
     * @return
     * @throws Exception
     */
    public static void combineDocuments(Document target, List<Document> documents) throws Exception {
        target.getFirstSection().getPageSetup().setRestartPageNumbering(true);
        target.getFirstSection().getPageSetup().setPageStartingNumber(1);
        int pageCount = target.getPageCount();

        for (Document document : documents) {
            document.getFirstSection().getPageSetup().setSectionStart(SectionStart.NEW_PAGE);
            document.getFirstSection().getPageSetup().setPageStartingNumber(pageCount + 1);
            pageCount += document.getPageCount();
            target.appendDocument(document, ImportFormatMode.KEEP_SOURCE_FORMATTING);
        }
    }

    private AsposeUtil() {
        // Do nothing.
    }
}