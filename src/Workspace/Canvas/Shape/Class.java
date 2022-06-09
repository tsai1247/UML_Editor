package Workspace.Canvas.Shape;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.*;

public class Class extends Shape {
    private int layerNum = 3;

    public Class(int x, int y) {
        super(x, y, 100, 80);
        nameOffset = new Point(0, -20);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // draw class
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0; i<layerNum; i++) {
            var Rectangle = new Rectangle2D.Double(x, y + height/layerNum*i, width, height/layerNum);
            g2d.setColor(Color.BLACK);
            g2d.draw(Rectangle);
        }
    }

}
