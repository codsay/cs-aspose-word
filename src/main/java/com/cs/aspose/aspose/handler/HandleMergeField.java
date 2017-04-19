package com.cs.aspose.aspose.handler;

import com.cs.aspose.aspose.data.ImageValue;
import com.cs.aspose.aspose.data.HtmlValue;
import com.aspose.words.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * @author Hoang DANG.
 */
public class HandleMergeField implements IFieldMergingCallback {

	private static final BufferedImage EMPTY_IMAGE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	static {
		final Graphics2D g2 = EMPTY_IMAGE.createGraphics();
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, EMPTY_IMAGE.getWidth(), EMPTY_IMAGE.getHeight());
		g2.dispose();
	}

	@Override
	public void fieldMerging(FieldMergingArgs field) throws Exception {
		Object orgValue = field.getFieldValue();
		if (orgValue instanceof HtmlValue) {
			HtmlValue htmlValue = (HtmlValue) orgValue;
			String value = htmlValue.getFormattedValue();

			DocumentBuilder builder = new DocumentBuilder(field.getDocument());
			builder.moveToMergeField(field.getFieldName());
			builder.insertHtml(value, true);
			return;
		}
	}

	/**
	 * Handle for image fields: «Image:XXX»
	 * <p>
	 * - Allowed return values: An image object / A filename / A stream contains the image
	 * </p>
	 */
	@Override
	public void imageFieldMerging(ImageFieldMergingArgs field) throws Exception {
		Object value = field.getFieldValue();
		if (!(value instanceof ImageValue)) {
			return;
		}

		ImageValue imageDto = (ImageValue) value;
		try {
			field.setImageStream(new ByteArrayInputStream(imageDto.getData()));
		} catch (Exception e) {
			field.setImage(EMPTY_IMAGE);
		}
		field.setImageHeight(new MergeFieldImageDimension(imageDto.getHeight(), MergeFieldImageDimensionUnit.POINT));
		field.setImageWidth(new MergeFieldImageDimension(imageDto.getWidth(), MergeFieldImageDimensionUnit.POINT));
	}

}