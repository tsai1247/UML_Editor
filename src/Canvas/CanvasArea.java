package Canvas;
import javax.swing.JPanel;

import Canvas.Shape.*;
import Canvas.Shape.Class;

import java.awt.event.*;
import java.awt.Graphics;

public class CanvasArea extends JPanel{
    private static CanvasArea instance = null;
    public static CanvasArea getInstance() {
        if(instance == null) {
            instance = new CanvasArea();
        }
        return instance;
    }
    private CanvasArea() {
        super();
        this.setLayout(null);

        // addMouseListener
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Clicked(e.getX(), e.getY());
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                Pressed(e.getX(), e.getY());
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                Released(e.getX(), e.getY());
            }
            @Override
            public void mouseMoved(MouseEvent e)
            {
                Moved(e.getX(), e.getY());
            }
        });
    }

    private Shape shape = new SimpleShape();
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        shape.draw(g);
    }

    public void Clicked(int x, int y) {
        this.repaint();
    }
    public void Released(int x, int y) {
        // TODO
        this.repaint();
    }
    public void Pressed(int x, int y) {
        // TODO
        this.repaint();
    }
    public void Moved(int x, int y) {
        // TODO
        this.repaint();
    }
}
