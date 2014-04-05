
package com.andbrowser.vendingmachine.test;

import android.test.*;

import com.andbrowser.vendingmachine.*;

public class StockManagerTest extends AndroidTestCase {

    private StockManager mStockManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mStockManager = new StockManager();
    }

    public void testPurchaseOnEmpty() throws Exception {
        assertFalse(mStockManager.purchase(new Coke()));
    }

    public void testIsStockFalse() throws Exception {
        final Coke coke = new Coke();
        assertFalse(mStockManager.isStock(coke));
    }

}
