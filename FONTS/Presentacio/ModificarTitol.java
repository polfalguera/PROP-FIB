package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.*;

public class ModificarTitol extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nouTitolTextField;
    private JLabel nouTitol;

    private boolean accept = false;

    public ModificarTitol() {
        setContentPane(contentPane);
        setModal(true);
        setSize(500,500);
        getRootPane().setDefaultButton(buttonOK);

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

    public boolean isAccept() {
        return accept;
    }

    public String getNouTitol() {
       return nouTitolTextField.getText();
    }
    private void onOK() {
        accept = true;
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ModificarTitol dialog = new ModificarTitol();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
