package transformations;

import shapes.Rectangle;
import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Escalation extends JFrame implements Runnable {

    private Color color;
    private Graphics graphics;

    private Image background, buffer;
    private Thread thread;
    private float counter;

    public Escalation()
    {
        color = Color.BLUE;
        setTitle("Escalación");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);
        counter = 1;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                repaint();
                thread.sleep(100);
                counter += .01;
            }
            catch (InterruptedException e) {}
        }
    }

    public void update(Graphics graphics)
    {
        ArrayList<PointXY> points = new ArrayList<PointXY>();
        points.add(new PointXY(100, 100));
        //points.add(new PointXY(135, 200));

        int width = getWidth();
        int height = getHeight();

        graphics.setClip(0, 0, width, height);

        buffer = createImage(width, height);
        Graphics graphicBuffer = buffer.getGraphics();
        graphicBuffer.setClip(0, 0, width, height);
        graphicBuffer.drawImage(background, 0, 0, this);

        ArrayList<PointXY> result = HelperFuncs.Escalation(counter, counter, points);

        ArrayList<PointXY> graphicPoints = Rectangle.RectangleToPointsXY(new PointXY(100, 100), result.get(0));
        for (PointXY point : graphicPoints)
            graphicBuffer.drawLine(point.x, point.y, point.x, point.y);

//        for (PointXY point : result)
//            graphicBuffer.drawLine(point.x, point.y, point.x, point.y);

        graphics.drawImage(buffer, 0, 0, this);
    }

    public void paint(Graphics graphics)
    {
        update(graphics);
    }
}
