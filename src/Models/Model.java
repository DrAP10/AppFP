/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author DrAP
 */
public class Model {
    private GeneralAdjustment generalAdjustment;
    private ArrayList<Transaction> transactions;

    public Model() {
        this.generalAdjustment = new GeneralAdjustment();
        this.transactions = new ArrayList<Transaction>();
    }

    public void setAdjustment(int value, int index) {
        generalAdjustment.setAdjustment(value, index);
    }

    public int getTotalAdjustment() {
        return generalAdjustment.getTotalAdjustment();
    }  
    
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
}
