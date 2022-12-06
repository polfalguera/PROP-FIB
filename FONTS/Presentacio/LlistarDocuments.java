package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class LlistarDocuments extends JDialog {
    private JPanel contentPane;
    private JButton buttonSortir;
    private JList llistatDocuments;
    private JLabel infoTextField;

    public LlistarDocuments(List<String> documents, String funcio) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSortir);
        setSize(500,600);

        if (funcio == "Expressió Booleana") {
            infoTextField.setText("Llistat de documents que compleixen l'expressió booleana introduïda:");
        }
        else if(funcio == "Similaritat") {
            infoTextField.setText("Llistat de documents més semblants:");
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < documents.size()-1; i += 2) {
            String aux = (documents.get(i) + "," + documents.get(i + 1));
            res.add(aux);
        }
        llistatDocuments.setListData(res.toArray());

        buttonSortir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() { //Botó de sortir.
        dispose();
    }
}
