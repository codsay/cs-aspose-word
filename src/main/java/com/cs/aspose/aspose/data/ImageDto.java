package com.cs.aspose.aspose.data;

import java.util.Arrays;

/**
 * @author Hoang DANG.
 */
public class ImageDto {

    private byte[] data;
    private double width;
    private double height;

    /**
     * Constructor.
     */
    public ImageDto(byte[] data, double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
        this.setData(data);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        if (data == null) {
            this.data = new byte[0];
        } else {
            this.data = Arrays.copyOf(data, data.length);
        }
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
