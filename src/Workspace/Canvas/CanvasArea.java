package Workspace.Canvas;
import javax.swing.JPanel;

import Workspace.Canvas.Line.AssociationLine;
import Workspace.Canvas.Line.CompositionLine;
import Workspace.Canvas.Line.DependencyLine;
import Workspace.Canvas.Line.GeneralizationLine;
import Workspace.Canvas.Line.Line;
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
                    if(hoveredShape.isSelected()) {
                        // check if user clicked on a port
                        var clickedPort = hoveredShape.getHoveredPort(new Point(x, y));
                        if(clickedPort!= null) {
                            // highlight all lines connected to this port
                            for(Line line : lines) {
                                if(line.getStartShape() == hoveredShape) {
                                    if(line.getStartPort() == clickedPort) {
                                        line.setHighlighted(true);
                                    }
                                }
                                else if(line.getEndShape() == hoveredShape) {
                                    if(line.getEndPort() == clickedPort) {
                                        line.setHighlighted(true);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        hoveredShape.setSelected(true);
                        System.out.println("selected: " + hoveredShape.getName());
                    }
                }
                setOptionEnabled();
                break;
            case USECASE:
            case CLASS:
                shapes.add(createShape(ModeArea.getInstance().getCurrentMode(), new Point(x, y)));
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
                
                Moved(x, y);
                for(Shape shape : shapes) {
                    if(shape.isInRectangle(startPoint, endPoint)) {
                        shape.setSelected(true);
                    }
                }

                setOptionEnabled();
                break;
            case ASSOCIATION:
            case COMPOSITION:
            case GENERALIZATION:
            case DEPENDENCY:
                endPoint = new Point(x, y);
                startShape = getHoveredShape(startPoint);
                endShape = getHoveredShape(endPoint);
                if(startShape != null && endShape != null && startShape != endShape) {
                    lines.add(
                        createLine(ModeArea.getInstance().getCurrentMode(), startShape, startPoint, endShape, endPoint)
                    );
                }
                break;
            default:
                break;
        }
        this.repaint();
    }
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

    public void Pressed(int x, int y) {
        switch(ModeArea.getInstance().getCurrentMode()) {
            case SELECT:
                startPoint = new Point(x, y);
                if(getHoveredShape(startPoint) == null || !getHoveredShape(startPoint).isSelected()) {
                    ClearSelectedShape();
                }
                ClearHighlightedLine();
                break;
            case ASSOCIATION:
            case COMPOSITION:
            case DEPENDENCY:
            case GENERALIZATION:
                startPoint = new Point(x, y);
                break;
            default:
                break;
        }
        this.repaint();
    }
    
    public void Moved(int x, int y) {
        System.out.println("move");
        for(Shape shape : shapes) {
            if(shape.isSelected()) {
                shape.move(x - startPoint.x, y - startPoint.y);
            }
        }
    }
}
