package com.cs.aspose.aspose.data;

/**
 * Template file.
 *
 * @author Hoang DANG.
 */
public class TemplateFile {

    private String fileName;
    private String field;

    /**
     * Create template file which is connected with a field.<br />
     * If this field's value is null or empty (collection), this template will be ignored.
     *
     * @param fileName
     * @param field
     */
    public TemplateFile(String fileName, String field) {
        this.fileName = fileName;
        this.field = field;
    }

    /**
     * Create template file.
     *
     * @param fileName
     */
    public TemplateFile(String fileName) {
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