package Workspace.Canvas.Line;
import Workspace.Canvas.Line.Body.DottedLine;
import Workspace.Canvas.Shape.Shape;
import Workspace.Canvas.Shape.Shape.Port;


public class DependencyLine extends Line {
    public DependencyLine(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        super(startShape, startPort, endShape, endPort);
        LineBody = new DottedLine();
    }
}
