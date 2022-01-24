package org.trading;

import java.util.ArrayList;

public class TradeCheckImpl implements TradeCheck {


    @Override
    public void checkForTrade(ArrayList<Order> buyOrder, ArrayList<Order> sellOrder) {
        ArrayList<Order> tradeList = new ArrayList<>();
        for (Order buy : buyOrder) {
            for (Order sell : sellOrder) {
                if (buy.getSharePrice() >= sell.getSharePrice()) {
                    //tradeList.add(sell);
                    Order orderTrade = new Order();
                    int remainShare = buy.getNumOfShares() - sell.getNumOfShares();
                    //System.out.println(" remainShare value:::: " + remainShare);
                    if (remainShare > 0) {
                       /* System.out.println(" remainShare>0 :::: " + remainShare);*/
                        remainShareGreaterThanZero(tradeList, buy, sell, orderTrade, remainShare);
                    } else if (remainShare < 0) {
                        remainShareLessThanZero(tradeList, buy, sell, orderTrade, remainShare);
                    } else {
                        sell.setSharePrice(0);
                        buy.setSharePrice(0);
                    }

                }
            }

        }

        buyOrder.removeIf(e -> e.getSharePrice() == 0);
        sellOrder.removeIf(e -> e.getSharePrice() == 0);
        tradeList.forEach(trade -> {
            System.out.println("TRADE " + trade.getNumOfShares() + "@" + trade.getSharePrice());
        });
    }

    private void remainShareLessThanZero(ArrayList<Order> tradeList, Order buy, Order sell, Order orderTrade, int remainShare) {
        sell.setNumOfShares(Math.abs(remainShare));
        orderTrade.setNumOfShares(buy.getNumOfShares());
        orderTrade.setSharePrice(sell.getSharePrice());
        tradeList.add(orderTrade);
        buy.setSharePrice(0);
    }

    private void remainShareGreaterThanZero(ArrayList<Order> tradeList, Order buy, Order sell, Order orderTrade, int remainShare) {
        buy.setNumOfShares(remainShare);
        orderTrade.setNumOfShares(sell.getNumOfShares());
        orderTrade.setSharePrice(sell.getSharePrice());
        sell.setSharePrice(0);
        tradeList.add(orderTrade);
    }
}
