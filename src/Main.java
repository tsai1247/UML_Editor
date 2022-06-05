import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.*;
import Menu.MenuArea;
import Mode.ModeArea;
import Canvas.CanvasArea;


public class Main {
    private static MenuArea menuarea = MenuArea.getInstance();
    private static ModeArea modearea = ModeArea.getInstance();
    private static CanvasArea canvasarea = CanvasArea.getInstance();

    private static JFrame frame;
    private static JPanel panel;
    private static int width = 800, height = 600;
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }

    private static void createAndShowGUI() {
    // Create and set up the window.
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("UML Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setPreferredSize(new Dimension(width, height));
        
    //#endregion

    //#region Add MenuArea to frame
        frame.setJMenuBar(menuarea);
    //#endregion
    
    //#region Add JPanel to frame
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(0, 0, width, height);
        frame.getContentPane().add(panel);
    //#endregion

    //#region Add CanvasArea to panel
    panel.add(canvasarea, BorderLayout.CENTER);
    //#endregion

    //#region Add ModeArea to panel
        panel.add(modearea, BorderLayout.WEST);
    //#endregion


    //#region Show the window.
        frame.pack();
        frame.setVisible(true);
    //#endregion

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}