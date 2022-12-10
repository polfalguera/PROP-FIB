package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.*;

public class HistorialExpressionsBooleanes extends JDialog {
    private JPanel contentPane;
    private JButton buttonSortir;
    private JList expressionsList;
    private JButton copiarExpressióButton;
    private JButton modificarExpressióButton;
    private JButton eliminarExpressióButton;

    public void initialize() {
        buttonSortir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSortir();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { onSortir(); }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onSortir(); }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        copiarExpressióButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    public HistorialExpressionsBooleanes() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSortir);

        initialize();
    }
    private void onSortir() {
        // add your code here if necessary
        dispose();
    }
}
