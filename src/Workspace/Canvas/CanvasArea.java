package Workspace.Canvas;
import javax.swing.JPanel;

import Workspace.Canvas.Line.AssociationLine;
import Workspace.Canvas.Line.CompositionLine;
import Workspace.Canvas.Line.GeneralizationLine;
import Workspace.Canvas.Line.Line;
import Workspace.Canvas.Shape.*;
import Workspace.Canvas.Shape.Class;
import Workspace.Mode.ModeArea;

import java.awt.event.*;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Point;

public class CanvasArea extends JPanel{
    enum Mode {
        SELECT,
        ASSOCIATION,
        COMPOSITION,
        GENERALIZATION,
        CLASS,
        USECASE,
        MOVE,
        NONE
    }
    protected Mode mode = Mode.NONE;
    private Mode setMode(Mode mode) {
        this.mode = mode;
        System.out.println("mode: " + mode);
        return mode;
    }
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
        if(shape.getWidth() == 0 || shape.getHeight() == 0) {
            return;
        }
        this.shape = shape;
    }
    public Shape getShape() {
        return shape;
    }

    private Point startPoint = null, endPoint = null;

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
                var hoveredShape = getHoveredShape(new Point(x, y));
                if(hoveredShape != null) {
                    hoveredShape.setSelected(true);
                    System.out.println("selected: " + hoveredShape.getName());
                }
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
                endPoint = new Point(x, y);
                if(mode == Mode.SELECT)
                    shape.setSelected(startPoint, endPoint);
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
                startPoint = new Point(x, y);
                if(getHoveredShape(startPoint) != null && getHoveredShape(startPoint).isSelected()) {
                    setMode(Mode.MOVE);
                }
                else if(getHoveredShape(startPoint) != null)
                {
                    shape.setAllShapesBelowSelected(false);
                    setMode(Mode.NONE);
                }
                else
                {
                    shape.setAllShapesBelowSelected(false);
                    setMode(Mode.SELECT);
                }
                break;
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
        this.repaint();
    }
}
