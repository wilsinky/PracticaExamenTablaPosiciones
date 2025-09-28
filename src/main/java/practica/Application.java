package practica;

import practica.logic.Service;
import practica.presentation.tablaPosiciones.Controller;
import practica.presentation.tablaPosiciones.Model;
import practica.presentation.tablaPosiciones.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error al iniciar" + e.getMessage());
        }

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);

        JFrame window = new JFrame();
        window.setSize(600,400);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Examen");
        window.setContentPane(view.getPanel());

        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);
        tabbedPane.addTab("Tablero", view.getPanel());

        window.setLocationRelativeTo(null);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });

        window.setVisible(true);
    }

    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
}