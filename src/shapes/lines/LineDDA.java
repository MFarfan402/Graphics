package shapes.lines;

import utilities.HelperFuncs;
import utilities.PointXY;

import java.util.ArrayList;

public class LineDDA {

    public PointXY pointA, pointB;
    public LineDDA(PointXY pointA, PointXY pointB)
    {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public ArrayList<PointXY> LineToPointsXY()
    {
        int[] xValues, yValues;
        ArrayList<PointXY> points = new ArrayList<PointXY>();

        float slope = HelperFuncs.calculateSlope(pointA, pointB);
        slope = Math.abs(slope);
        if (slope >= 1)
        {
            int positions = (pointB.y > pointA.y) ?
                    pointB.y - pointA.y + 1 :
                    pointA.y - pointB.y + 1 ;

            float x = pointA.x;
            xValues = new int[positions];
            yValues = new int[positions];
            xValues[0] = Math.round(x);

            for (int i = 0, j = pointA.y; i < positions; i++, j++)
            {
                yValues[i] = j;
                if (i > 0)
                {
                    x += (1.0f/slope);
                    xValues[i] = Math.round(x);
                }
            }
        }
        else
        {
            int positions = (pointB.x > pointA.x) ?
                    pointB.x - pointA.x + 1 :
                    pointA.x - pointB.x + 1 ;

            float y = pointA.y;
            xValues = new int[positions];
            yValues = new int[positions];
            yValues[0] = Math.round(y);

            for (int i = 0, j = pointA.x; i < positions; i++, j++)
            {
                xValues[i] = j;
                if (i > 0)
                {
                    y += (1.0f/slope);
                    yValues[i] = Math.round(y);
                }
            }
        }
        for (int i = 0; i < xValues.length; i++)
            points.add(new PointXY(xValues[i], yValues[i]));
        return points;
    }
}
