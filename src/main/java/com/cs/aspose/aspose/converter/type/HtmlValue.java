package com.cs.aspose.aspose.converter.type;

/**
 * HTML value.
 *
 * @@author Hoang DANG.
 */
public class HtmlValue {

    private String value;
    private HtmlFormat format;

    /**
     * Constructor.
     *
     * @param value
     */
    public HtmlValue(String value, HtmlFormat format) {
        this.value = value;
        this.format = format;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value Is the value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return Returns the format.
     */
    public HtmlFormat getFormat() {
        return format;
    }

    /**
     * @param format Is the format to set.
     */
    public void setFormat(HtmlFormat format) {
        this.format = format;
    }

    /**
     * Format the value to send to Aspose.
     *
     * @return formatted value.
     */
    public String getFormattedValue() {
        String value = getValue();
        value = value.replaceAll("<a", "<a style=\"color: blue; text-decoration: underline;\" ");
        return value;
    }
}