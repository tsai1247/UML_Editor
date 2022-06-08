package Workspace.Canvas.Line;

import Workspace.Canvas.Line.Arrow.HollowTriangle;
import Workspace.Canvas.Shape.Shape;
import Workspace.Canvas.Shape.Shape.Port;

public class GeneralizationLine extends Line {
    public GeneralizationLine(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        super(startShape, startPort, endShape, endPort);
        FrontArrow = new HollowTriangle();
    }
}