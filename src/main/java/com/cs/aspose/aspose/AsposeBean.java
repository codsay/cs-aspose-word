package com.cs.aspose.aspose;

import com.aspose.words.IMailMergeDataSource;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @@author Hoang DANG.
 */
public class AsposeBean extends AbstractAsposeBean implements IMailMergeDataSource {

    private List<Object> objects = new ArrayList<>();
    private int index = -1;
    private String regionName;

    public AsposeBean(AsposeContext context, Object object) {
        super(context);
        objects.add(object);
    }

    public AsposeBean(AsposeContext context, Object object, String regionName) {
        this(context, object);
        this.regionName = regionName;
    }

    public AsposeBean(AsposeContext context, Collection<?> dtos, String regionName) {
        super(context);
        this.regionName = regionName;

        objects = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dtos)) {
            objects.addAll(dtos);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public IMailMergeDataSource getChildDataSource(String regionName) throws Exception {
        try {
            Object object = objects.get(index);
            Object value = getValue(object, regionName);
            if (value != null && Collection.class.isAssignableFrom(value.getClass())) {
                // <<TableStart:regionName>>
                return new AsposeBean(context, (Collection<Object>) value, regionName);
            } else {
                // <<user.firstName>>
                // "user" is treated as a region.
                return new AsposeBean(context, value, regionName);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new AsposeRTException("Cannot read field " + regionName, e);
        } catch (NestedNullException e) {
            return new AsposeBean(context, null, regionName);
        }
    }

    @Override
    public String getTableName() throws Exception {
        return regionName;
    }

    @Override
    public boolean getValue(String fieldName, Object[] values) throws Exception {
        try {
            if (fieldName.startsWith("msg_")) {
                String messageKey = fieldName.replace("msg_", StringUtils.EMPTY).replace("*", ".");
                values[0] = context.getMessageResource().getMessage(context.getLanguage(), messageKey);
                return true;
            }
            values[0] = getFormattedValue(objects.get(index), fieldName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new AsposeRTException("Cannot read field " + fieldName, e);
        } catch (NestedNullException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean moveNext() throws Exception {
        if (++index < objects.size()) {
            return true;
        }
        return false;
    }
}