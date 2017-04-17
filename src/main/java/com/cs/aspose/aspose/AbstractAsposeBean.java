package com.cs.aspose.aspose;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hoang DANG.
 */
public abstract class AbstractAsposeBean {

    protected AsposeContext context;

    public AbstractAsposeBean(AsposeContext context) {
        this.context = context;
    }

    protected Object getValue(Object target, String field) throws Exception {
        if (target == null) {
            return null;
        }
        return PropertyUtils.getNestedProperty(target, standardFieldName(field));
    }

    @SuppressWarnings("unchecked")
    protected Object getFormattedValue(Object inTarget, String inField) throws Exception {
        if (StringUtils.contains(inField, "#")) {
            String[] inFields = StringUtils.split(inField, "#");
            Collection<Object> value = getValueAsCollection(inTarget, inFields[0]);
            for (int i = 1; i < inFields.length; i++) {
                int index = NumberUtils.toInt(inFields[i]);
                if (index >= value.size()) {
                    return 0;
                }
                Object value2 = value.toArray()[index];
                if (i == inFields.length - 1) {
                    return value2;
                } else {
                    value = (Collection<Object>) value2;
                }
            }
            return null;
        }

        Object target = inTarget;
        String field = standardFieldName(inField);

        String[] fields = field.split("\\.");
        for (int i = 0; i < fields.length - 2; i++) {
            if (target == null) {
                return null;
            }
            target = PropertyUtils.getSimpleProperty(target, fields[i]);
        }
        if (target == null) {
            return null;
        }

        String fieldName = fields[fields.length - 1];
        return context.formatValue(target, fieldName, PropertyUtils.getSimpleProperty(target, fieldName));
    }

    protected Collection<Object> getValueAsCollection(Object target, String field) throws Exception {
        return (Collection<Object>) getValue(target, field);
    }

    private String standardFieldName(String inField) {
        if (StringUtils.isBlank(inField)) {
            throw new IllegalArgumentException("Illegal field name");
        }
        String[] fields = StringUtils.trimToEmpty(inField).split("\\.");
        List<String> standardFields = new ArrayList<>(fields.length);
        for (String field : fields) {
            if (StringUtils.isAllUpperCase(field)) {
                standardFields.add(StringUtils.lowerCase(field));
            } else {
                final int len = field.length();
                StringBuilder builder = new StringBuilder("");
                for (int i = 0; i < len; i++) {
                    if ((i == 0) || ((i < len - 1) && Character.isUpperCase(field.charAt(i)) && Character.isUpperCase(field.charAt(i + 1)))) {
                        builder.append(StringUtils.lowerCase(String.valueOf(field.charAt(i))));
                    } else {
                        builder.append(String.valueOf(field.charAt(i)));
                    }
                }
                standardFields.add(builder.toString());
            }
        }
        return StringUtils.join(standardFields, ".");
    }
}