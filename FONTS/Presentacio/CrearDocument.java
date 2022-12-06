package FONTS.Presentacio;


import javax.swing.*;
import java.awt.event.*;

public class CrearDocument extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField autorTextField;
    private JTextField titolTextField;
    private JTextField contingutTextField;
    private JLabel autor;
    private JTextArea contingutTextArea;

    private boolean accept = false;

    public CrearDocument() {
        setContentPane(contentPane);
        contingutTextArea.setSize(5,10);
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

    public String getAutor() {
        return autorTextField.getText();
    }
    public String getTitol() {
        return titolTextField.getText();
    }

    public String getContingut() {
        return contingutTextArea.getText();
    }
    public boolean isAccept() {
        return accept;
    }

    private void onOK() {
        accept = true;
        // add your code here
        dispose();
    }

    private void onCancel() {
        accept = false;
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CrearDocument dialog = new CrearDocument();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
