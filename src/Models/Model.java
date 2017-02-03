package Models;

import java.util.ArrayList;

public class Model {

    private GeneralAdjustment generalAdjustment;
    private ArrayList<Transaction> transactions;
    private int[][] matrizComplejidad;
    private double fa; //Factor de Ajuste
    private double fp; //Puntos Función Ajustados
    private double fpna; //Puntos Función no Ajustados
    private double duration, cDuration, eDuration; //Estimación de duración, y variables para factores de calibrado
    private double effort, cEffort, eEffort; //Estimación del esfuerzo, y variables para factores de calibrado

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
        effort = 0; cEffort = 0; eEffort = 0;
        duration = 0; cDuration = 0; eDuration = 0;
    }

    public void setAdjustment(int value, int index) {
        generalAdjustment.setAdjustment(value, index);
        updateFA();
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
        this.fa = calcularFA();
        updateFP();
    }

    public void updateFP() {
        this.fp = calcularFP();
        updateDuration();
        updateEffort();
    }

    public void updateFPNA() {
        this.fpna = calcularFPNA();
        updateFP();
    }

    private void updateDuration() {
        this.duration = this.cDuration * Math.pow(this.fp, eDuration);
    }
    
    public void updateDuration(double c, double e) {
        this.cDuration = c;
        this.eDuration = e;
        this.duration = this.cDuration * Math.pow(this.fp, eDuration);
    }

    private void updateEffort() {
        this.effort = this.cEffort * Math.pow(this.fp, eEffort);
    }
    
    public void updateEffort(double c, double e) {
        this.cEffort = c;
        this.eEffort = e;
        this.effort = this.cEffort * Math.pow(this.fp, eEffort);
    }

    public double getFa() {
        return this.fa;
    }

    public double getFp() {
        return this.fp;
    }

    public double getFpna() {
        return this.fpna;
    }
    
    public double calcularFA() {
        return (generalAdjustment.getTotalAdjustment() * 0.01) + 0.65;
    }
    
    public double calcularFP() {
        return fa * fpna;
    }
    
    public double calcularFPNA() {
        int[][] m = matrizComplejidad;
        return m[0][0] * 3 + m[0][1] * 4 + m[0][2] * 6
                + m[1][0] * 4 + m[1][1] * 5 + m[1][2] * 7
                + m[2][0] * 7 + m[2][1] * 10 + m[2][2] * 15
                + m[3][0] * 5 + m[3][1] * 7 + m[3][2] * 10
                + m[4][0] * 3 + m[4][1] * 4 + m[4][2] * 6;
    }

    public double getDuration() {
        return this.duration;
    }

    public double getEffort() {
        return this.effort;
    }
}
