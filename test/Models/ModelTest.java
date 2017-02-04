/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DrAP
 */
public class ModelTest {
    
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("-----Model Test-----");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("--------------------");
    }
    
    @Test
    public void testCalcularFPNA() {
        System.out.println("Calcular FPNA");
        Model instance = new Model();
        instance.setAdjustment(5, 0);
        instance.setAdjustment(3, 1);
        instance.setAdjustment(2, 2);
        instance.addTransaction(new Transaction(TransactionType.EE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.GLE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.SE, "name", 1, 1));
        instance.addTransaction(new Transaction(TransactionType.CE, "name", 5, 3, 7, 10));
        double expResult = 26.0;
        double result = instance.calcularFPNA();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalcularFA() {
        System.out.println("Calcular FA");
        Model instance = new Model();
        instance.setAdjustment(5, 0);
        instance.setAdjustment(3, 1);
        instance.setAdjustment(2, 2);
        instance.addTransaction(new Transaction(TransactionType.EE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.GLE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.SE, "name", 1, 1));
        instance.addTransaction(new Transaction(TransactionType.CE, "name", 5, 3, 7, 10));
        double expResult = 0.75;
        double result = instance.calcularFA();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalcularFP() {
        System.out.println("Calcular FP");
        Model instance = new Model();
        instance.setAdjustment(5, 0);
        instance.setAdjustment(3, 1);
        instance.setAdjustment(2, 2);
        instance.addTransaction(new Transaction(TransactionType.EE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.GLE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.SE, "name", 1, 1));
        instance.addTransaction(new Transaction(TransactionType.CE, "name", 5, 3, 7, 10));
        double expResult = 19.5;
        double result = instance.calcularFP();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalcularEffort() {
        System.out.println("Calcular Effort");
        Model instance = new Model();
        instance.setAdjustment(5, 0);
        instance.setAdjustment(3, 1);
        instance.setAdjustment(2, 2);
        instance.addTransaction(new Transaction(TransactionType.EE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.GLE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.SE, "name", 1, 1));
        instance.addTransaction(new Transaction(TransactionType.CE, "name", 5, 3, 7, 10));
        instance.updateEffort(48.90, 0.661);
        double expResult = 348.354;
        double result = instance.calcularEffort();
        assertEquals(expResult, result, 0.001);
    }
    
    @Test
    public void testCalcularDuration() {
        System.out.println("Calcular Duration");
        Model instance = new Model();
        instance.setAdjustment(5, 0);
        instance.setAdjustment(3, 1);
        instance.setAdjustment(2, 2);
        instance.addTransaction(new Transaction(TransactionType.EE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.GLE, "name", 5, 3));
        instance.addTransaction(new Transaction(TransactionType.SE, "name", 1, 1));
        instance.addTransaction(new Transaction(TransactionType.CE, "name", 5, 3, 7, 10));
        instance.updateDuration(0.503, 0.409);
        double expResult = 1.695;
        double result = instance.CalcularDuration();
        assertEquals(expResult, result, 0.001);
    }
}
