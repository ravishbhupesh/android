package com.forex.app.util;

import java.util.Comparator;

/**
 * Created by bhupeshr on 4/11/2017.
 */

public class ForexSpinnerComparator implements Comparator<ForexSpinner> {
    @Override
    public int compare(ForexSpinner o1, ForexSpinner o2) {
        return o1.getSymbol().compareTo(o2.getSymbol());
    }
}
