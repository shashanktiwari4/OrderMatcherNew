package org.trading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Order> buyOrder = new ArrayList<>();
        ArrayList<Order> sellOrder = new ArrayList<>();
        TradeCheck tradeCheck = new TradeCheckImpl();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.equalsIgnoreCase("PRINT")) {
                String[] tokens = line.split("\\s");
                // System.out.println(tokens[0] + " " + tokens[1]);
                String[] share = tokens[1].split("@");
                Order order = new Order();
                order.setNumOfShares(Integer.parseInt(share[0]));
                order.setSharePrice(Integer.parseInt(share[1]));
                if (tokens[0].equalsIgnoreCase("BUY")) {
                    buyOrder.add(order);
                } else if (tokens[0].equalsIgnoreCase("SELL")) {
                    sellOrder.add(order);
                }
                tradeCheck.checkForTrade(buyOrder, sellOrder);
            } else {
                ArrayList<Order> tempSellOrder = new ArrayList<>(sellOrder);
                ArrayList<Order> tempBuyOrder = new ArrayList<>(buyOrder);
                Collections.sort(tempSellOrder);
                Collections.sort(tempBuyOrder, Collections.reverseOrder());
                System.out.println(" -- SELL --");
                tempSellOrder.forEach(e -> {
                    System.out.println("SELL" + " " + e.getNumOfShares() + "@" + e.getSharePrice());
                });
                System.out.println(" --BUY--");
                tempBuyOrder.forEach(e -> {
                    System.out.println("BUY" + " " + e.getNumOfShares() + "@" + e.getSharePrice());
                });

            }


        }

    }
}
