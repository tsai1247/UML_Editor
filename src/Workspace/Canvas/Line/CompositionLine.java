package Workspace.Canvas.Line;

import Workspace.Canvas.Line.Arrow.HollowDiamond;
import Workspace.Canvas.Shape.Shape;
import Workspace.Canvas.Shape.Shape.Port;

public class CompositionLine extends Line {
    public CompositionLine(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        super(startShape, startPort, endShape, endPort);
        FrontArrow = new HollowDiamond();
    }

}