package Workspace.Canvas.Line;

import Workspace.Canvas.Shape.Shape;
import Workspace.Canvas.Shape.Shape.Port;

public class AssociationLine extends Line {
    public AssociationLine(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        super(startShape, startPort, endShape, endPort);
    }
}