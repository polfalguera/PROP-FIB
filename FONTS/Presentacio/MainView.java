package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainView extends JFrame {

    private JMenu file, edit,help,Export;
    private JMenuItem cut,copy,paste,selectAll, New,Open,Save,Save_as,txt,xml,jamp;
    private JMenuBar mb = new JMenuBar();
    private final ControladorPresentacio iCtrlPresentation;
    private JPanel panel1;
    private JButton button1;
    private JTextArea ta;

    /**
     * Inicialitza els components de la Vista Principal i assigna els listeners corresponents.
     */
    private void initializeComponents() {
        initializeMenuBar();
        assign_listenerComponents();
        panel1.setVisible(true);
        button1.setVisible(true);
    }

    /**
     * Inicialitza els botons del menú de la Vista Principal.
     */
    private void initializeMenuBar() {

        JFrame f= new JFrame("La nostra Aplicacio");


        ta=new JTextArea();
        ta.setBounds(5,5,360,320);
        f.add(ta);

        file =new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        Export = new JMenu("Export");

        cut =new JMenuItem("cut");
        paste =new JMenuItem("paste");
        selectAll =new JMenuItem("selectAll");
        New = new JMenuItem("New");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        Save_as = new JMenuItem("Save as");
        txt = new JMenuItem("txt");
        xml = new JMenuItem("uml");
        jamp = new JMenuItem("jamp");

        Export.add(txt);Export.add(xml); Export.add(jamp);
        file.add(New);file.add(Open);file.add(Save);file.add(Save_as);file.add(Export);
        edit.add(cut); edit.add(paste); edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        f.setJMenuBar(mb);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }

    /**
     * Constructora de la Vista Principal.
     *
     * @param pCtrlPresentation controlador presentació.
     */
    public MainView(ControladorPresentacio pCtrlPresentation) {
        iCtrlPresentation = pCtrlPresentation;
        initializeComponents();
    }
    public void actionPerformed (ActionEvent e) throws Exception {
        Object source = e.getSource();

        if(e.getSource()==cut)
            ta.cut();
        if(e.getSource()==paste)
            ta.paste();
        if(e.getSource()==copy)
            ta.copy();
        if(e.getSource()==selectAll)
            ta.selectAll();
    }

    private void assign_listenerComponents() {

        New.addActionListener(e -> {
            try {
                actionPerformed(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        paste.addActionListener(e -> {
            try {
                actionPerformed(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cut.addActionListener(e -> {
            try {
                actionPerformed(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        selectAll.addActionListener(e -> {
            try {
                actionPerformed(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
