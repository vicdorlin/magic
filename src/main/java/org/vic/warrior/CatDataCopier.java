package org.vic.warrior;

import org.vic.test.domain.Dog;
import org.vic.test.domain.Sheep;
import org.vic.test.domain.Sheep2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author vicdor
 * @create 2017-03-08 15:59
 */
public class CatDataCopier implements IDataPorterCopier {
    @Override
    public <D> void copyValue(D d, Object fieldValueA, Method dSetter, String fieldName) throws IllegalAccessException, InvocationTargetException {
        if ("baby".equals(fieldName)) {
            if (fieldValueA instanceof Sheep) {
                try {
                    dSetter.invoke(d, DataPorter.newPorter().copyData(Dog.class, fieldValueA));
                } catch (IllegalArgumentException exception) {
                    dSetter.invoke(d, DataPorter.newPorter().copyData(Sheep2.class, fieldValueA));
                }
            } else {
                dSetter.invoke(d, DataPorter.newPorter().copyData(Sheep.class, fieldValueA));
            }
        } else if ("hi".equals(fieldName)) {
            if (fieldValueA instanceof Boolean) {
                boolean hi = (boolean) fieldValueA;
                dSetter.invoke(d, hi ? 1 : 0);
            }
        } else {
            new SimpleDataCopier().copyValue(d, fieldValueA, dSetter, fieldName);
        }
    }
}
