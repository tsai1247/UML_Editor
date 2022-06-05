package Menu;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

public class MenuItem extends JMenuItem{
    public MenuItem(String name){
        super(name);
    }

    public MenuItem(String name, ActionListener listener){
        super(name);
        this.addActionListener(listener);
    }
}
