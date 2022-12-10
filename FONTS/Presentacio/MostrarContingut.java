package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.*;

public class MostrarContingut extends JDialog {
    private JPanel contentPane;
    private JButton buttonSortir;
    private JLabel infoLabel;
    private JTextArea contingut;

    public MostrarContingut(String a, String t, String c) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSortir);

        /*-----------------------------------------*/
        pack();
        infoLabel.setText("Contingut del document amb autor "+a+" i títol '"+t+"':");
        contingut.setSize(500,500);
        contingut.setText(c);
        setVisible(true);
        /*-----------------------------------------*/

        buttonSortir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSortir();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onSortir();
            }
        });
    }

    private void onSortir() {
        // add your code here
        dispose();
    }
}
