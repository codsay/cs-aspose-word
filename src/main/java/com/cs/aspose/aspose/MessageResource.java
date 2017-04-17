package com.cs.aspose.aspose;

import java.util.Locale;

/**
 * Created by Hoang DANG.
 */
public interface MessageResource {

    String getMessage(Locale language, String key);

    String getMessage(Locale language, String key, Object... params);

}
