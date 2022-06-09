package Workspace.Canvas;
import javax.swing.JPanel;

import Workspace.Canvas.Line.*;
import Workspace.Canvas.Shape.*;
import Workspace.Canvas.Shape.Class;
import Workspace.Menu.MenuArea;
import Workspace.Mode.ModeArea;
import Workspace.Mode.ModeArea.Mode;

import java.awt.event.*;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Point;

public class CanvasArea extends JPanel{
    // singleton pattern
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

        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Clicked(e.getX(), e.getY());
                setOptionEnabled();
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                Pressed(e.getX(), e.getY());
                setOptionEnabled();
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                Released(e.getX(), e.getY());
                setOptionEnabled();
            }
        });
    }

    // factory pattern
    protected Line createLine(Mode mode, Shape startShape, Point startPoint, Shape endShape, Point endPoint) {
        var startPort = startShape.getHoveredPort(startPoint);
        var endPort = endShape.getHoveredPort(endPoint);
        switch(mode) {
            case ASSOCIATION:
                return new AssociationLine(startShape, startPort, endShape, endPort);
            case COMPOSITION:
                return new CompositionLine(startShape, startPort, endShape, endPort);
            case GENERALIZATION:
                return new GeneralizationLine(startShape, startPort, endShape, endPort);
            case DEPENDENCY:
                return new DependencyLine(startShape, startPort, endShape, endPort);
            default:
                return null;
        }
    }
    protected Shape createShape(Mode mode, Point point) {
        switch(mode) {
            case CLASS:
                return new Class(point.x, point.y);
            case USECASE:
                return new UseCase(point.x, point.y);
            default:
                return null;
        }
    }

    // containers    
    private Vector<Line> lines = new Vector<Line>();
    private Vector<Shape> shapes = new Vector<Shape>();
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
    public Shape getHoveredShape(Point point) {
        for(int i=shapes.size()-1; i>=0; i--) {
            Shape shape = shapes.get(i);
            if(shape.isHovered(point)) {
                return shape;
            }
        }
        return null;
    }

    // record Point when mouse pressed
    private Point startPoint = null, endPoint = null;

    // control the menu items' state
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

    // control the status of objects on the canvas
    public void ClearSelectedShape() {
        for(Shape shape : shapes) {
            shape.setSelected(false);
        }
        setOptionEnabled();
        this.repaint();
    }
    public void ClearHighlightedLine()
    {    
        for(Line line : lines) {
            line.setHighlighted(false);
        }
        this.repaint();
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

    // event handlers
    public void Clicked(int x, int y) {
        switch(ModeArea.getInstance().getCurrentMode()) {
            case SELECT:
                var hoveredShape = getHoveredShape(new Point(x, y));
                if(hoveredShape != null) {
                    var clickedPort = hoveredShape.getClickedPort(new Point(x, y));
                    var hoveredPort = hoveredShape.getHoveredPort(new Point(x, y));
                    if(hoveredShape.isSelected() && clickedPort != null) {
                        // highlight all lines connected to this port
                        for(Line line : lines) {
                            if(line.getStartShape() == hoveredShape && line.getStartPort() == clickedPort) {
                                    line.setHighlighted(true);
                            }
                            else if(line.getEndShape() == hoveredShape && line.getEndPort() == clickedPort) {
                                    line.setHighlighted(true);
                            }
                        }
                    }
                    else if(hoveredPort != null) {
                        hoveredShape.setSelected(true);
                    }
                }
                break;
            default:
                var shape = createShape(ModeArea.getInstance().getCurrentMode(), new Point(x, y));
                if(shape != null) {
                    shapes.add(shape);
                    shape.setSelected(true);
                }
                break;
        }

        this.repaint();
    }
    public void Released(int x, int y) {
        Shape startShape, endShape;
        switch(ModeArea.getInstance().getCurrentMode()) {
            case SELECT:
                endPoint = new Point(x, y);
                Moved(x, y);
                for(Shape shape : shapes) {
                    if(shape.isInRectangle(startPoint, endPoint)) {
                        shape.setSelected(true);
                    }
                }
                break;
            default:
                endPoint = new Point(x, y);
                startShape = getHoveredShape(startPoint);
                endShape = getHoveredShape(endPoint);
                if(startShape != null && endShape != null && startShape != endShape) {
                    var line = createLine(ModeArea.getInstance().getCurrentMode(), startShape, startPoint, endShape, endPoint);
                    if(line != null) {
                        lines.add(line);
                    }
                }
                break;
        }
        this.repaint();
    }
    

    public void Pressed(int x, int y) {
        startPoint = new Point(x, y);
        if(getHoveredShape(startPoint) == null || !getHoveredShape(startPoint).isSelected()) {
            ClearSelectedShape();
        }
        ClearHighlightedLine();
        this.repaint();
    }
    
    public void Moved(int x, int y) {
        for(Shape shape : shapes) {
            if(shape.isSelected()) {
                shape.move(x - startPoint.x, y - startPoint.y);
            }
        }
    }
}
