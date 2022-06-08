package Workspace.Canvas.Line;
import java.awt.*;

import Workspace.Canvas.Line.Arrow.Arrow;
import Workspace.Canvas.Line.Body.Body;
import Workspace.Canvas.Shape.Shape;
import Workspace.Canvas.Shape.Shape.Port;

public class Line {
    protected Shape startShape, endShape;
    protected Port startPort, endPort;
    protected Body LineBody = new Body();
    protected Arrow FrontArrow = new Arrow(), BackArrow = null;
    public Line(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        this.startShape = startShape;
        this.endShape = endShape;
        this.startPort = startPort;
        this.endPort = endPort;
    }

    public void setLineBody(Body LineBody) {
        this.LineBody = LineBody;
    }
    public void setFrontArrow(Arrow FrontArrow) {
        this.FrontArrow = FrontArrow;
    }
    public void setBackArrow(Arrow BackArrow) {
        this.BackArrow = BackArrow;
    }

    public void draw(Graphics g)
    {
        if(LineBody != null)
            LineBody.draw(g, startShape.getPosition(startPort), endShape.getPosition(endPort));
        if(FrontArrow != null)
            FrontArrow.draw(g, startShape.getPosition(startPort), endShape.getPosition(endPort));
        if(BackArrow != null)
            BackArrow.draw(g, startShape.getPosition(startPort), endShape.getPosition(endPort));
    }

    public Port getStartPort() {
        return startPort;
    }

    public Port getEndPort() {
        return endPort;
    }

    public void setHighlighted(boolean b) {
        LineBody.setHighlighted(b);
    }

    public Shape getStartShape() {
        return startShape;
    }

    public Shape getEndShape() {
        return endShape;
    }

}