package Workspace.Menu;
import javax.swing.JMenu;

public class MenuFolder extends JMenu {
        public MenuFolder(String name, MenuItem[] items){
            super(name);
            for(MenuItem item : items){
                this.add(item);
            }
        }
        
        public void addMenuItem(MenuItem item){
            this.add(item);
        }
        
        public void addMenuFolder(MenuFolder folder){
            this.add(folder);
        }
        
           
}
