package transformations;

import shapes.Rectangle;
import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Rotation extends JFrame implements Runnable {

    private Color color;
    private Graphics graphics;

    private Image background, buffer;
    private Thread thread;
    private int counter;

    private boolean centerRotation;

    public Rotation(boolean centerRotation)
    {
        this.centerRotation = centerRotation;
        color = Color.BLUE;
        setTitle("Rotacion");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);
        counter = 0;
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
                thread.sleep(10);
                if (++counter == 360) counter = 0;
            }
            catch (InterruptedException e) {}
        }
    }

    public void update(Graphics graphics)
    {
        ArrayList<PointXY> points = Rectangle.RectangleToPointsXY(new PointXY(100, 100), new PointXY(200, 200));

        int width = getWidth();
        int height = getHeight();

        graphics.setClip(0, 0, width, height);

        buffer = createImage(width, height);
        Graphics graphicBuffer = buffer.getGraphics();
        graphicBuffer.setClip(0, 0, width, height);
        graphicBuffer.drawImage(background, 0, 0, this);

        ArrayList<PointXY> result = centerRotation ?
                HelperFuncs.CenterRotation(counter, points, new PointXY(150, 150)) :
                HelperFuncs.Rotation(counter, points);
        for (PointXY point : result)
            graphicBuffer.drawLine(point.x, point.y, point.x, point.y);

        graphics.drawImage(buffer, 0, 0, this);
    }

    public void paint(Graphics graphics)
    {
        update(graphics);
    }
}