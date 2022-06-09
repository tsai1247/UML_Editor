package Workspace;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.*;
import Workspace.Menu.MenuArea;
import Workspace.Mode.ModeArea;
import Workspace.Canvas.CanvasArea;


public class Main {
    private static MenuArea menuarea = MenuArea.getInstance();
    private static ModeArea modearea = ModeArea.getInstance();
    private static CanvasArea canvasarea = CanvasArea.getInstance();

    private static JFrame frame;
    private static JPanel panel;
    private static int width = 800, height = 500;

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("UML Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(width, height));

        //Add MenuArea to frame
        frame.setJMenuBar(menuarea);
        
        //Add JPanel to frame
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        //Add CanvasArea to panel
        panel.add(canvasarea, BorderLayout.CENTER);

        //Add ModeArea to panel
        panel.add(modearea, BorderLayout.WEST);

        //Show the window.
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}