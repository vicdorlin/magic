package org.vic.test.domain.decoratorPattern;

/**
 * @author vicdor
 * @create 2017-03-02 11:59
 */
public class SuperMan implements Man {
    @Override
    public void dress() {
        System.out.println("我是超人");
    }

    public static void main(String[] args) {
        SuperMan sm = new SuperMan();
        TakeShoe ts = new TakeShoe(sm);
        TakeShirt tst = new TakeShirt(ts);
        tst.dress();
    }
}
