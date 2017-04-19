package com.cs.aspose.aspose.data;

import java.util.List;

/**
 * Document template.
 *
 * @author Hoang DANG.
 */
public class DocumentTemplate<T> {

    private T data;
    private List<TemplateFile> files;
    private int format;

    /**
     * Constructor.
     */
    public DocumentTemplate(T data, List<TemplateFile> files, int format) {
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
    public List<TemplateFile> getFiles() {
        return files;
    }

    /**
     * @param files Is the files to set.
     */
    public void setFiles(List<TemplateFile> files) {
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
