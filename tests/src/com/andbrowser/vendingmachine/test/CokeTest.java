
package com.andbrowser.vendingmachine.test;

import android.test.*;

import com.andbrowser.vendingmachine.*;

public class CokeTest extends AndroidTestCase {

    private Coke mCoke;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCoke = new Coke();
    }

    public void testName() throws Exception {
        assertEquals("Coke", mCoke.getName());
    }

    public void testPrice() throws Exception {
        assertEquals(Integer.valueOf(120), mCoke.getPrice());
    }

}
