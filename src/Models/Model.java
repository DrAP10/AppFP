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
    private int[][] matrizComplejidad;
    private double fa; //Factor de Ajuste
    private double fp; //Puntos Función Ajustados
    private double fpna; //Puntos Función no Ajustados

    public Model() {
        this.generalAdjustment = new GeneralAdjustment();
        this.transactions = new ArrayList<Transaction>();
        matrizComplejidad = new int[5][3];
        for (int row = 0; row < matrizComplejidad.length; row++) {
            for (int col = 0; col < matrizComplejidad[row].length; col++) {
                matrizComplejidad[row][col] = 0;
            }
        }
        fpna = 0;
        fp = 0;
    }

    public void setAdjustment(int value, int index) {
        generalAdjustment.setAdjustment(value, index);
    }

    public int getTotalAdjustment() {
        return generalAdjustment.getTotalAdjustment();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateMatrizComplejidad(transaction);
    }

    private void updateMatrizComplejidad(Transaction transaction) {
        switch (transaction.getType()) {
            case EE:
                if (transaction.getComplexity() == Complexity.Sencilla) {
                    matrizComplejidad[0][0]++;
                } else if (transaction.getComplexity() == Complexity.Media) {
                    matrizComplejidad[0][1]++;
                } else {
                    matrizComplejidad[0][2]++;
                }
                break;
            case SE:
                if (transaction.getComplexity() == Complexity.Sencilla) {
                    matrizComplejidad[1][0]++;
                } else if (transaction.getComplexity() == Complexity.Media) {
                    matrizComplejidad[1][1]++;
                } else {
                    matrizComplejidad[1][2]++;
                }
                break;
            case CE:
                if (transaction.getComplexity() == Complexity.Sencilla) {
                    matrizComplejidad[2][0]++;
                } else if (transaction.getComplexity() == Complexity.Media) {
                    matrizComplejidad[2][1]++;
                } else {
                    matrizComplejidad[2][2]++;
                }
                break;
            case GLI:
                if (transaction.getComplexity() == Complexity.Sencilla) {
                    matrizComplejidad[3][0]++;
                } else if (transaction.getComplexity() == Complexity.Media) {
                    matrizComplejidad[3][1]++;
                } else {
                    matrizComplejidad[3][2]++;
                }
                break;
            case GLE:
                if (transaction.getComplexity() == Complexity.Sencilla) {
                    matrizComplejidad[4][0]++;
                } else if (transaction.getComplexity() == Complexity.Media) {
                    matrizComplejidad[4][1]++;
                } else {
                    matrizComplejidad[4][2]++;
                }
                break;
        }
        updateFPNA();
    }

    public int[][] getMatrizComplejidad() {
        return matrizComplejidad;
    }

    public void updateFA() {
        this.fa = (generalAdjustment.getTotalAdjustment() * 0.01) + 0.65;
        updateFP();
    }

    public void updateFP() {
        this.fp = this.fa * this.fpna;
    }

    public void updateFPNA() {
        int[][] m = this.matrizComplejidad;
        this.fpna = m[0][0] * 3 + m[0][1] * 4 + m[0][2] * 6
                + m[1][0] * 4 + m[1][1] * 5 + m[1][2] * 7
                + m[2][0] * 7 + m[2][1] * 10 + m[2][2] * 15
                + m[3][0] * 5 + m[3][1] * 7 + m[3][2] * 10
                + m[4][0] * 3 + m[4][1] * 4 + m[4][2] * 6;
        updateFP();
    }

    public double getFa() {
        return fa;
    }

    public double getFp() {
        return fp;
    }

    public double getFpna() {
        return fpna;
    }
}
