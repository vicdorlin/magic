package org.vic.test.domain.decoratorPattern;

/**
 * @author vicdor
 * @create 2017-03-02 12:06
 */
public class TakeShoe extends Decorator {

    public TakeShoe(Man man) {
        super(man);
    }

    private void myDress(){
        System.out.println("穿了鞋子");
    }

    @Override
    public void dress(){
        myDress();
        super.dress();
    }
}
