package Workspace.Menu;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.util.Vector;

import Workspace.Canvas.CanvasArea;
import Workspace.Canvas.Shape.Composite;
import Workspace.Canvas.Shape.Shape;

public class MenuArea extends JMenuBar{
    // singleton pattern
    private static MenuArea instance = null;
    public static MenuArea getInstance(){
        if(instance == null){
            instance = new MenuArea();
        }
        return instance;
    }
    private MenuArea(){
        super();
        setComponents();
    }


    MenuFolder File, Edit;
    MenuItem group, ungroup, changeName;

    private void setComponents(){
        // create menu
        // file menu
        File = new MenuFolder("File", new MenuItem[]{});

        // items in edit menu
        group = new MenuItem("Group", new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                System.out.println("Group");
                Vector<Shape> selectedShapes = CanvasArea.getInstance().getSelectedShapes();
                if(selectedShapes.size() > 1) {
                    Composite composite = new Composite(selectedShapes);
                    CanvasArea.getInstance().getShapes().add(composite);
                }
                
                group.setEnabled(false);
                ungroup.setEnabled(true);
                CanvasArea.getInstance().repaint();
            }
        });
        ungroup = new MenuItem("Ungroup", new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                System.out.println("Ungroup");
                Vector<Shape> selectedShapes = CanvasArea.getInstance().getSelectedShapes();
                for(Shape shape : selectedShapes) {
                    CanvasArea.getInstance().getShapes().remove(shape);
                    for(Shape child : shape.getShapes()) {
                        child.setSelected(true);
                        CanvasArea.getInstance().getShapes().add(child);
                    }
                }
                ungroup.setEnabled(false);
                group.setEnabled(true);
                CanvasArea.getInstance().repaint();
            }
        });
        changeName = new MenuItem("Change Name", new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                System.out.println("Change Name");
                RenameWindow renameWindow;

                Vector<Shape> selectedShapes = CanvasArea.getInstance().getSelectedShapes();
                if(selectedShapes.size() == 1) {
                    renameWindow = new RenameWindow(selectedShapes.get(0).getName());
                }
                else{
                    renameWindow = new RenameWindow("");
                }
                renameWindow.ShowWindow();
            }
        });
        
        // edit menu
        Edit = new MenuFolder("Edit", new MenuItem[]{group, ungroup, changeName});

        // all items is initialized disabled
        group.setEnabled(false);
        ungroup.setEnabled(false);
        changeName.setEnabled(false);

        this.add(File);
        this.add(Edit);
    }

    public enum Option
    {
        GROUP,
        UNGROUP,
        CHANGE_NAME
    }
    public void setEnabled(Option option, boolean enabled) {
        switch(option) {
            case GROUP:
                group.setEnabled(enabled);
                break;
            case UNGROUP:
                ungroup.setEnabled(enabled);
                break;
            case CHANGE_NAME:
                changeName.setEnabled(enabled);
                break;
            default:
                break;
        }
    }


}
