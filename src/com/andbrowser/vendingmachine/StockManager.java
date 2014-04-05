
package com.andbrowser.vendingmachine;

import java.util.*;

public class StockManager extends ArrayList<Juice> {

    public boolean isStock(final Juice juice) {
        final Iterator<Juice> it = iterator();
        while (it.hasNext()) {
            if (it.next().getName() == juice.getName()) {
                return true;
            }
        }
        return false;
    }

    public boolean purchase(final Juice juice) {
        final Iterator<Juice> it = iterator();
        while (it.hasNext()) {
            final Juice stockJuice = it.next();
            if (stockJuice.getName() == juice.getName()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

}
