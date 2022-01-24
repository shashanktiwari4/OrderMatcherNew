package org.trading;

import java.util.ArrayList;

public interface TradeCheck {

    public void checkForTrade(ArrayList<Order> buyOrder, ArrayList<Order> sellOrder);

}
