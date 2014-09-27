package com.neumiiller.oeno.managers;

import com.squareup.otto.Bus;

/**
 * @author QJN on 2014-09-27.
 */
public class BusDepot {
    private static final Bus BUS = new Bus();

    public static Bus get() {
        return BUS;
    }

    private BusDepot() {
        // No instances.
    }
}
