package Controllers;

import Interfaces.Observer;
import Models.*;
import Views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Controller implements Observer{

    //VIEW
    MainWindow mainWindow;
    //MODEL
    Model model;

    ActionListener comboBoxActionListener;

    public Controller(MainWindow mainWindow, Model model) {
        this.mainWindow = mainWindow;
        this.model = model;

        comboBoxActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.valueOf(((JComboBox) e.getSource()).getSelectedItem().toString());
                int index = Integer.valueOf(e.getActionCommand());
                model.setAdjustment(value, index);
                actualizarVista();
            }
        };
        addComboBoxsActionListners();

        mainWindow.setTransactionFormOutVisible(false);
        mainWindow.addTransactionFormView.comboBoxTransactionType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean visible = ((JComboBox) e.getSource()).getSelectedItem().toString().equals("CE");
                mainWindow.setTransactionFormOutVisible(visible);
            }
        });

        mainWindow.addTransactionFormView.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mainWindow.isTransactionValid()) {
                    JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                model.addTransaction(mainWindow.getTransaction());
                actualizarVista();
            }
        });
        
        mainWindow.effort.addObserver(this);
        mainWindow.duration.addObserver(this);
    }

    private void addComboBoxsActionListners() {
        mainWindow.generalAdjustmentFormView.jComboBox1.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox2.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox3.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox4.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox5.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox6.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox7.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox8.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox9.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox10.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox11.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox12.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox13.addActionListener(comboBoxActionListener);
        mainWindow.generalAdjustmentFormView.jComboBox14.addActionListener(comboBoxActionListener);
    }

    public void iniciar() {
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
        model.updateFA();
        mainWindow.fAjLabel.setText(String.valueOf(model.getFa()));
        mainWindow.pAjLabel.setText(String.valueOf(model.getFp()));
        mainWindow.pfnajLabel.setText(String.valueOf(model.getFpna()));
        mainWindow.esfuerzojLabel.setText(String.valueOf(model.getEffort()));
        mainWindow.duracionjLabel.setText(String.valueOf(model.getDuration()));
        mainWindow.updateTable(model.getMatrizComplejidad());
    }
    
    public void actualizarVista() {
        mainWindow.updateTable(model.getMatrizComplejidad());
        mainWindow.pfnajLabel.setText(String.valueOf(model.getFpna()));
        mainWindow.generalAdjustmentFormView.labelTotal.setText(String.valueOf(model.getTotalAdjustment()));
        mainWindow.fAjLabel.setText(String.valueOf(model.getFa()));
        mainWindow.pAjLabel.setText(String.valueOf(model.getFp()));
        mainWindow.esfuerzojLabel.setText(String.valueOf(model.getEffort()));
        mainWindow.duracionjLabel.setText(String.valueOf(model.getDuration()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        MainWindow mainWindow = new MainWindow();
        Model model = new Model();
        Controller controller = new Controller(mainWindow, model);
        controller.iniciar();
    }

    @Override
    public void actualizarDuracion(double c, double e) {
        model.updateDuration(c, e);
        mainWindow.duracionjLabel.setText(String.valueOf(model.getDuration()));
    }

    @Override
    public void actualizarEsfuerzo(double c, double e) {
        model.updateEffort(c, e);
        mainWindow.esfuerzojLabel.setText(String.valueOf(model.getEffort()));
    }
}
