package com.cs.aspose.aspose;

import com.cs.aspose.aspose.converter.AsposeAnnotationConverter;
import com.cs.aspose.aspose.converter.AsposeConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Hoang DANG.
 */
public class AsposeContext {

    private Locale language;
    private MessageResource messageResource;

    private final Map<Class<?>, List<AsposeConverter>> converterMap = new HashMap<>();
    private final Map<Class<? extends Annotation>, List<AsposeAnnotationConverter>> annotationConverterMap = new LinkedHashMap<>();

    public AsposeContext(Locale language) {
        this.language = language;
    }

    public Locale getLanguage() {
        return language;
    }

    public AsposeContext setLanguage(Locale language) {
        this.language = language;
        return this;
    }

    public MessageResource getMessageResource() {
        return messageResource;
    }

    public AsposeContext setMessageResource(MessageResource messageResource) {
        this.messageResource = messageResource;
        return this;
    }

    /**
     * Register converter.
     *
     * @param clazz
     * @param converter
     * @return
     */
    public <T> AsposeContext register(Class<T> clazz, AsposeConverter converter) {
        if (!converterMap.containsKey(clazz)) {
            converterMap.put(clazz, new ArrayList<>());
        }
        converterMap.get(clazz).add(converter);
        return this;
    }

    /**
     * @return converter.
     */
    public List<AsposeConverter> getConverter(Class<?> clazz) {
        return converterMap.get(clazz);
    }

    /**
     * Register converter.
     *
     * @param clazz
     * @param converter
     * @return
     */
    public AsposeContext registerAnnotation(Class<? extends Annotation> clazz, AsposeAnnotationConverter converter) {
        if (!annotationConverterMap.containsKey(clazz)) {
            annotationConverterMap.put(clazz, new ArrayList<>());
        }
        annotationConverterMap.get(clazz).add(converter);
        return this;
    }

    /**
     * @return converter.
     */
    public List<AsposeAnnotationConverter> getAnnotationConverter(Class<? extends Annotation> clazz) {
        return annotationConverterMap.get(clazz);
    }

    /**
     * @return anotation classes.
     */
    public Collection<Class<? extends Annotation>> getAnotationClasses() {
        return annotationConverterMap.keySet();
    }


    public Object formatValue(Object entity, String fieldName, Object originalValue) {
        if (originalValue == null) {
            return null;
        }

        Object value = originalValue;
        List<AsposeConverter> typeConverters = getConverter(originalValue.getClass());
        if (typeConverters != null) {
            for (AsposeConverter typeConverter : typeConverters) {
                value = typeConverter.convertObject(this, value);
            }
        }

        for (Class<? extends Annotation> annotationType : getAnotationClasses()) {
            Annotation annotation = getField(entity.getClass(), fieldName).getAnnotation(annotationType);
            if (annotation != null) {
                for (AsposeAnnotationConverter converter : getAnnotationConverter(annotationType)) {
                    value = converter.convertObject(this, originalValue, value, annotation);
                }
            }
        }

        return value;
    }

    public static Field getField(Class<?> originalClass, String fieldName) {
        Field field = null;
        Class<?> clazz = originalClass;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                // The field doesn't exist in child class.
                // Look up in parent class.
            }
            clazz = clazz.getSuperclass();
        }
        if (field == null) {
            throw new AsposeRTException("Unable to find field " + fieldName + " in class" + originalClass.getSimpleName());
        }
        return field;
    }

}