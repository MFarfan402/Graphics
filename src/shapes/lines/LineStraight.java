package shapes.lines;

import utilities.PointXY;

import java.util.ArrayList;

public class LineStraight {

    public PointXY pointA, pointB;
    public boolean isVertical;

    public LineStraight(PointXY pointA, PointXY pointB, boolean isVertical)
    {
        this.pointA = pointA;
        this.pointB = pointB;
        this.isVertical = isVertical;
    }

    public ArrayList<PointXY> LineToPointsXY()
    {
        ArrayList<PointXY> points = new ArrayList<>();

        if (isVertical)
        {
            int limit = Math.max(pointA.y, pointB.y);
            for (int i = Math.min(pointA.y, pointB.y); i <= limit; i++)
                points.add(new PointXY(pointA.x, i));
        }
        else
        {
            int limit = Math.max(pointA.x, pointB.x);
            for (int i = Math.min(pointA.x, pointB.x); i <= limit; i++)
                points.add(new PointXY(i, pointA.y));
        }
        return points;
    }
}
