package Workspace.Menu;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.util.Vector;

import Workspace.Canvas.CanvasArea;
import Workspace.Canvas.Shape.Composite;
import Workspace.Canvas.Shape.Shape;

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
                    Vector<Shape> selectedShapes = CanvasArea.getInstance().getSelectedShapes();
                    if(selectedShapes.size() > 1) {
                        Composite composite = new Composite(selectedShapes);
                        CanvasArea.getInstance().getShapes().add(composite);
                    }
                    CanvasArea.getInstance().repaint();
                }
            }),
            new MenuItem("Ungroup", new ActionListener(){
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
                    CanvasArea.getInstance().repaint();
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
