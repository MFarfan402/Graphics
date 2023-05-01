package utilities;

import shapes.MidPointCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ScanLineFill extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public ScanLineFill()
    {

        color = Color.MAGENTA;
        setTitle("Relleno scanline");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    private void DrawCircle()
    {
        MidPointCircle circle = new MidPointCircle(
                new PointXY(150, 150), 50);
        DrawPoints(circle.CircleToPointsXY());
    }

    private void DrawPoints(ArrayList<PointXY> points)
    {
        for (PointXY point : points)
            PutPixel(point.x, point.y);
    }

    private void PutPixel(int x, int y)
    {
        buffer.setRGB(x, y, color.getRGB());
        this.getGraphics().drawImage(buffer, 0, 0, this);
    }

//    private void Fill(int x, int y)
//    {
//        for (int )
//        int myColor = buffer.getRGB(x, y);
//        Color mySecondColor = new Color(myColor);
//
//        if (!mySecondColor.equals(Color.MAGENTA))
//        {
//            PutPixel(x, y);
//            Fill(x + 1, y);
//            Fill(x - 1, y);
//            Fill(x, y - 1);
//            Fill(x, y + 1);
//        }
//    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        DrawCircle();
        //ill(100, 100);
    }

}

