package FONTS.Presentacio;


import javax.swing.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class MainView extends JFrame {
    private ControladorPresentacio ictrlPresentacio;

    private JLabel autor;
    private JPanel mainPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JComboBox comboBox1;

    private JList list1;
    private JButton crearDocumentButton;
    private JButton modificarAutorButton;
    private JButton modificarTitolButton;
    private JButton eliminarDocumentButton;
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
    private JMenuItem SelectAll;

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
        this.SelectAll = new JMenuItem("Select All");

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
        this.Edit.add(SelectAll);

        //Adding menus
        this.MenuBar.add(File);
        this.File.add(Export);
        this.MenuBar.add(Edit);
        this.MenuBar.add(Help);
        this.setJMenuBar(MenuBar);

    }

    public void initializeListeners() throws Exception{
        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getItemAt(comboBox1.getSelectedIndex()) == "Llistar autor" ) {
                    String autor = searchTextField.getText();
                    try {
                        List<String> ti = ictrlPresentacio.iqueryLlistarAutorsPrefix(autor);
                        JFrame aux = new LlistarTitols(ti,autor,"Llistar autor");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(comboBox1.getItemAt(comboBox1.getSelectedIndex()) == "Llistar titol" ) {
                    String autor = searchTextField.getText();
                    try {
                        List<String> ti = ictrlPresentacio.iqueryLlistarTitolsAutor(autor);
                        JFrame aux = new LlistarTitols(ti,autor,"Llistar titol");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
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
        SelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTextField.selectAll();
            }
        });
        modificarAutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() != -1) {
                    String[] a = list1.getSelectedValue().toString().split(",");
                    JDialog aux = new ModificarAutor();
                    aux.setVisible(true);
                    String nou_autor = ((ModificarAutor) aux).getNouAutor();
                    String doc = nou_autor+","+a[1];
                    boolean accept = ((ModificarAutor) aux).isAccept();
                    try {
                        if (accept) {
                            ictrlPresentacio.iqueryModificarAutor(a[0],nou_autor,a[1]);
                            ((DefaultListModel) list1.getModel()).setElementAt(doc,list1.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        modificarTitolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() != -1) {
                    String[] a = list1.getSelectedValue().toString().split(",");
                    JDialog aux = new ModificarTitol();
                    aux.setVisible(true);
                    String nou_titol = ((ModificarTitol) aux).getNouTitol();
                    String doc = a[0]+","+nou_titol;
                    boolean accept = ((ModificarTitol) aux).isAccept();
                    try {
                        if (accept) {
                            ictrlPresentacio.iqueryModificarTitol(a[0],a[1],nou_titol);
                            ((DefaultListModel) list1.getModel()).setElementAt(doc,list1.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        crearDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog aux = new CrearDocument();
                aux.setVisible(true);
                String autor = ((CrearDocument) aux).getAutor();
                String titol = ((CrearDocument) aux).getTitol();
                String contingut = ((CrearDocument) aux).getContingut();
                Boolean accept = ((CrearDocument) aux).isAccept();
                try {
                    if (accept) {
                        ictrlPresentacio.iqueryCrearDocument(autor,titol,contingut);
                        String doc = autor+","+titol;
                        ((DefaultListModel) list1.getModel()).addElement(doc);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        eliminarDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() != -1) {
                    String[] a = list1.getSelectedValue().toString().split(",");
                    JDialog aux = new EliminarDocument();
                    aux.setSize(350,150);
                    aux.setVisible(true);
                    boolean accept = ((EliminarDocument) aux).isAccept();
                    try {
                        if (accept) {
                            ictrlPresentacio.iqueryEliminarDocument(a[0],a[1]);
                            ((DefaultListModel) list1.getModel()).remove(list1.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        //Mostrar el Contingut
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    JList target = (JList) e.getSource();
                    int index = target.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Object item = target.getModel().getElementAt(index);
                        String[] a = item.toString().split(",");
                        try {
                            String Contingut = ictrlPresentacio.iqueryGetContingutDocument(a[0],a[1]);
                            JOptionPane.showMessageDialog(null,Contingut);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
    }
    public MainView(String title, ControladorPresentacio pCtrlPresentacio) throws Exception {
        super(title);
        ictrlPresentacio = pCtrlPresentacio;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(750,500);
        initializeMenuBar();
        initializeListeners();
    }

}
