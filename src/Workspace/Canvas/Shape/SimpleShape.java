package Workspace.Canvas.Shape;
import java.awt.Graphics;

public class SimpleShape extends Shape {
    public SimpleShape() {
        super();
    }

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public void setAllShapesBelowSelected(boolean isSelected) {
    }

    @Override
    public boolean isSelected() {
        return false;
    }
    
}
