package org.vic.warrior.porters;

import org.vic.test.domain.Dog;
import org.vic.test.domain.Sheep;
import org.vic.test.domain.Sheep2;
import org.vic.warrior.DataPorter;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.vic.util.CommonUtils.transferToString;

/**
 * @author vicdor
 * @create 2016-08-02 21:03
 */
public class CatPorter extends DataPorter {
    public <D, A> void transferFieldValue(D d, Class<D> clazzD, A a, Class<A> clazzA, String fieldName, String fieldNameOfA) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor pdA = new PropertyDescriptor(fieldNameOfA, clazzA);
        Method aGetter = pdA.getReadMethod();
        Object fieldValueA = aGetter.invoke(a);

        PropertyDescriptor pdD = new PropertyDescriptor(fieldName, clazzD);
        Method dSetter = pdD.getWriteMethod();
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
        }else {
            try {
                dSetter.invoke(d, fieldValueA);
            } catch (IllegalArgumentException e) {
                try {
                    //如果类型不对应,尝试转为String
                    dSetter.invoke(d, transferToString(fieldValueA));
                } catch (IllegalArgumentException exception) {
                    //如果类型依旧不对，尝试转为Date
                    dSetter.invoke(d, transferToDate(fieldValueA));
                }
            }
        }
    }
}
