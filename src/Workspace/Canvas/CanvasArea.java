package Workspace.Canvas;
import javax.swing.JPanel;

import Workspace.Canvas.Line.AssociationLine;
import Workspace.Canvas.Line.CompositionLine;
import Workspace.Canvas.Line.GeneralizationLine;
import Workspace.Canvas.Line.Line;
import Workspace.Canvas.Shape.*;
import Workspace.Canvas.Shape.Class;
import Workspace.Menu.MenuArea;
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
            public void mouseDragged(MouseEvent e)
            {
                Moved(e.getX(), e.getY());
            }
        });
    }
    
    private Vector<Line> lines = new Vector<Line>();
    private Vector<Shape> shapes = new Vector<Shape>();

    private Point startPoint = null, endPoint = null;

    public Shape getHoveredShape(Point point) {
        for(int i=shapes.size()-1; i>=0; i--) {
            Shape shape = shapes.get(i);
            if(shape.isHovered(point)) {
                return shape;
            }
        }
        return null;
    }
    public Vector<Shape> getShapes() {
        return shapes;
    }
    public Vector<Shape> getSelectedShapes()
    {
        Vector<Shape> selectedShapes = new Vector<Shape>();
        for(int i=shapes.size()-1; i>=0; i--) {
            if(shapes.get(i).isSelected()) {
                selectedShapes.add(shapes.get(i));
            }
        }
        return selectedShapes;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Shape shape : shapes) {
            shape.draw(g);
        }
        for(Line line : lines) {
            line.draw(g);
        }
    }

    public void setOptionEnabled()
    {
        var selectedShapes = getSelectedShapes();
        var size = selectedShapes.size();
        if(size == 0) {
            MenuArea.getInstance().setEnabled(MenuArea.Option.GROUP, false);
            MenuArea.getInstance().setEnabled(MenuArea.Option.UNGROUP, false);
            MenuArea.getInstance().setEnabled(MenuArea.Option.CHANGE_NAME, false);
        }
        else if(size == 1) {
            MenuArea.getInstance().setEnabled(MenuArea.Option.GROUP, false);
            if(selectedShapes.get(0).getShapes().size() > 0) {
                MenuArea.getInstance().setEnabled(MenuArea.Option.UNGROUP, true);
                MenuArea.getInstance().setEnabled(MenuArea.Option.CHANGE_NAME, false);
            }
            else {
                MenuArea.getInstance().setEnabled(MenuArea.Option.UNGROUP, false);
                MenuArea.getInstance().setEnabled(MenuArea.Option.CHANGE_NAME, true);
            }
        }
        else {
            MenuArea.getInstance().setEnabled(MenuArea.Option.GROUP, true);
            MenuArea.getInstance().setEnabled(MenuArea.Option.UNGROUP, false);
            MenuArea.getInstance().setEnabled(MenuArea.Option.CHANGE_NAME, false);
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
                else
                {
                    MenuArea.getInstance().setEnabled(MenuArea.Option.CHANGE_NAME, false);
                    MenuArea.getInstance().setEnabled(MenuArea.Option.UNGROUP, false);
                }
                setOptionEnabled();
                break;
            case USECASE:
                shapes.add(new UseCase(x, y));
                break;
            case CLASS:
                shapes.add(new Class(x, y));
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
                {
                    for(Shape shape : shapes) {
                        if(shape.isInRectangle(startPoint, endPoint)) {
                            shape.setSelected(true);
                        }
                    }
                }
                else if(mode == Mode.MOVE)
                {
                    Moved(x, y);
                }
                setOptionEnabled();
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
                else
                {
                    for(Shape shape : shapes) {
                        shape.setSelected(false);
                    }
                    if(getHoveredShape(startPoint) != null)
                    {
                        setMode(Mode.NONE);
                    }
                    else
                    {
                        setMode(Mode.SELECT);
                    }
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
        if(mode == Mode.MOVE) {
            mode = Mode.NONE;
            System.out.println("move");
            for(Shape shape : shapes) {
                if(shape.isSelected()) {
                    shape.move(x - startPoint.x, y - startPoint.y);
                }
            }
            startPoint = new Point(x, y);
            this.repaint();
        }
    }
}
