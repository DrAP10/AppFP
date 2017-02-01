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
public class GeneralAdjustment {
    static final int N_ELEMENTS = 14;
    int[] adjustmentArray = new int[N_ELEMENTS];
    int totalAdjustment;

    public GeneralAdjustment() {
        for(int i = 0 ; i < N_ELEMENTS ; i++)
            adjustmentArray[i] = 0;
        totalAdjustment = 0;
    }
    
    public void setAdjustment(int value, int index){
        if(value<=5 && value>=0 && index>=0 && index<N_ELEMENTS)
            adjustmentArray[index] = value;
        updateTotalAdjustment();
    }
    
    private void updateTotalAdjustment(){
        int total=0;
        for(int i = 0 ; i < N_ELEMENTS ; i++)
            total += adjustmentArray[i];
        totalAdjustment = total;
    }

    public int getTotalAdjustment() {
        return totalAdjustment;
    }
    
    
    
}
