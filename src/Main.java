import javax.swing.*;
import Menu.MenuArea;

public class Main {
    
    public static MenuArea menuarea = MenuArea.getInstance();
    public static JFrame frame;


    private static void createAndShowGUI() {
    //#region JFrame head
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame("UML Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

    //#endregion

        frame.setJMenuBar(menuarea);


    //#region JFrame tail
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 600);
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