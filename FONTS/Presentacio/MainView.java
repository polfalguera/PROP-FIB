package FONTS.Presentacio;

import javax.swing.*;

public class MainView extends JFrame {

    JMenu menu, submenu;
    JMenuItem i1, i2, i3, i4, i5;

    private JMenuItem cut,copy,paste,selectAll;
    private final ControladorPresentacio iCtrlPresentation;
    private JPanel panel1;
    private JMenuBar MenuBar;
    private JMenu MyMenu;


    /**
     * Inicialitza els components de la Vista Principal i assigna els listeners corresponents.
     */
    private void initializeComponents() {
        initializeMenuBar();
    }

    /**
     * Inicialitza els botons del menú de la Vista Principal.
     */
    private void initializeMenuBar() {

        JFrame f= new JFrame("Menu and MenuItem Example");
        JMenuBar mb=new JMenuBar();
        menu=new JMenu("Menu");
        submenu=new JMenu("Sub Menu");
        i1=new JMenuItem("Item 1");
        i2=new JMenuItem("Item 2");
        i3=new JMenuItem("Item 3");
        i4=new JMenuItem("Item 4");
        i5=new JMenuItem("Item 5");
        menu.add(i1); menu.add(i2); menu.add(i3);
        submenu.add(i4); submenu.add(i5);
        menu.add(submenu);
        mb.add(menu);
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


}
