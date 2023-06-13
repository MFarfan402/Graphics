package scenes;

import shapes.lines.Line;
import utilities.HelperFuncs;
import utilities.PointXY;
import utilities.PointXYZ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation3D extends JFrame implements Runnable
{
    private BufferedImage buffer, bufferSingle;
    private Graphics2D graphics;
    private Thread thread;

    public Animation3D()
    {
        buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        bufferSingle = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();

        setTitle("Proyecto");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(graphics);

//        PaintBase();

//        PuntosFuga3(50, 50, 80, 40, Color.BLACK);


//        PuntosFuga3(30, 20, 80, 17, Color.BLACK);
//        PuntosFuga3(30, 40, 80, 34, Color.BLUE);
        PuntosFuga3(10, 10, 80, 40, Color.red);
//        PuntosFuga3(50, 50, 80, 10, Color.red);

        g.drawImage(buffer, 0,0, this);
        g.dispose();
    }

    public void PaintBase()
    {

    }

    private void PutPixel(int x, int y, Color color)
    {
        bufferSingle.setRGB(0, 0, color.getRGB());
        graphics.drawImage(bufferSingle, x, y, this);
    }
    public int GetPixel(int x, int y) {
        return buffer.getRGB(x, y);
    }

    public void PuntosFuga3 (int x1, int y1, int z1, int large, Color c)
    {
        ArrayList<Integer> valuesX1 = new ArrayList<>();
        ArrayList<Integer> valuesY1 = new ArrayList<>();
        ArrayList<Integer> valuesX2 = new ArrayList<>();
        ArrayList<Integer> valuesY2 = new ArrayList<>();
        ArrayList<Integer> valuesX3 = new ArrayList<>();
        ArrayList<Integer> valuesY3 = new ArrayList<>();
        ArrayList<Integer> valuesX4 = new ArrayList<>();
        ArrayList<Integer> valuesY4 = new ArrayList<>();
        ArrayList<Integer> puntox = new ArrayList<>();
        ArrayList<Integer> puntoy = new ArrayList<>();
        ArrayList<Integer> puntox1 = new ArrayList<>();
        ArrayList<Integer> puntoy1 = new ArrayList<>();
        ArrayList<Integer> puntox2 = new ArrayList<>();
        ArrayList<Integer> puntoy2 = new ArrayList<>();
        int[] arrX = {0, 0, 0};
        int[] arrY = {0, 1, 0};

        double x, y, z = 700.0;
        double xc = -50.0;
        double yc = -40.0;
        double zc = -80.0;

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large);

            x = xc + (tempX - xc) * ((z - zc) / (z1 - zc));
            y = yc + (tempY - yc) * ((z - zc) / (z1 - zc));

            valuesX1.add((int) x);
            valuesY1.add((int) y);
        }

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            double tempZ = z1;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large - 5);

            x = xc + (tempX - xc + 30) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc - 5) * ((z - zc) / (tempZ - zc));

            valuesX2.add((int) x);
            valuesY2.add((int) y);
        }

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            double tempZ = z1;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large - 5);

            x = xc + (tempX - xc - 30) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc - 5) * ((z - zc) / (tempZ - zc));

            valuesX3.add((int) x);
            valuesY3.add((int) y);
        }

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            double tempZ = z1;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large - 5);

            x = xc + (tempX - xc) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc - 20) * ((z - zc) / (tempZ - zc));

            valuesX4.add((int) x);
            valuesY4.add((int) y);
        }

        puntox.add(550);
        puntoy.add(400);

        puntox1.add(300);
        puntoy1.add(400);

        puntox2.add(420);
        puntoy2.add(580);

        for (int cont = 1; cont < valuesX1.size(); cont++)
        {
            Line line = new Line(new PointXY(valuesX1.get(cont - 1), valuesY1.get(cont - 1)), new PointXY(valuesX1.get(cont), valuesY1.get(cont)));
            Line line2 = new Line(new PointXY(valuesX2.get(cont - 1), valuesY2.get(cont - 1)), new PointXY(valuesX2.get(cont), valuesY2.get(cont)));
            Line line3 = new Line(new PointXY(valuesX3.get(cont - 1), valuesY3.get(cont - 1)), new PointXY(valuesX3.get(cont), valuesY3.get(cont)));
            Line line4 = new Line(new PointXY(valuesX4.get(cont - 1), valuesY4.get(cont - 1)), new PointXY(valuesX4.get(cont), valuesY4.get(cont)));

            Line line5 = new Line(new PointXY(valuesX1.get(cont - 1), valuesY1.get(cont - 1)), new PointXY(valuesX2.get(cont - 1), valuesY2.get(cont - 1)));
            Line line6 = new Line(new PointXY(valuesX2.get(cont - 1), valuesY2.get(cont - 1)), new PointXY(valuesX4.get(cont - 1), valuesY4.get(cont - 1)));
            Line line7 = new Line(new PointXY(valuesX4.get(cont - 1), valuesY4.get(cont - 1)), new PointXY(valuesX3.get(cont - 1), valuesY3.get(cont - 1)));
            Line line8 = new Line(new PointXY(valuesX3.get(cont - 1), valuesY3.get(cont - 1)), new PointXY(valuesX1.get(cont - 1), valuesY1.get(cont - 1)));

            ArrayList<PointXY> points = line.LineToPointsXY();
            points.addAll(line2.LineToPointsXY());
            points.addAll(line3.LineToPointsXY());
            points.addAll(line4.LineToPointsXY());
            points.addAll(line5.LineToPointsXY());
            points.addAll(line6.LineToPointsXY());
            points.addAll(line7.LineToPointsXY());
            points.addAll(line8.LineToPointsXY());

            for (PointXY point : points)
                PutPixel(point.x, point.y, c);
        }
    }
}
