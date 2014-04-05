
package com.andbrowser.vendingmachine;

import java.util.*;

public class VendingMachine {

    private MoneyManager mInsertMoney;

    private final StockManager mJuiceStock;

    private final SaleManager mSaleList;

    public VendingMachine() {
        mJuiceStock = new StockManager();
        mInsertMoney = new MoneyManager();
        mSaleList = new SaleManager();
    }

    public Integer getTotal() {
        return mInsertMoney.getTotal();
    }

    public boolean insert(final Integer i) {
        if (mInsertMoney.insert(i)) {
            return true;
        }
        return false;
    }

    public ArrayList<Integer> refund() {
        return mInsertMoney.refund();
    }

    public void addStock(final Juice coke) {
        mJuiceStock.add(coke);
    }

    public ArrayList<Juice> getStock() {
        return mJuiceStock;
    }

    public boolean isPurchase(final Juice juice) {
        if (!mInsertMoney.isPurchase(juice)) {
            return false;
        }
        return mJuiceStock.isStock(juice);
    }

    public boolean purchase(final Juice juice) {
        boolean status = false;
        if (!isPurchase(juice)) {
            return status;
        }
        status = mJuiceStock.purchase(juice);
        final Integer price = juice.getPrice();
        mSaleList.add(price);
        mInsertMoney = mInsertMoney.subtractive(price);
        return status;
    }

    public Integer getSaleAmount() {
        return mSaleList.getTotal();
    }
}
