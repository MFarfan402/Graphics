package shapes.lines;

import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Line {
    public PointXY pointA, pointB;

    public Line(PointXY pointA, PointXY pointB)
    {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public ArrayList<PointXY> LineToPointsXY()
    {
        ArrayList<PointXY> points = new ArrayList<PointXY>();

        int xk = pointA.x, yk = pointA.y;
        int dx = pointB.x - pointA.x, dy = pointB.y - pointA.y;
        int incX = 1, incY = 1, incE, incNE, pk = 0;

        if (dx < 0)
        {
            dx = -dx;
            incX = -1;
        }
        if (dy < 0)
        {
            dy = -dy;
            incY = -1;
        }

        if (Math.abs(dx) > Math.abs(dy))
        {
            incE = 2 * dy;
            incNE = 2 * (dy - dx);

            while (xk != pointB.x)
            {
                points.add(new PointXY(xk, yk));
                xk += incX;
                if (2 * (pk + dy) < dx)
                    pk += incE;
                else
                {
                    pk += incNE;
                    yk += incY;
                }
            }
        }
        else
        {
            incE = 2 * dx;
            incNE = 2 * (dx - dy);
            while (yk != pointB.y)
            {
                points.add(new PointXY(xk, yk));
                yk += incY;
                if (2 * (pk + dx) < dy)
                    pk += incE;
                else
                {
                    pk += incNE;
                    xk += incX;
                }
            }
        }
        return points;
    }
}
