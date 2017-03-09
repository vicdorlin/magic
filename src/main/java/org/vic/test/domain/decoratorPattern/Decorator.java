package org.vic.test.domain.decoratorPattern;

/**
 * @author vicdor
 * @create 2017-03-02 12:01
 */
public abstract class Decorator implements Man{
    protected Man man;

    public Decorator(Man man) {
        this.man = man;
    }

    @Override
    public void dress(){
        if(man != null){
            man.dress();
        }
    }

}
