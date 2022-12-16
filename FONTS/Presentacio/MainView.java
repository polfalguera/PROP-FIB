package FONTS.Presentacio;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;


public class MainView extends JFrame {
    private ControladorPresentacio ictrlPresentacio;
    private JPanel mainPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JComboBox consultesComboBox;


    private JPopupMenu popupMenu;

    private JMenuItem pop_crear_document;
    private JMenuItem pop_modificar_autor;
    private JMenuItem pop_modificar_titol;
    private JMenuItem pop_modificar_contingut;
    private JMenuItem pop_eliminar_document;

    private JMenuItem pop_exportar_document;
    private JMenuItem pop_importar_document;

    private JList listDocuments;
    private JButton crearDocumentButton;
    private JButton modificarAutorButton;
    private JButton modificarTitolButton;
    private JButton eliminarDocumentButton;
    private JButton modificarContingutButton;
    private JButton historialButton;
    private JButton importarDocumentButton;
    private JButton exportarDocumentButton;
    private JSpinner nDocumentsSpinner;
    private JRadioButton Model1;
    private JRadioButton Model2;
    private ButtonGroup models;
    private JPanel simi_rellePanel;
    private JLabel nDocumentsLabel;
    private JMenuBar MenuBar;
    private JMenu File;
    private JMenu Edit;
    private JMenu Help;
    private JMenuItem Open;
    private JMenuItem NewDoc;
    private JMenuItem Save;
    private JMenuItem SaveAs;
    private JMenuItem Export;
    private JMenuItem Import;
    private JMenuItem Copy;
    private JMenuItem Paste;
    private JMenuItem Cut;
    private JMenuItem SelectAll;

    public void initializeMenuBar() {

        popupMenu = new JPopupMenu();
        pop_crear_document = new JMenuItem("crear document");
        pop_modificar_autor = new JMenuItem("modificar autor");
        pop_modificar_titol = new JMenuItem("modificar titol");
        pop_modificar_contingut = new JMenuItem("modificar contingut");
        pop_eliminar_document = new JMenuItem("eliminar document");
        pop_exportar_document = new JMenuItem("exportar document");
        pop_importar_document = new JMenuItem("importar document");

        popupMenu.add(pop_crear_document);
        popupMenu.add(pop_modificar_autor);
        popupMenu.add(pop_modificar_titol);
        popupMenu.add(pop_modificar_contingut);
        popupMenu.add(pop_eliminar_document);
        popupMenu.add(pop_exportar_document);
        popupMenu.add(pop_importar_document);
        this.MenuBar = new JMenuBar();

        //Initializing items
        this.Open = new JMenuItem("Open");
        this.NewDoc = new JMenuItem("New document");
        this.Save = new JMenuItem("Save file");
        this.SaveAs = new JMenuItem("Save file as...");
        this.Export = new JMenuItem("Exportar document");
        this.Import = new JMenuItem("Importar document");

        this.Copy = new JMenuItem("Copy");
        this.Paste = new JMenuItem("Paste");
        this.Cut = new JMenuItem("Cut");
        this.SelectAll = new JMenuItem("Select All");

        //Initializing menus
        this.File = new JMenu("File");
        this.Edit = new JMenu("Edit");
        this.Help = new JMenu("Help");

        //Adding items
        this.File.add(Open);
        this.File.add(NewDoc);
        this.File.add(Save);
        this.File.add(SaveAs);
        this.File.add(Export);
        this.File.add(Import);

        this.Edit.add(Copy);
        this.Edit.add(Paste);
        this.Edit.add(Cut);
        this.Edit.add(SelectAll);

        //Adding menus
        this.MenuBar.add(File);
        this.MenuBar.add(Edit);
        this.MenuBar.add(Help);
        this.setJMenuBar(MenuBar);

        //RadioButtons
        models = new ButtonGroup();
        models.add(Model1);
        models.add(Model2);
        Model1.setSelected(true);
    }

    public void initializeListeners() throws Exception{
        listDocuments.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger() &&
                        listDocuments.locationToIndex(e.getPoint()) == listDocuments.getSelectedIndex()) {
                    popupMenu.show(e.getComponent(),
                            e.getX(), e.getY());
                }
            }
        });

        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Expressió Booleana" ) {
                    String expressio = searchTextField.getText();
                    try {
                        List<String> docs = ictrlPresentacio.iqueryConsultaExpressioBooleana(expressio);
                        if (docs.isEmpty()) {
                            JOptionPane.showMessageDialog(null,"Cap document satisfà l'expressió booleana introduïda.");
                        }
                        else {
                            JDialog aux = new LlistarDocuments(docs,"Expressió Booleana");
                            aux.setVisible(true);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Similaritat" ) {
                    String[] info = searchTextField.getText().split(",");
                    int k = (Integer) nDocumentsSpinner.getValue();
                    int mode;
                    if (Model2.isSelected()) {
                        mode = 1;
                    }
                    else {
                        mode = 0;
                    }
                    try {
                        List<String> docs = ictrlPresentacio.iqueryObtenirKSemblants(info[0],info[1],k,mode);
                        JDialog aux = new LlistarDocuments(docs,"Similaritat");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Rellevància" ) {
                    String info = searchTextField.getText();
                    int k = (Integer) nDocumentsSpinner.getValue();
                    int mode;
                    if (Model2.isSelected()) {
                        mode = 1;
                    }
                    else {
                        mode = 0;
                    }
                    try {
                        List<String> docs = ictrlPresentacio.iqueryObtenirKRellevants(info,k,mode);
                        JDialog aux = new LlistarDocuments(docs,"Rellevància");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Llistar autor" ) {
                    String autor = searchTextField.getText();
                    try {
                        List<String> ti = ictrlPresentacio.iqueryLlistarAutorsPrefix(autor);
                        JDialog aux = new LlistarTitols(ti,autor,"Llistar autor");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
                if(consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Llistar titol" ) {
                    String autor = searchTextField.getText();
                    try {
                        List<String> ti = ictrlPresentacio.iqueryLlistarTitolsAutor(autor);
                        JDialog aux = new LlistarTitols(ti,autor,"Llistar titol");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
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

        ActionListener modificar_contingut = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {
                    String[] a = listDocuments.getSelectedValue().toString().split(",");
                    JDialog aux = new ModificarContingut();
                    aux.setVisible(true);
                    String nou_contingut = ((ModificarContingut) aux).getContingut();
                    boolean accept = ((ModificarContingut) aux).isAccept();
                    try {
                        if (accept) {
                            ictrlPresentacio.iqueryModificarContingut(a[0],a[1],nou_contingut);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
        };
        modificarContingutButton.addActionListener(modificar_contingut);
        pop_modificar_contingut.addActionListener(modificar_contingut);

        ActionListener modificar_autor = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {
                    String[] a = listDocuments.getSelectedValue().toString().split(",");
                    JDialog aux = new ModificarAutor();
                    aux.setVisible(true);
                    String nou_autor = ((ModificarAutor) aux).getNouAutor();
                    String doc = nou_autor+","+a[1];
                    boolean accept = ((ModificarAutor) aux).isAccept();
                    try {
                        if (accept) {
                            ictrlPresentacio.iqueryModificarAutor(a[0],nou_autor,a[1]);
                            ((DefaultListModel) listDocuments.getModel()).setElementAt(doc, listDocuments.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
        };
        modificarAutorButton.addActionListener(modificar_autor);
        pop_modificar_autor.addActionListener(modificar_autor);

        ActionListener modificar_titol = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {
                    String[] a = listDocuments.getSelectedValue().toString().split(",");
                    JDialog aux = new ModificarTitol();
                    aux.setVisible(true);
                    String nou_titol = ((ModificarTitol) aux).getNouTitol();
                    String doc = a[0]+","+nou_titol;
                    boolean accept = ((ModificarTitol) aux).isAccept();
                    try {
                        if (accept) {
                            ictrlPresentacio.iqueryModificarTitol(a[0],a[1],nou_titol);
                            ((DefaultListModel) listDocuments.getModel()).setElementAt(doc, listDocuments.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
        };
        modificarTitolButton.addActionListener(modificar_titol);
        pop_modificar_titol.addActionListener(modificar_titol);

        ActionListener crear_document = new ActionListener() {
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
                        ((DefaultListModel) listDocuments.getModel()).addElement(doc);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    JOptionPane.showMessageDialog(null,"Tornem a mostrar la finestra per a que puguis copiar el progrés que tenies.");
                    aux.setVisible(true);
                }
            }
        };
        crearDocumentButton.addActionListener(crear_document);
        pop_crear_document.addActionListener(crear_document);
        NewDoc.addActionListener(crear_document);
        ActionListener eliminar_document = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {
                    String[] a = listDocuments.getSelectedValue().toString().split(",");
                    JDialog aux = new EliminarDocument();
                    aux.setSize(350,150);
                    aux.setVisible(true);
                    boolean accept = ((EliminarDocument) aux).isAccept();
                    try {
                        if (accept) {
                            ictrlPresentacio.iqueryEliminarDocument(a[0],a[1]);
                            ((DefaultListModel) listDocuments.getModel()).remove(listDocuments.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
        };
        eliminarDocumentButton.addActionListener(eliminar_document);
        pop_eliminar_document.addActionListener(eliminar_document);

        MouseAdapter mostrar_contingut = new MouseAdapter() {
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
                            MostrarContingut mc = new MostrarContingut(a[0],a[1],Contingut);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage());
                        }
                    }
                }
            }
        };
        listDocuments.addMouseListener(mostrar_contingut);

        consultesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Expressió Booleana") {
                    historialButton.setVisible(true);
                } else historialButton.setVisible(false);
                if ((consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Similaritat") ||
                        (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Rellevància"))
                {
                    simi_rellePanel.setVisible(true);
                }
                else simi_rellePanel.setVisible(false);
            }
        });

        historialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<String> exs = ictrlPresentacio.iqueryGetConjuntExpressions();
                JDialog aux = new HistorialExpressionsBooleanes(exs);
                String exSel = ((HistorialExpressionsBooleanes) aux).getExSeleccionada();
                List<String> exsEl = ((HistorialExpressionsBooleanes) aux).getExsEliminades();
                if (!exSel.isEmpty()) {
                    searchTextField.setText(exSel);
                }
                try {
                    if (!exsEl.isEmpty()) {
                        for (String ex: exsEl) { ictrlPresentacio.iqueryEliminarExpressioBooleana(ex); }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        ActionListener importar_document = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Selecciona el archiu que vols importar");
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                chooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter i_filter_txt = new FileNameExtensionFilter("txt", "txt");
                FileNameExtensionFilter i_filter_xml = new FileNameExtensionFilter("xml", "xml");
                FileNameExtensionFilter i_filter_jamp = new FileNameExtensionFilter("jamp", "jamp");
                chooser.addChoosableFileFilter(i_filter_txt);
                chooser.addChoosableFileFilter(i_filter_xml);
                chooser.addChoosableFileFilter(i_filter_jamp);
                if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                    String addr = chooser.getSelectedFile().toString();
                    String format = chooser.getFileFilter().getDescription();
                    try {
                        System.out.println(addr);
                        System.out.println(format);
                        ictrlPresentacio.icarregarDocument(addr,format);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.toString());
                    }
                }
            }
        };
        importarDocumentButton.addActionListener(importar_document);
        pop_importar_document.addActionListener(importar_document);
        Import.addActionListener(importar_document);

        ActionListener exportar_document = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {
                    String[] doc = listDocuments.getSelectedValue().toString().split(",");
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setDialogTitle("Selecciona el directori on desar el document");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter e_filter_txt = new FileNameExtensionFilter("txt", "txt");
                    FileNameExtensionFilter e_filter_xml = new FileNameExtensionFilter("xml", "xml");
                    FileNameExtensionFilter e_filter_jamp = new FileNameExtensionFilter("jamp", "jamp");
                    chooser.addChoosableFileFilter(e_filter_txt);
                    chooser.addChoosableFileFilter(e_filter_xml);
                    chooser.addChoosableFileFilter(e_filter_jamp);
                    if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                        String addr = chooser.getSelectedFile().toString();
                        String format = chooser.getFileFilter().getDescription();
                        try {
                            System.out.println(addr);
                            ictrlPresentacio.iqueryExportarDocument(doc[0],doc[1],format,addr);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,ex.toString());
                        }
                    }
                }
            }
        };

        exportarDocumentButton.addActionListener(exportar_document);
        pop_exportar_document.addActionListener(exportar_document);
        Export.addActionListener(exportar_document);

        exportarDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {
                    String[] doc = listDocuments.getSelectedValue().toString().split(",");
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setDialogTitle("Selecciona el directori on desar el document");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter e_filter_txt = new FileNameExtensionFilter("txt", "txt");
                    FileNameExtensionFilter e_filter_xml = new FileNameExtensionFilter("xml", "xml");
                    FileNameExtensionFilter e_filter_jamp = new FileNameExtensionFilter("jamp", "jamp");
                    chooser.addChoosableFileFilter(e_filter_txt);
                    chooser.addChoosableFileFilter(e_filter_xml);
                    chooser.addChoosableFileFilter(e_filter_jamp);
                    if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                        String addr = chooser.getSelectedFile().toString();
                        String format = chooser.getFileFilter().getDescription();
                        try {
                            ictrlPresentacio.iqueryExportarDocument(doc[0],doc[1],format,addr);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,ex.toString());
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
        historialButton.setVisible(false);
        initializeMenuBar();
        initializeListeners();
    }

}