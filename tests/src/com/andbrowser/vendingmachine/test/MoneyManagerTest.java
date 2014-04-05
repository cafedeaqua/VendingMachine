
package com.andbrowser.vendingmachine.test;

import android.test.*;

import com.andbrowser.vendingmachine.*;

import java.util.*;

public class MoneyManagerTest extends AndroidTestCase {

    private MoneyManager mMoneyManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMoneyManager = new MoneyManager();
    }

    public void testInitialTotal() throws Exception {
        assertEquals(Integer.valueOf(0), mMoneyManager.getTotal());
    }

    public void testSubtractive() throws Exception {
        assertEquals(mMoneyManager, mMoneyManager.subtractive(Integer.valueOf(100)));
    }

    public void testInitialRefund() throws Exception {
        assertEquals(Integer.valueOf(0), mMoneyManager.getTotal());
        final ArrayList<Integer> refundList = mMoneyManager.refund();
        assertEquals(Integer.valueOf(0), mMoneyManager.getTotal());
        assertTrue(refundList.isEmpty());
    }

    public void testOneInsertRefund() throws Exception {
        assertEquals(Integer.valueOf(0), mMoneyManager.getTotal());
        mMoneyManager.insert(Money.COIN_10);
        assertEquals(Integer.valueOf(10), mMoneyManager.getTotal());
        final ArrayList<Integer> refundList = mMoneyManager.refund();
        final ArrayList<Integer> expectedList = new ArrayList<Integer>();
        expectedList.add(Money.COIN_10);
        assertEquals(expectedList, refundList);
        assertEquals(Integer.valueOf(0), mMoneyManager.getTotal());
    }

    public void testAllMoneyInsertRefund() throws Exception {
        assertEquals(Integer.valueOf(0), mMoneyManager.getTotal());
        mMoneyManager.insert(Money.COIN_10);
        mMoneyManager.insert(Money.COIN_50);
        mMoneyManager.insert(Money.COIN_100);
        mMoneyManager.insert(Money.COIN_500);
        mMoneyManager.insert(Money.BILL_1000);
        assertEquals(Integer.valueOf(1660), mMoneyManager.getTotal());
        final ArrayList<Integer> refundList = mMoneyManager.refund();
        final ArrayList<Integer> expectedList = new ArrayList<Integer>();
        expectedList.add(Money.COIN_10);
        expectedList.add(Money.COIN_50);
        expectedList.add(Money.COIN_100);
        expectedList.add(Money.COIN_500);
        expectedList.add(Money.BILL_1000);
        assertEquals(expectedList, refundList);
        assertEquals(Integer.valueOf(0), mMoneyManager.getTotal());
    }
}
