package com.cs.aspose.aspose.data;

import java.util.List;

/**
 * Document template DTO.
 *
 * @author Hoang DANG.
 */
public class DocumentTemplateDto<T> {

    private T data;
    private List<DocumentTemplateFile> files;
    private int format;

    /**
     * Constructor.
     */
    public DocumentTemplateDto(T data, List<DocumentTemplateFile> files, int format) {
        this.data = data;
        this.files = files;
        this.format = format;
    }

    /**
     * @return Returns the data.
     */
    public T getData() {
        return data;
    }

    /**
     * @param data Is the data to set.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return Returns the files.
     */
    public List<DocumentTemplateFile> getFiles() {
        return files;
    }

    /**
     * @param files Is the files to set.
     */
    public void setFiles(List<DocumentTemplateFile> files) {
        this.files = files;
    }

    /**
     * @return Returns the format.
     */
    public int getFormat() {
        return format;
    }

    /**
     * @param format Is the format to set.
     */
    public void setFormat(int format) {
        this.format = format;
    }

}
