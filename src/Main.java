import frames.FrameWithShapes;
import frames.Grid;
import frames.Primitives;
import shapes.*;
import shapes.curves.Curve;
import shapes.curves.ParametricCurve;
import shapes.lines.LineDDA;
import shapes.lines.LineMidPoint;
import shapes.lines.*;
import transformations.Escalation;
import transformations.Rotation;
import transformations.Translation;
import utilities.FloodFill;
import utilities.HelperFuncs;
import utilities.PointXY;
import utilities.ScanLineFill;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // #1 Mid-Point Line
        LineMidPoint lineMidPoint = new LineMidPoint(new PointXY(100, 100), new PointXY(350, 200));

        // #2 Rectangle 2 points
        Rectangle rectangle2 = new Rectangle(new PointXY(350, 400), new PointXY(100, 100));

        // #3 Basic Circle - DONE
        // Circle circle = new Circle(100, 100, 50);

        // #4 Polar Circle - DONE
        // PolarCircle polarCircle = new PolarCircle(new PointXY(200, 200),  50, true);

        // #5 Mid-Point Circle
        MidPointCircle midPointCircle = new MidPointCircle(new PointXY(100, 100), 50, true);

        // #6 Ellipse
        Ellipse ellipse = new Ellipse(new PointXY(200, 200), 150, 50);

        // #7 Flood-fill - PENDING
        FloodFill floodFill = new FloodFill();

        // #8 Scanline-fill - PENDING
        // ScanLineFill scanLineFill = new ScanLineFill();

        // #9 Curve -> Parable
        Curve curve = new Curve(8, 1, true);
        Curve curve2 = new Curve(100, 1, false);

        // #10 Curve -> Smoke
        Curve smoke = new Curve(0, 2, false);

        // #11 Parametric Curve -> Infinite
        ParametricCurve infinite = new ParametricCurve(1, 150);

        // #12 Parametric Curve -> Flower
        ParametricCurve flower = new ParametricCurve(2, 0);

        // #13 Parametric Curve -> Sun
        ParametricCurve sun = new ParametricCurve(3, 0);

        // #14 Grid
        int[] verticalValues = new int[50];
        int[] horizontalValues = new int[50];
        for (int i = 55, j = 0; i < 300; i+=15, j++)
        {
            verticalValues[j] = i;
            horizontalValues[j] = i;
        }
        Grid grid = new Grid(verticalValues, horizontalValues, true);

        // #15 Shapes
        FrameWithShapes frame = new FrameWithShapes();

        // #16 Primitives
        Primitives primitives = new Primitives();

        // #17 Translation
        Translation translation = new Translation();
//
//        // #18 Escalation
        Escalation escalation = new Escalation();

        // #19 Rotation
        Rotation rotation2 = new Rotation(false);

    }
}