package org.vic.test.domain.decoratorPattern;

/**
 * @author vicdor
 * @create 2017-03-02 13:18
 */
public class TakeShirt extends Decorator {
    public TakeShirt(Man man) {
        super(man);
    }

    public void myDress(){
        System.out.println("穿一件衬衣");
    }

    @Override
    public void dress() {
        myDress();
        super.dress();
    }
}
