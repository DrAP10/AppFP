/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DrAP
 */
public class GeneralAdjustmentTest {
    
    public GeneralAdjustmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("--------------GeneralAdjustment Test------------");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("------------------------------------------------");
    }

    /**
     * Test of setAdjustment method, of class GeneralAdjustment.
     */
    @Test
    public void testSetAndGetAdjustment() {
        System.out.println("set and get Adjustment");
        GeneralAdjustment instance = new GeneralAdjustment();
        instance.setAdjustment(5, 0);
        instance.setAdjustment(2, 1);
        instance.setAdjustment(3, 2);
        instance.setAdjustment(1, 3);
        instance.setAdjustment(4, 4);
        instance.setAdjustment(4, 5);
        instance.setAdjustment(5, 6);
        instance.setAdjustment(0, 7);
        int expResult = 24;
        int result = instance.getTotalAdjustment();
        assertEquals(expResult, result);
    }

    
}
