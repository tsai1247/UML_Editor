package Workspace.Canvas.Line.Body;
import java.awt.*;


public class DottedLine extends Body {
    private static float[] dash = {10f, 30f};
    public DottedLine() {
        stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dash, 0);
        highlightedstroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dash, 0);
    }
}
