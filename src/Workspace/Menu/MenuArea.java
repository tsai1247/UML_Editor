package Workspace.Menu;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;

public class MenuArea extends JMenuBar{
    private static MenuArea instance = null;
    private MenuArea(){
        super();
        addMenuFolder(new MenuFolder("File", new MenuItem[]{
        }));
        
        addMenuFolder(new MenuFolder("Edit", new MenuItem[]{
            new MenuItem("Group", new ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent e){
                    System.out.println("Group");
                }
            }),
            new MenuItem("Ungroup", new ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent e){
                    System.out.println("Ungroup");
                }
            }),
            new MenuItem("Change Name", new ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent e){
                    System.out.println("Change Name");
                }
            }),
        }));
    }

    public static MenuArea getInstance(){
        if(instance == null){
            instance = new MenuArea();
        }
        return instance;
    }
    
    public void addMenuFolder(MenuFolder folder){
        this.add(folder);
    }
}
