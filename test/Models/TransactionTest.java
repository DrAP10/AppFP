/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author DrAP
 */
public class TransactionTest {
    
    public TransactionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("-----Transaction Test-----");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("--------------------------");
    }

    /**
     * Test of getNFiles method, of class Transaction.
     */
    @Test
    public void testGetNFiles() {
        System.out.println("getNFiles");
        Transaction instance = new Transaction(TransactionType.EE, "nombre", 5, 3);
        int expResult = 5;
        int result = instance.getNFiles();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNDatas method, of class Transaction.
     */
    @Test
    public void testGetNDatas() {
        System.out.println("getNDatas");
        Transaction instance = new Transaction(TransactionType.EE, "nombre", 5, 3);
        int expResult = 3;
        int result = instance.getNDatas();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Transaction.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Transaction instance = new Transaction(TransactionType.EE, "nombre", 5, 3);
        TransactionType expResult = TransactionType.EE;
        TransactionType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Transaction.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Transaction instance = new Transaction(TransactionType.EE, "nombre", 5, 3);
        String expResult = "nombre";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComplexity method, of class Transaction.
     */
    @Test
    public void testGetComplexity() {
        System.out.println("getComplexity");
        Transaction instance = new Transaction(TransactionType.EE, "nombre", 5, 3);
        Complexity expResult = Complexity.Media;
        Complexity result = instance.getComplexity();
        assertEquals(expResult, result);
    }
    
}
