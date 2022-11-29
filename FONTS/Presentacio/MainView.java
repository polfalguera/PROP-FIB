package FONTS.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JLabel autor;
    private JPanel mainPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JComboBox comboBox1;
    private JList list1;
    private JMenuBar MenuBar;
    private JMenu File;
    private JMenu Export;
    private JMenu Edit;
    private JMenu Help;
    private JMenuItem Open;
    private JMenuItem NewDoc;
    private JMenuItem Save;
    private JMenuItem SaveAs;
    private JMenuItem txt;
    private JMenuItem xml;
    private JMenuItem jamp;
    private JMenuItem Copy;
    private JMenuItem Paste;
    private JMenuItem Cut;


    public void initializeMenuBar() {
        this.MenuBar = new JMenuBar();

        //Initializing items
        this.Open = new JMenuItem("Open");
        this.NewDoc = new JMenuItem("New document");
        this.Save = new JMenuItem("Save file");
        this.SaveAs = new JMenuItem("Save file as...");
        this.txt = new JMenuItem(".txt");
        this.xml = new JMenuItem(".xml");
        this.jamp = new JMenuItem(".jamp");
        this.Copy = new JMenuItem("Copy");
        this.Paste = new JMenuItem("Paste");
        this.Cut = new JMenuItem("Cut");

        //Initializing menus
        this.File = new JMenu("File");
        this.Export = new JMenu("Export");
        this.Edit = new JMenu("Edit");
        this.Help = new JMenu("Help");

        //Adding items
        this.File.add(Open);
        this.File.add(NewDoc);
        this.File.add(Save);
        this.File.add(SaveAs);
        this.Export.add(txt);
        this.Export.add(xml);
        this.Export.add(jamp);
        this.Edit.add(Copy);
        this.Edit.add(Paste);
        this.Edit.add(Cut);

        //Adding menus
        this.MenuBar.add(File);
        this.File.add(Export);
        this.MenuBar.add(Edit);
        this.MenuBar.add(Help);

        this.setJMenuBar(MenuBar);
    }

    public void initializeListeners() {

        Copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTextField.copy();
            }
        });

        Paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTextField.paste();
            }
        });

        Cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTextField.cut();
            }
        });

    }

    public MainView(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        this.setSize(750,500);
        initializeMenuBar();
        initializeListeners();
    }
}
