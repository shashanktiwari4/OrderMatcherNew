package org.trading;

public class Order implements  Comparable{

    private int numOfShares;

    private int sharePrice;




    public int getNumOfShares() {
        return numOfShares;
    }

    public void setNumOfShares(int numOfShares) {
        this.numOfShares = numOfShares;
    }

    public int getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(int sharePrice) {
        this.sharePrice = sharePrice;
    }


    @Override
    public int compareTo(Object o) {
        Order ord=(Order)o;
        return this.sharePrice-ord.sharePrice;
    }
}
