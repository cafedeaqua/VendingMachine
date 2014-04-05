
package com.andbrowser.vendingmachine;

import java.util.*;

public class VendingMachineUtil {

    public VendingMachineUtil() {
        throw new AssertionError();
    }

    public static ArrayList<JuiceStock> calculateStock(final ArrayList<Juice> stock) {
        final ArrayList<JuiceStock> resultList = new ArrayList<JuiceStock>();
        int cokeCounter = 0;
        JuiceStock cokeStock = null;
        final Iterator<Juice> it = stock.iterator();
        while (it.hasNext()) {
            final Juice juice = it.next();
            // NOTE: If adding another juice, you MUST add below logic.
            if (juice instanceof Coke) {
                if (cokeCounter == 0) {
                    cokeStock = new JuiceStock(juice);
                }
                cokeCounter++;
            }
        }
        if (cokeStock != null) {
            cokeStock.setStockSize(cokeCounter);
            resultList.add(cokeStock);
        }
        return resultList;
    }
}
