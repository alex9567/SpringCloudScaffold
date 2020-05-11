package com.chen.common.utils;

import java.text.MessageFormat;

public class MessageFormatUtil {
    public static String messageFormat(String message, Object... args) {
        if (message == null) {
            return message;
        }
        if (args.length == 0) {
            return message;
        } else {
            return MessageFormat.format(message, args);
        }
    }
}
