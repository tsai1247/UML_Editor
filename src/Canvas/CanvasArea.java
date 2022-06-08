package Canvas;
import javax.swing.JPanel;

import Canvas.Line.AssociationLine;
import Canvas.Line.CompositionLine;
import Canvas.Line.GeneralizationLine;
import Canvas.Line.Line;
import Canvas.Shape.*;
import Canvas.Shape.Class;
import Mode.ModeArea;

import java.awt.event.*;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Point;

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
    
    private Vector<Line> lines = new Vector<Line>();
    private Shape shape = new SimpleShape();
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    private Point startPoint = null;
    private Point endPoint = null;

    public Shape getHoveredShape(Point point) {
        return shape.getHoveredShape(point);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        shape.draw(g);
        for(Line line : lines) {
            line.draw(g);
        }
    }

    public void Clicked(int x, int y) {
        switch(ModeArea.getInstance().getCurrentMode()) {
            case SELECT:
                break;
            case USECASE:
                setShape(new UseCase(shape, x, y));
                break;
            case CLASS:
                setShape(new Class(shape, x, y));
                break;
            default:
                break;
        }

        this.repaint();
    }
    public void Released(int x, int y) {
        Shape startShape, endShape;
        switch(ModeArea.getInstance().getCurrentMode()) {
            case SELECT:
                break;
            case ASSOCIATION:
                endPoint = new Point(x, y);
                startShape = getHoveredShape(startPoint);
                endShape = getHoveredShape(endPoint);
                if(startShape != null && endShape != null) {
                    lines.add(
                        new AssociationLine(
                            startShape, startShape.getHoveredPort(startPoint),
                            endShape, endShape.getHoveredPort(endPoint)
                        )
                    );
                }
                break;
            case COMPOSITION:
                endPoint = new Point(x, y);
                startShape = getHoveredShape(startPoint);
                endShape = getHoveredShape(endPoint);
                if(startShape != null && endShape != null) {
                    lines.add(
                        new CompositionLine(
                            startShape, startShape.getHoveredPort(startPoint),
                            endShape, endShape.getHoveredPort(endPoint)
                        )
                    );
                }
                break;
            case GENERALIZATION:
                endPoint = new Point(x, y);
                startShape = getHoveredShape(startPoint);
                endShape = getHoveredShape(endPoint);
                if(startShape != null && endShape != null) {
                    lines.add(
                        new GeneralizationLine(
                            startShape, startShape.getHoveredPort(startPoint),
                            endShape, endShape.getHoveredPort(endPoint)
                        )
                    );
                }
                break;
            default:
                break;
        }
        this.repaint();
    }

    public void Pressed(int x, int y) {
        switch(ModeArea.getInstance().getCurrentMode()) {
            case SELECT:
            case ASSOCIATION:
            case COMPOSITION:
            case GENERALIZATION:
                startPoint = new Point(x, y);
                break;
            default:
                break;
        }
        this.repaint();
    }
    public void Moved(int x, int y) {
        // TODO
        this.repaint();
    }
}
