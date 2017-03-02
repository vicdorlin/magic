package org.vic.warrior.porters;

import org.vic.test.domain.Dog;
import org.vic.test.domain.Sheep;
import org.vic.test.domain.Sheep2;
import org.vic.warrior.DataPorter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author vicdor
 * @create 2016-08-02 21:03
 */
public class CatPorter extends DataPorter {
    public <D> void copyValue(D d, Object fieldValueA, Method dSetter, String fieldName) throws InvocationTargetException, IllegalAccessException {
        if("baby".equals(fieldName)){
            if(fieldValueA instanceof Sheep){
                try{
                    dSetter.invoke(d,super.copyData(Dog.class,fieldValueA));
                }catch (IllegalArgumentException exception){
                    dSetter.invoke(d,super.copyData(Sheep2.class,fieldValueA));
                }
            }else {
                dSetter.invoke(d,super.copyData(Sheep.class,fieldValueA));
            }
        }else if("hi".equals(fieldName)){
            if(fieldValueA instanceof Boolean){
                boolean hi = (boolean) fieldValueA;
                dSetter.invoke(d,hi?1:0);
            }
        }else {
            super.copyValue(d,fieldValueA,dSetter,fieldName);
        }
    }
}
