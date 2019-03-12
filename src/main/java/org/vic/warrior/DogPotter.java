package org.vic.warrior;

import org.vic.test.domain.Cat;
import org.vic.test.domain.Dog;

/**
 * @author vicdor
 * @create 2017-11-14 19:10
 */
public class DogPotter implements IPorter<Dog,Cat> {
    @Override
    public Cat transferF2T(Dog dog) {
        return new Cat(dog);
    }
}
