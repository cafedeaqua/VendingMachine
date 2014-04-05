
package com.andbrowser.vendingmachine;

import java.util.*;

public class MoneyManager extends ArrayList<Integer> {

    public Integer getTotal() {
        Integer total = Integer.valueOf(0);
        final Iterator<Integer> it = iterator();
        while (it.hasNext()) {
            total += it.next();
        }
        return total;
    }

    public boolean isPurchase(final Integer price) {
        if (getTotal() >= price) {
            return true;
        }
        return false;
    }

    public boolean isPurchase(final Juice coke) {
        return isPurchase(coke.getPrice());
    }

    public boolean insert(final Integer inputMoney) {
        if (Money.COIN_10.equals(inputMoney) || Money.COIN_50.equals(inputMoney)
                || Money.COIN_100.equals(inputMoney)
                || Money.COIN_500.equals(inputMoney) || Money.BILL_1000.equals(inputMoney)) {
            add(inputMoney);
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getArrayList() {
        return this;
    }

    public MoneyManager subtractive(final Integer price) {
        Integer total = getTotal();
        if (total < price) {
            return this;
        }
        total -= price;
        return regenerateInsertMoney(total);
    }

    private MoneyManager regenerateInsertMoney(final Integer receive_total) {
        Integer total = receive_total;
        final MoneyManager newMoneyList = new MoneyManager();
        total = extractCoinAndBill(newMoneyList, total, Money.BILL_1000);
        total = extractCoinAndBill(newMoneyList, total, Money.COIN_500);
        total = extractCoinAndBill(newMoneyList, total, Money.COIN_100);
        total = extractCoinAndBill(newMoneyList, total, Money.COIN_50);
        total = extractCoinAndBill(newMoneyList, total, Money.COIN_10);
        return newMoneyList;
    }

    private Integer extractCoinAndBill(final MoneyManager newMoneyList, Integer total,
            final Integer coinOrBill) {
        final int counter = total / coinOrBill;
        for (int i = 0; i < counter; i++) {
            newMoneyList.add(coinOrBill);
            total -= coinOrBill;
        }
        return total;
    }

    public ArrayList<Integer> refund() {
        final ArrayList<Integer> refundList = new ArrayList<Integer>(this);
        removeAll(this);
        return refundList;
    }
}
