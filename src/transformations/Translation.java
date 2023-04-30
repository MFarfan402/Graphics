package transformations;

import utilities.HelperFuncs;
import utilities.PointXY;
import shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Translation extends JFrame implements Runnable {

    private Color color;
    private Graphics graphics;

    private Image background, buffer;
    private Thread thread;

    private int counter;


    public Translation()
    {
        color = Color.BLUE;
        setTitle("Translacion");
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
                thread.sleep(100);

                if (++counter == 50) counter = 0;
            }
            catch (InterruptedException e) {}
        }
    }

    public void update(Graphics graphics)
    {
        int width = getWidth();
        int height = getHeight();

        graphics.setClip(0, 0, width, height);

        buffer = createImage(width, height);
        Graphics graphicBuffer = buffer.getGraphics();
        graphicBuffer.setClip(0, 0, width, height);
        graphicBuffer.drawImage(buffer, 0, 0, this);

        ArrayList<PointXY> points = new ArrayList<PointXY>();
        points.add(new PointXY(100, 100));
        points.add(new PointXY(135, 200));

        ArrayList<PointXY> result = HelperFuncs.Translation(counter, counter, points);

        ArrayList<PointXY> graphicPoints = Rectangle.RectangleToPointsXY(result.get(0), result.get(1));
        for (PointXY point : graphicPoints)
            graphicBuffer.drawLine(point.x, point.y, point.x, point.y);

        graphics.drawImage(buffer, 0, 0, this);
    }

    public void paint(Graphics graphics)
    {
        update(graphics);
    }

}
