/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author DrAP
 */
public class Model {
    private GeneralAdjustment generalAdjustment;

    public Model() {
        this.generalAdjustment = new GeneralAdjustment();
    }

    public void setAdjustment(int value, int index) {
        generalAdjustment.setAdjustment(value, index);
    }

    public int getTotalAdjustment() {
        return generalAdjustment.getTotalAdjustment();
    }  
}
