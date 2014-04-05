
package com.andbrowser.vendingmachine;

import java.util.*;

public class SaleManager extends ArrayList<Integer> {

    public Integer getTotal() {
        Integer total = Integer.valueOf(0);
        for (final Iterator<Integer> it = iterator(); it.hasNext();) {
            total += it.next();
        }
        return total;

    }
}
