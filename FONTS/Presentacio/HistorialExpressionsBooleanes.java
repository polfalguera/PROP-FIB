package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.Set;

/**
 * Classes per a poder copiar en el CLIPBOARD l'expressió desitjada.
 */
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class HistorialExpressionsBooleanes extends JDialog {
    private JPanel contentPane;
    private JButton buttonSortir;
    private JList expressionsList;
    private JButton copiarExpressioButton;
    private JButton modificarExpressioButton;
    private JButton eliminarExpressioButton;

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

        copiarExpressioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = expressionsList.getSelectedValue().toString();
                Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                cb.setContents(new StringSelection(a),null);
            }
        });
    }
    public HistorialExpressionsBooleanes(Set<String> exs) {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonSortir);

        initialize();
        for (String ex: exs) { ((DefaultListModel) expressionsList.getModel()).addElement(ex); }
        pack();
        setSize(750,375);
        setVisible(true);
    }
    private void onSortir() {
        // add your code here if necessary
        dispose();
    }
}
