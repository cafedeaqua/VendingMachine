
package com.andbrowser.vendingmachine;

abstract public class Juice {

    protected String mName;

    protected Integer mPrice;

    public Juice() {
        super();
    }

    public String getName() {
        return mName;
    }

    public Integer getPrice() {
        return mPrice;
    }

}
