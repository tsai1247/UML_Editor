package Workspace.Canvas.Line.Arrow;
import java.awt.*;


public class HollowTriangle extends Arrow {
    @Override
	public void draw(Graphics g, Point startPoint, Point endPoint) {
        Graphics2D g2d = (Graphics2D) g;
        var arrowHeadLeftPoint = getRotatedPoint(endPoint, startPoint, angle);
        var arrowHeadRightPoint = getRotatedPoint(endPoint, startPoint, -angle);
        drawALine(g2d, startPoint, endPoint);
        drawALine(g2d, endPoint, arrowHeadLeftPoint);
        drawALine(g2d, endPoint, arrowHeadRightPoint);
        drawALine(g2d, arrowHeadLeftPoint, arrowHeadRightPoint);
    }
}
    
