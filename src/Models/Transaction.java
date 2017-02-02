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
    private Complexity complexity;

    public Transaction(TransactionType type, String name, int nFiles, int nDatas) {
        this.type = type;
        this.name = name;
        this.nFilesIn = nFiles;
        this.nDatasIn = nDatas;
        this.nFilesOut = 0;
        this.nDatasOut = 0;
        updateComplexity();
    }

    public Transaction(TransactionType type, String name, int nFilesIn, int nFilesOut, int nDatasIn, int nDatasOut) {
        this.type = type;
        this.name = name;
        this.nFilesIn = nFilesIn;
        this.nFilesOut = nFilesOut;
        this.nDatasIn = nDatasIn;
        this.nDatasOut = nDatasOut;
        updateComplexity();
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

    public Complexity getComplexity() {
        return complexity;
    }
    
    private void updateComplexity(){
        switch (type){
            case EE:
                if(nFilesIn<=1){
                    if(nDatasIn<=15)
                        complexity=Complexity.Sencilla;
                    else
                        complexity=Complexity.Media;
                } else if(nFilesIn==2){
                    if(nDatasIn<5)
                        complexity=Complexity.Sencilla;
                    else if(nDatasIn<=15)
                        complexity=Complexity.Media;
                    else
                        complexity=Complexity.Compleja;
                } else {
                    if(nDatasIn<5)
                        complexity=Complexity.Media;
                    else
                        complexity=Complexity.Compleja;
                }
                break;
            case SE:
                if(nFilesIn<=1){
                    if(nDatasIn<20)
                        complexity=Complexity.Sencilla;
                    else
                        complexity=Complexity.Media;
                } else if(nFilesIn==2||nFilesIn==3){
                    if(nDatasIn<=5)
                        complexity=Complexity.Sencilla;
                    else if(nDatasIn<20)
                        complexity=Complexity.Media;
                    else
                        complexity=Complexity.Compleja;
                } else {
                    if(nDatasIn<=5)
                        complexity=Complexity.Media;
                    else
                        complexity=Complexity.Compleja;
                }
                break;
            case GLE:
            case GLI:
                if(nFilesIn==1){
                    if(nDatasIn<=50)
                        complexity=Complexity.Sencilla;
                    else
                        complexity=Complexity.Media;
                } else if(nFilesIn>1 && nFilesIn<6){
                    if(nDatasIn<20)
                        complexity=Complexity.Sencilla;
                    else if(nDatasIn<=50)
                        complexity=Complexity.Media;
                    else
                        complexity=Complexity.Compleja;
                } else {
                    if(nDatasIn<20)
                        complexity=Complexity.Media;
                    else
                        complexity=Complexity.Compleja;
                }
                break;
            case CE:
                int entrada, salida;
                //Calculamos con los de entrada
                if(nFilesIn<=1){
                    if(nDatasIn<=15)
                        entrada=0;
                    else
                        entrada=1;
                } else if(nFilesIn==2){
                    if(nDatasIn<5)
                        entrada=0;
                    else if(nDatasIn<=15)
                        entrada=1;
                    else
                        entrada=2;
                } else {
                    if(nDatasIn<5)
                        entrada=1;
                    else
                        entrada=2;
                }
                //Calculamos ocn los de salida
                if (nFilesOut <= 1) {
                    if (nDatasOut < 20) {
                        salida=0;
                    } else {
                        salida=1;
                    }
                } else if (nFilesOut == 2 || nFilesOut == 3) {
                    if (nDatasOut <= 5) {
                        salida=0;
                    } else if (nDatasOut < 20) {
                        salida=1;
                    } else {
                        salida=2;
                    }
                } else {
                    if (nDatasOut <= 5) {
                        salida=1;
                    } else {
                        salida=2;
                    }
                }
                //Comparamos
                if(entrada==2||salida==2)
                        complexity=Complexity.Compleja;
                else if(entrada==1||salida==1)
                        complexity=Complexity.Media;
                else
                        complexity=Complexity.Sencilla;
        }
    }
}
