package org.vic.warrior;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 简易数据移植逻辑
 *
 * @author vicdor
 * @create 2017-03-08 15:27
 */
public class SimpleDataCopier implements IDataPorterCopier {
    @Override
    public <D> void copyValue(D d, Object fieldValueA, Method dSetter, String fieldName) throws IllegalAccessException, InvocationTargetException {
        dSetter.invoke(d, fieldValueA);
    }
}
