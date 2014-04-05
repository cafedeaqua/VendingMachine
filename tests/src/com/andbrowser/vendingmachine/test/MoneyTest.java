
package com.andbrowser.vendingmachine.test;

import android.test.*;

import com.andbrowser.vendingmachine.*;

public class MoneyTest extends AndroidTestCase {

    public void testname() throws Exception {
        try {
            new Money();
            fail();
        } catch (final AssertionError e) {
        }
    }

}
