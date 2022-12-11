package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Classes per a poder copiar en el CLIPBOARD l'expressió desitjada.
 */
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class HistorialExpressionsBooleanes extends JDialog {
    private JPanel contentPane;
    private JButton buttonSortir;
    private JList expressionsList;
    private JButton seleccionarExpressioButton;
    private JButton eliminarExpressioButton;
    private String exSeleccionada = new String();
    private List<String> exsEliminades = new ArrayList<>();

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

        seleccionarExpressioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionsList.getSelectedIndex() != -1) {
                    exSeleccionada = expressionsList.getSelectedValue().toString();
                    dispose();
                }
            }
        });

        eliminarExpressioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expressionsList.getSelectedIndex() != -1) {
                    String exEliminada = expressionsList.getSelectedValue().toString(); //agafo l'expressió seleccionada
                    ((DefaultListModel) expressionsList.getModel()).remove(expressionsList.getSelectedIndex()); //elimino l'expressió de la llista

                    exsEliminades.add(exEliminada);
                }
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
    public String getExSeleccionada() { return exSeleccionada; }
    public List<String> getExsEliminades() { return exsEliminades; }
}
