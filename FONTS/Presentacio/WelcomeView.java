package FONTS.Presentacio;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JFrame {
    private ControladorPresentacio iCtrlPresentacio;
    private JPanel welcomePanel;
    private JLabel textBenvinguda;
    private JButton iniciarButton;
    private JLabel empty;
    private JButton sortirButton;
    public void initializeListeners() {
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iCtrlPresentacio.carregarVistaPrincipal();
            }
        });
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public WelcomeView(String title, ControladorPresentacio pCtrlPresentacio) {
        super(title);
        iCtrlPresentacio = pCtrlPresentacio;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(welcomePanel);
        this.pack();
        this.setSize(750,500);

        initializeListeners();
    }
}
