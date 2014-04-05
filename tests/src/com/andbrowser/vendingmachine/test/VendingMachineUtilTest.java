
package com.andbrowser.vendingmachine.test;

import android.test.*;

import com.andbrowser.vendingmachine.*;

public class VendingMachineUtilTest extends AndroidTestCase {

    public void testname() throws Exception {
        try {
            new VendingMachineUtil();
            fail();
        } catch (final AssertionError e) {
        }
    }

}
