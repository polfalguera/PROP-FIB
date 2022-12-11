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
        //setModal(true); //Fa que les finestres obertes abans que el dialog no es puguin utilitzar fins a que es tanqui el dialog.
        //getRootPane().setDefaultButton(buttonSortir); Si cliques ENTER l'acció equivalent és picar el botó sortir.

        /*-----------------------------------------*/
        infoLabel.setText("Contingut del document amb autor "+a+" i títol '"+t+"':");
        contingut.setText(c);
        contingut.setCaretPosition(0); //To set scrollbar at the top of the text.
        pack();
        setSize(500,500);
        setVisible(true);
        /*-----------------------------------------*/

        buttonSortir.addActionListener(new ActionListener() {
            @Override
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
