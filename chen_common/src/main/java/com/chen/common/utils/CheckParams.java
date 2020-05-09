package com.chen.common.utils;

import com.chen.common.exception.BaseException;
import com.chen.common.exception.ErrorEnum;
import com.github.pagehelper.util.StringUtil;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collection;

/**
 * @author chen
 */
public class CheckParams {
    public static boolean stringNotBlank(String module, String param, String paramName) {
        if (StringUtil.isEmpty(param)) {
            String message = ErrorEnum.STRING_NOT_BLANK.getMessage();
            message = MessageFormat.format(message, paramName);
            throw new BaseException(module, ErrorEnum.STRING_NOT_BLANK.getCode(), message);
        }
        return true;
    }
    public static boolean booleanNotBlank(String module, Boolean param, String paramName) {
        if (param == null) {
            String message = ErrorEnum.BOOLEAN_NOT_BLANK.getMessage();
            message = MessageFormat.format(message, paramName);
            throw new BaseException(module, ErrorEnum.BOOLEAN_NOT_BLANK.getCode(), message);
        }
        return true;
    }
    public static boolean integerNotBlank(String module, Integer param, String paramName) {
        if (param == null) {
            String message = ErrorEnum.INTEGER_NOT_BLANK.getMessage();
            message = MessageFormat.format(message, paramName);
            throw new BaseException(module, ErrorEnum.INTEGER_NOT_BLANK.getCode(), message);
        }
        return true;
    }
    public static boolean listNotBlank(String module, Collection<?> param, String paramName) {
        if (CollectionUtils.isEmpty(param)) {
            String message = ErrorEnum.LIST_NOT_BLANK.getMessage();
            message = MessageFormat.format(message, paramName);
            throw new BaseException(module, ErrorEnum.LIST_NOT_BLANK.getCode(), message);
        }
        return true;
    }
    public static boolean objectNotBlank(String module, Object param, String paramName) {
        if (param == null) {
            String message = ErrorEnum.OBJECT_NOT_BLANK.getMessage();
            message = MessageFormat.format(message, paramName);
            throw new BaseException(module, ErrorEnum.OBJECT_NOT_BLANK.getCode(), message);
        }
        return true;
    }
}
