package com.adam.p1_slotmachine;

/**
 * Created by webs on 6/13/17.
 */

public class Bank{
    private static int amount;

    public static void setAmount(int amt){
        amount = amt;
    }
    public static int getAmount(){
        return amount;
    }

    public static String getStrAmount(){

        return "$" + Integer.toString(Bank.getAmount());
    }

}
