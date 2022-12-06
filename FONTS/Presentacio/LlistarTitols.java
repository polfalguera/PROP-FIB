package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class LlistarTitols extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList list1;
    private JLabel infoTextField;

    public LlistarTitols(List<String> list_titols, String autor,String funtion) {
        setContentPane(contentPane);
        setSize(500,500);
        getRootPane().setDefaultButton(buttonOK);

        if (funtion == "Llistar titol") {
            infoTextField.setText("Llista de titols amb autor "+autor);
        }
        else if (funtion == "Llistar autor") {
            infoTextField.setText("Llista de autors amb prefix: "+autor);
        }
        list1.setListData(list_titols.toArray());


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
