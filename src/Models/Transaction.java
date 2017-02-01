/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Juan Antonio
 */
public class Transaction {
    private TransactionType type;
    private String name;
    private int nFilesIn;
    private int nFilesOut;
    private int nDatasIn;
    private int nDatasOut;

    public Transaction(TransactionType type, String name, int nFilesIn, int nDatasIn) {
        this.type = type;
        this.name = name;
        this.nFilesIn = nFilesIn;
        this.nDatasIn = nDatasIn;
        this.nFilesOut = 0;
        this.nDatasOut = 0;
    }

    public Transaction(TransactionType type, String name, int nFilesIn, int nFilesOut, int nDatasIn, int nDatasOut) {
        this.type = type;
        this.name = name;
        this.nFilesIn = nFilesIn;
        this.nFilesOut = nFilesOut;
        this.nDatasIn = nDatasIn;
        this.nDatasOut = nDatasOut;
    }
    
    public int getNFiles(){
        return nFilesIn + nFilesOut;
    }
    
    public int getNDatas(){
        return nDatasIn + nDatasOut;
    }

    public TransactionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
    
    
}
