package Workspace.Canvas.Line.Body;
import java.awt.*;


public class DottedLine extends Body {
    private static float[] dash = {5.0f};
    public DottedLine() {
        stroke =  new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
    }
}
