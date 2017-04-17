package com.cs.aspose.aspose.data;

/**
 * @author Hoang DANG.
 */
public final class DocumentTemplateFile {

    private String fileName;
    private String field;

    /**
     * Create template file which is connected with a field.<br />
     * If this field's value is null or empty (collection), this template will be ignored.
     *
     * @param fileName
     * @param field
     */
    public DocumentTemplateFile(String fileName, String field) {
        this.fileName = fileName;
        this.field = field;
    }

    /**
     * Create template file.
     *
     * @param fileName
     */
    public DocumentTemplateFile(String fileName) {
        this(fileName, null);
    }

    /**
     * @return Returns the fileName.
     */
    public String getTemplate() {
        return fileName;
    }

    /**
     * @param fileName Is the fileName to set.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return Returns the field.
     */
    public String getField() {
        return field;
    }

    /**
     * @param field Is the field to set.
     */
    public void setField(String field) {
        this.field = field;
    }

}