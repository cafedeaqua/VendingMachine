
package com.andbrowser.vendingmachine;

public class JuiceStock {
    Juice mJuice;

    Integer mStockSize;

    public JuiceStock(final Juice juice) {
        mJuice = juice;
        mStockSize = 1;
    }

    public Juice getJuice() {
        return mJuice;
    }

    public Integer getStockSize() {
        return mStockSize;
    }

    public void setStockSize(final Integer stockSize) {
        mStockSize = stockSize;
    }

}
