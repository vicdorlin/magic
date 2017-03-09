package org.vic.warrior;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author vicdor
 * @create 2017-03-08 15:11
 */
public interface IDataPorterCopier {
    /**
     *
     * @param d
     * @param fieldValueA
     * @param dSetter
     * @param fieldName
     * @param <D>
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    <D> void copyValue(D d, Object fieldValueA, Method dSetter, String fieldName) throws IllegalAccessException, InvocationTargetException;
}
