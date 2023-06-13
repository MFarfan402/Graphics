package proyection;

import scenes.MyColor;
import shapes.Rectangle;
import shapes.lines.Line;
import utilities.PointXY;
import utilities.PointXYZ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Fill3D extends JFrame {
    private Color color;
    private BufferedImage buffer, bufferSingle;
    private Graphics2D graphics;

    private ArrayList<PointXY> pointsXY = new ArrayList<PointXY>();
    private ArrayList<PointXYZ> pointsXYZ;
    private PointXYZ vector;

    public Fill3D()
    {
        buffer = new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);
        bufferSingle = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();

        color = Color.RED;
        setTitle("Relleno 3D");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        GenericCube cube = new GenericCube();
        pointsXYZ = cube.pointsXYZ;

        vector = new PointXYZ(-2,-1,6);
    }

    private void DrawCube()
    {
        for (int i = 0; i < pointsXYZ.size(); i++)
        {
            float u =  (float) (-1 * pointsXYZ.get(i).z) / vector.z;
            float x = pointsXYZ.get(i).x + (vector.x * u);
            float y = pointsXYZ.get(i).y + (vector.y * u);

            pointsXY.add(new PointXY((int) x, (int) y));
        }
        for (PointXY point: pointsXY)
        {
            PutPixel(point.x, point.y, MyColor.myYellow);
            System.out.println("x:" + point.x + " y:" + point.y);
        }

        Line lineAB = new Line(pointsXY.get(0), pointsXY.get(1));
        Line lineAC = new Line(pointsXY.get(0), pointsXY.get(2));
        Line lineAE = new Line(pointsXY.get(0), pointsXY.get(4));

        Line lineBD = new Line(pointsXY.get(1), pointsXY.get(3));
        Line lineBF = new Line(pointsXY.get(1), pointsXY.get(5));

        Line lineEG = new Line(pointsXY.get(4), pointsXY.get(6));
        Line lineEF = new Line(pointsXY.get(4), pointsXY.get(5));

        Line lineCG = new Line(pointsXY.get(2), pointsXY.get(6));
        Line lineCD = new Line(pointsXY.get(2), pointsXY.get(3));

        Line lineHG = new Line(pointsXY.get(7), pointsXY.get(6));
        Line lineHF = new Line(pointsXY.get(7), pointsXY.get(5));
        Line lineHD = new Line(pointsXY.get(7), pointsXY.get(3));

        ArrayList<PointXY> points = lineAB.LineToPointsXY();
        points.addAll(lineCG.LineToPointsXY());
        points.addAll(lineBF.LineToPointsXY());
        points.addAll(lineHF.LineToPointsXY());
        points.addAll(lineHG.LineToPointsXY());
        points.addAll(lineAC.LineToPointsXY());

        for (PointXY point: points)
            PutPixel(point.x, point.y, MyColor.myYellow);
        Rellenar(110, 266, MyColor.myYellow, MyColor.myYellow);
//
        points.addAll(lineBD.LineToPointsXY());
        points.addAll(lineHD.LineToPointsXY());
        points.addAll(lineEG.LineToPointsXY());
        points.addAll(lineAE.LineToPointsXY());
        points.addAll(lineEF.LineToPointsXY());
        points.addAll(lineCD.LineToPointsXY());

        for (PointXY point: points)
            PutPixel(point.x, point.y, MyColor.cabinBlack2);
    }

    public void Rellenar (int x, int y,Color color, Color figura) {
        int limiteIzquierdo = x;
        int limiteDerecho = x;

        while (getPixel(limiteIzquierdo - 1, y) != figura.getRGB()) {
            limiteIzquierdo--;
        }
        while (getPixel(limiteDerecho + 1, y) != figura.getRGB()) {
            limiteDerecho++;
        }

        for (int i = limiteIzquierdo; i <= limiteDerecho; i++) {
            PutPixel(i, y, color);
        }

        for (int i = limiteIzquierdo; i <= limiteDerecho; i++) {
            if (getPixel(i , y - 1) != figura.getRGB() && getPixel(i , y - 1) != color.getRGB()) {
                Rellenar(i, y - 1,color,figura);
            }
            if (getPixel(i , y + 1) != figura.getRGB() && getPixel(i , y + 1) != color.getRGB()) {
                Rellenar(i, y + 1,color,figura);
            }
        }
    }
    public int getPixel(int x, int y) { return buffer.getRGB(x, y); }
    private void PutPixel(int x, int y, Color color)
    {
        bufferSingle.setRGB(0, 0, color.getRGB());
        graphics.drawImage(bufferSingle, x, y, this);
    }

    public void paint(Graphics g)
    {
        super.paint(graphics);
        DrawCube();

        g.drawImage(buffer, 0,0, this);
        g.dispose();
    }
}
