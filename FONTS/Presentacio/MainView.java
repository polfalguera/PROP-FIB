package FONTS.Presentacio;


import FONTS.Domini.Document;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.util.List;
import java.util.Set;
/**
 * Aquesta vista és l’encarregada de mostrar la pantalla principal
 * Aqui podrem fer de les funcionalitats principals del nostre programa com per exemple:
 * crear un nou document, carregar-ne un, eliminar-ne un, modificar el titol, etc...
 */

public class MainView extends JFrame {
    /** Instancai del controlador de presentacio */
    private ControladorPresentacio ictrlPresentacio;
    /** Panell on s'inclou tots els elements de la finestra princiapl*/
    private JPanel mainPanel;
    /** Text que indica la barra de cerca */
    private JLabel searchLabel;
    /** Input del usuari on fara les consultes */
    private JTextField searchTextField;
    /** Element per seleccionar quin tipus de consulta es */
    private JComboBox consultesComboBox;
    /** El popupMenu que apareix al clicar boto dret a la llista de documents */
    private JPopupMenu popupMenu;
    /** Element del menu del popupMenu */
    private JMenuItem pop_crear_document;
    /** Element del menu del popupMenu */
    private JMenuItem pop_modificar_autor;
    /** Element del menu del popupMenu */
    private JMenuItem pop_modificar_titol;
    /** Element del menu del popupMenu */
    private JMenuItem pop_modificar_contingut;
    /** Element del menu del popupMenu */
    private JMenuItem pop_eliminar_document;
    /** Element del menu del popupMenu */
    private JMenuItem pop_exportar_document;
    /** Element del menu del popupMenu */
    private JMenuItem pop_importar_document;

    /** Llista que representa tots el documents que tenim a la nostra aplicacio*/
    private JList listDocuments;
    /** Boto per crear un document*/
    private JButton crearDocumentButton;
    /** Boto per modificar l'autor d'un document*/
    private JButton modificarAutorButton;
    /** Boto per modificar el titol d'un document*/
    private JButton modificarTitolButton;
    /** Boto per eliminar un document*/
    private JButton eliminarDocumentButton;
    /** Boto per modificar el contingut d'un document*/
    private JButton modificarContingutButton;
    /** Boto per mostrar el historial d'expressions booleanes consultades*/
    private JButton historialButton;
    /** Boto per importar d'un document*/
    private JButton importarDocumentButton;
    /** Boto per exportar d'un document*/
    private JButton exportarDocumentButton;
    /** ELement que mostra quans documents vols cercar*/
    private JSpinner nDocumentsSpinner;
    /** Element per fer servir una cerca amb el model1*/
    private JRadioButton Model1;
    /** Element per fer servir una cerca amb el model2*/
    private JRadioButton Model2;
    /** Conjunts de radioButtons*/
    private ButtonGroup models;
    /** Panel es mostra quan el tipus de cerca es similarita o rellevancia*/
    private JPanel simi_rellePanel;
    /** Text on es mostra el numeor de documents*/
    private JLabel nDocumentsLabel;
    /** Input perque l'usuari pugui introduir el titol d'un document*/
    private JTextField titolTextField;
    /** Representa la barra de menu*/
    private JMenuBar MenuBar;
    /** Element de la barra de menu*/
    private JMenu File;
    /** Element de la barra de menu*/
    private JMenu Edit;
    /** Element de la barra de menu*/
    private JMenu Help;
    /** Element del menu de File*/
    private JMenuItem NewDoc;
    /** Element del menu de File*/
    private JMenuItem Export;
    /** Element del menu de File*/
    private JMenuItem Import;
    /** Element del menu de Edit*/
    private JMenuItem Copy;
    /** Element del menu de Edit*/
    private JMenuItem Paste;
    /** Element del menu de Edit*/
    private JMenuItem Cut;
    /** Element del menu de Edit*/
    private JMenuItem SelectAll;
    /** Text informatiu*/
    private TextPrompt placeholder;
    /** Text informatiu*/
    private TextPrompt placeholder_titol;
    private String expressioAmodificar = "";
    /**
     * Inicialitza els components de la Vista Principal.
     */
    public void initialize() {

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
        this.NewDoc = new JMenuItem("New document");
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
        this.File.add(NewDoc);
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

        List<Document> docs = ictrlPresentacio.iqueryGetCjtDocuments();
        for (Document d : docs) {
            ((DefaultListModel) listDocuments.getModel()).addElement(d.getAutor() + "," + d.getTitol());
        }

        //Set del placeholders
        historialButton.setVisible(false);

        placeholder = new TextPrompt("Introdueix un autor", searchTextField);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);

        simi_rellePanel.setVisible(true);
        historialButton.setVisible(false);
        titolTextField.setVisible(true);

        placeholder_titol = new TextPrompt("Introdueix un titol", titolTextField);
        placeholder_titol.changeAlpha(0.75f);
        placeholder_titol.changeStyle(Font.ITALIC);
    }

    /**
     * Assigna els listeners als components corresponents.
     */
    public void initializeListeners() throws Exception {
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
        titolTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Similaritat") {
                    expressioAmodificar = "";
                    int k = (Integer) nDocumentsSpinner.getValue();
                    int mode;
                    if (Model2.isSelected()) {
                        mode = 1;
                    } else {
                        mode = 0;
                    }
                    try {
                        String autor = searchTextField.getText();
                        String titol = titolTextField.getText();
                        List<String> docs = ictrlPresentacio.iqueryObtenirKSemblants(autor, titol, k, mode);
                        JDialog aux = new LlistarDocuments(docs, "Similaritat");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Expressió Booleana") {
                    String expressio = searchTextField.getText();
                    try {
                        List<String> docs = ictrlPresentacio.iqueryConsultaExpressioBooleana(expressio);
                        if (!expressioAmodificar.equals("")) {
                            ictrlPresentacio.iqueryEliminarExpressioBooleana(expressioAmodificar);
                            expressioAmodificar = "";
                        }
                        if (docs.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Cap document satisfà l'expressió booleana introduïda.");
                        } else {
                            JDialog aux = new LlistarDocuments(docs, "Expressió Booleana");
                            aux.setVisible(true);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Similaritat") {
                    expressioAmodificar = "";
                    int k = (Integer) nDocumentsSpinner.getValue();
                    int mode;
                    if (Model2.isSelected()) {
                        mode = 1;
                    } else {
                        mode = 0;
                    }
                    try {
                        String autor = searchTextField.getText();
                        String titol = titolTextField.getText();
                        List<String> docs = ictrlPresentacio.iqueryObtenirKSemblants(autor, titol, k, mode);
                        JDialog aux = new LlistarDocuments(docs, "Similaritat");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Rellevància") {
                    expressioAmodificar = "";
                    String info = searchTextField.getText();
                    int k = (Integer) nDocumentsSpinner.getValue();
                    int mode;
                    if (Model2.isSelected()) {
                        mode = 1;
                    } else {
                        mode = 0;
                    }
                    try {
                        List<String> docs = ictrlPresentacio.iqueryObtenirKRellevants(info, k, mode);
                        JDialog aux = new LlistarDocuments(docs, "Rellevància");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Llistar autor") {
                    expressioAmodificar = "";
                    String autor = searchTextField.getText();
                    try {
                        List<String> ti = ictrlPresentacio.iqueryLlistarAutorsPrefix(autor);
                        JDialog aux = new LlistarTitols(ti, autor, "Llistar autor");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Llistar titol") {
                    expressioAmodificar = "";
                    String autor = searchTextField.getText();
                    try {
                        List<String> ti = ictrlPresentacio.iqueryLlistarTitolsAutor(autor);
                        JDialog aux = new LlistarTitols(ti, autor, "Llistar titol");
                        aux.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
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
                    JDialog aux = new ModificarContingut();
                    aux.setVisible(true);
                    String nou_contingut = ((ModificarContingut) aux).getContingut();
                    boolean accept = ((ModificarContingut) aux).isAccept();
                    try {
                        if (accept) {
                            List<String> ne = ictrlPresentacio.iqueryGetAutorTitolIndex(listDocuments.getSelectedIndex());
                            String autor1 = ne.get(0);
                            String titol1 = ne.get(1);
                            ictrlPresentacio.iqueryModificarContingut(autor1, titol1, nou_contingut);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        };
        modificarContingutButton.addActionListener(modificar_contingut);
        pop_modificar_contingut.addActionListener(modificar_contingut);

        ActionListener modificar_autor = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {

                    JDialog aux = new ModificarAutor();
                    aux.setVisible(true);
                    String nou_autor = ((ModificarAutor) aux).getNouAutor();
                    boolean accept = ((ModificarAutor) aux).isAccept();
                    try {
                        if (accept) {
                            List<String> ne = ictrlPresentacio.iqueryGetAutorTitolIndex(listDocuments.getSelectedIndex());
                            String autor1 = ne.get(0);
                            String titol1 = ne.get(1);
                            String doc = nou_autor + "  ,   " + titol1;
                            ictrlPresentacio.iqueryModificarAutor(autor1, nou_autor, titol1);
                            ((DefaultListModel) listDocuments.getModel()).setElementAt(doc, listDocuments.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        };
        modificarAutorButton.addActionListener(modificar_autor);
        pop_modificar_autor.addActionListener(modificar_autor);

        ActionListener modificar_titol = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedIndex() != -1) {
                    JDialog aux = new ModificarTitol();
                    aux.setVisible(true);
                    String nou_titol = ((ModificarTitol) aux).getNouTitol();

                    boolean accept = ((ModificarTitol) aux).isAccept();
                    try {
                        if (accept) {
                            List<String> ne = ictrlPresentacio.iqueryGetAutorTitolIndex(listDocuments.getSelectedIndex());
                            String autor1 = ne.get(0);
                            String titol1 = ne.get(1);
                            String doc = autor1 + "    ,   " + nou_titol;
                            ictrlPresentacio.iqueryModificarTitol(autor1, titol1, nou_titol);
                            ((DefaultListModel) listDocuments.getModel()).setElementAt(doc, listDocuments.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
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
                        ictrlPresentacio.iqueryCrearDocument(autor, titol, contingut);
                        String doc = autor + "   ,   " + titol;
                        ((DefaultListModel) listDocuments.getModel()).addElement(doc);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Tornem a mostrar la finestra per a que puguis copiar el progrés que tenies.");
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
                    JDialog aux = new EliminarDocument();
                    aux.setSize(350, 150);
                    aux.setVisible(true);
                    boolean accept = ((EliminarDocument) aux).isAccept();
                    try {
                        if (accept) {
                            List<String> ne = ictrlPresentacio.iqueryGetAutorTitolIndex(listDocuments.getSelectedIndex());
                            String autor1 = ne.get(0);
                            String titol1 = ne.get(1);
                            String doc = autor1 + "    ,   " + titol1;
                            ictrlPresentacio.iqueryEliminarDocument(autor1, titol1);
                            ((DefaultListModel) listDocuments.getModel()).remove(listDocuments.getSelectedIndex());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
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
                        try {
                            List<String> ne = ictrlPresentacio.iqueryGetAutorTitolIndex(index);
                            String autor1 = ne.get(0);
                            String titol1 = ne.get(1);
                            String Contingut = ictrlPresentacio.iqueryGetContingutDocument(autor1, titol1);
                            MostrarContingut mc = new MostrarContingut(autor1, titol1, Contingut);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
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

                    placeholder.setText("Introdueix una expressió booleana. P. ex. {p1 p2 p3} & (“hola adéu” | pep) & !joan");
                    historialButton.setVisible(true);
                    simi_rellePanel.setVisible(false);
                    titolTextField.setVisible(false);
                } else if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Similaritat") {
                    placeholder.setText("Introdueix un autor");
                    placeholder_titol.setText("Introdueix un autor");
                    simi_rellePanel.setVisible(false);
                    historialButton.setVisible(false);
                    titolTextField.setVisible(true);
                    simi_rellePanel.setVisible(true);
                } else if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Llistar titol") {
                    placeholder.setText("Introdueix un autor. P. ex. Toni");
                    simi_rellePanel.setVisible(false);
                    titolTextField.setVisible(false);
                    historialButton.setVisible(false);
                } else if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Llistar autor") {
                    placeholder.setText("Introdueix un prefix. P. ex. T");
                    simi_rellePanel.setVisible(false);
                    titolTextField.setVisible(false);
                    historialButton.setVisible(false);
                } else if (consultesComboBox.getItemAt(consultesComboBox.getSelectedIndex()) == "Rellevància") {
                    placeholder.setText("Introdueix les paraules que han de contenir els documents. P. ex. taula cadira finestra");
                    simi_rellePanel.setVisible(false);
                    titolTextField.setVisible(false);
                    historialButton.setVisible(false);
                    simi_rellePanel.setVisible(true);
                }
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
                    boolean modificada = ((HistorialExpressionsBooleanes) aux).isAccept();
                    if (modificada) {
                        expressioAmodificar = exSel;
                    }
                }
                try {
                    if (!exsEl.isEmpty()) {
                        for (String ex : exsEl) {
                            ictrlPresentacio.iqueryEliminarExpressioBooleana(ex);
                        }
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
                chooser.setCurrentDirectory(new File("."));
                chooser.setDialogTitle("Selecciona el archiu que vols importar");
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.setMultiSelectionEnabled(true);

                chooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter i_filter_txt = new FileNameExtensionFilter("txt", "txt");
                FileNameExtensionFilter i_filter_xml = new FileNameExtensionFilter("xml", "xml");
                FileNameExtensionFilter i_filter_jamp = new FileNameExtensionFilter("jamp", "jamp");
                chooser.addChoosableFileFilter(i_filter_txt);
                chooser.addChoosableFileFilter(i_filter_xml);
                chooser.addChoosableFileFilter(i_filter_jamp);
                if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                    //String addr = chooser.getSelectedFile().toString();
                    File[] files = chooser.getSelectedFiles();
                    String format = chooser.getFileFilter().getDescription();
                    try {
                        for (File f : files) {
                            String addr = f.toString();
                            List<String> autorItitol = ictrlPresentacio.icarregarDocument(addr, format);
                            String doc = autorItitol.get(0) + "    ,   " + autorItitol.get(1);
                            ((DefaultListModel) listDocuments.getModel()).addElement(doc);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
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
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File("."));
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
                            List<String> ne = ictrlPresentacio.iqueryGetAutorTitolIndex(listDocuments.getSelectedIndex());
                            String autor1 = ne.get(0);
                            String titol1 = ne.get(1);
                            ictrlPresentacio.iqueryExportarDocument(autor1, titol1, format, addr);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.toString());
                        }
                    }
                }
            }
        };

        exportarDocumentButton.addActionListener(exportar_document);
        pop_exportar_document.addActionListener(exportar_document);
        Export.addActionListener(exportar_document);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ictrlPresentacio.iqueryTancarPrograma();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    /**
     * Constructora de la finestra principal.
     * @param title text que mostra a dalt de la finestra principal
     * @param pCtrlPresentacio representa la instancia de CtrlPresentacio
     * */
    public MainView(String title, ControladorPresentacio pCtrlPresentacio) throws Exception {
        super(title);
        ictrlPresentacio = pCtrlPresentacio;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(750, 500);
        setLocationRelativeTo(null);
        initialize();
        initializeListeners();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(12, 5, new Insets(15, 0, 0, 0), -1, -1));
        mainPanel.setBackground(new Color(-4077363));
        searchLabel = new JLabel();
        searchLabel.setText("Search:");
        mainPanel.add(searchLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchTextField = new JTextField();
        searchTextField.setText("");
        mainPanel.add(searchTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        consultesComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Similaritat");
        defaultComboBoxModel1.addElement("Expressió Booleana");
        defaultComboBoxModel1.addElement("Rellevància");
        defaultComboBoxModel1.addElement("Llistar titol");
        defaultComboBoxModel1.addElement("Llistar autor");
        consultesComboBox.setModel(defaultComboBoxModel1);
        mainPanel.add(consultesComboBox, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(1, 0, 11, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        listDocuments = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        listDocuments.setModel(defaultListModel1);
        listDocuments.putClientProperty("List.isFileList", Boolean.FALSE);
        scrollPane1.setViewportView(listDocuments);
        modificarTitolButton = new JButton();
        modificarTitolButton.setText("ModificarTitol");
        mainPanel.add(modificarTitolButton, new GridConstraints(11, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modificarAutorButton = new JButton();
        modificarAutorButton.setText("ModificarAutor");
        mainPanel.add(modificarAutorButton, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarDocumentButton = new JButton();
        eliminarDocumentButton.setText("EliminarDocument");
        mainPanel.add(eliminarDocumentButton, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modificarContingutButton = new JButton();
        modificarContingutButton.setText("Modificar Contingut");
        mainPanel.add(modificarContingutButton, new GridConstraints(10, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        historialButton = new JButton();
        historialButton.setText("Historial");
        mainPanel.add(historialButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("-----------------------------------");
        mainPanel.add(label1, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        crearDocumentButton = new JButton();
        crearDocumentButton.setText("CrearDocument");
        mainPanel.add(crearDocumentButton, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("-----------------------------------");
        mainPanel.add(label2, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exportarDocumentButton = new JButton();
        exportarDocumentButton.setText("ExportarDocument");
        mainPanel.add(exportarDocumentButton, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        importarDocumentButton = new JButton();
        importarDocumentButton.setText("ImportarDocument");
        mainPanel.add(importarDocumentButton, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        simi_rellePanel = new JPanel();
        simi_rellePanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(simi_rellePanel, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nDocumentsSpinner = new JSpinner();
        simi_rellePanel.add(nDocumentsSpinner, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nDocumentsLabel = new JLabel();
        nDocumentsLabel.setText("Nº documents");
        simi_rellePanel.add(nDocumentsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Model1 = new JRadioButton();
        Model1.setText("Model1");
        simi_rellePanel.add(Model1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Model2 = new JRadioButton();
        Model2.setText("Model2");
        simi_rellePanel.add(Model2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("-----------------------------------");
        mainPanel.add(label3, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titolTextField = new JTextField();
        mainPanel.add(titolTextField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchLabel.setLabelFor(scrollPane1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
