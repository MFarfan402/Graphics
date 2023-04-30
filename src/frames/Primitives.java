package frames;

import shapes.Ellipse;
import shapes.MidPointCircle;
import shapes.Rectangle;
import shapes.lines.Line;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Primitives extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public Primitives()
    {
        setTitle("Primitivas");
        setSize(1200, 500);
        setLayout(null);
        CreateButtons();
        setVisible(true);
        color = Color.BLUE;
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    private void CreateButtons()
    {
        // Linea, rectangulo, cuadrado, triangulo, circulo, elipse

        JButton buttonLine = new JButton("Linea");
        buttonLine.setBounds(30, 30, 100, 30);
        buttonLine.addActionListener((ActionEvent e) -> {
            ExecuteAction("Linea");
        });
        add(buttonLine);

        JButton buttonRectangle = new JButton("Rectangulo");
        buttonRectangle.setBounds(30, 60, 100, 30);
        buttonRectangle.addActionListener((ActionEvent e) -> {
            ExecuteAction("Rectangulo");
        });
        add(buttonRectangle);

        JButton buttonSquare = new JButton("Cuadrado");
        buttonSquare.setBounds(30, 90, 100, 30);
        buttonSquare.addActionListener((ActionEvent e) -> {
            ExecuteAction("Cuadrado");
        });
        add(buttonSquare);

        JButton buttonTriangle = new JButton("Triangulo");
        buttonTriangle.setBounds(30, 120, 100, 30);
        buttonTriangle.addActionListener((ActionEvent e) -> {
            ExecuteAction("Triangulo");
        });
        add(buttonTriangle);

        JButton buttonCircle = new JButton("Circulo");
        buttonCircle.setBounds(30, 150, 100, 30);
        buttonCircle.addActionListener((ActionEvent e) -> {
            ExecuteAction("Circulo");
        });
        add(buttonCircle);

        JButton buttonEllipse = new JButton("Elipse");
        buttonEllipse.setBounds(30, 180, 100, 30);
        buttonEllipse.addActionListener((ActionEvent e) -> {
            ExecuteAction("Elipse");
        });
        add(buttonEllipse);
    }

    private void ExecuteAction(String shape)
    {
        switch (shape)
        {
            case "Linea":
                String lineX1 = JOptionPane.showInputDialog("Valor x1:");
                String lineY1 = JOptionPane.showInputDialog("Valor y1:");
                String lineX2 = JOptionPane.showInputDialog("Valor x2:");
                String lineY2 = JOptionPane.showInputDialog("Valor y2:");

                Line line = new Line(
                        new PointXY(Integer.parseInt(lineX1), Integer.parseInt(lineY1)),
                        new PointXY(Integer.parseInt(lineX2), Integer.parseInt(lineY2)));
                DrawPoints(line.LineToPointsXY());
            break;

            case "Rectangulo":
                String rectX1 = JOptionPane.showInputDialog("Valor x1:");
                String rectY1 = JOptionPane.showInputDialog("Valor y1:");
                String rectX2 = JOptionPane.showInputDialog("Valor x2:");
                String rectY2 = JOptionPane.showInputDialog("Valor y2:");

                DrawPoints(Rectangle.RectangleToPointsXY(
                        new PointXY(Integer.parseInt(rectX1), Integer.parseInt(rectY1)),
                        new PointXY(Integer.parseInt(rectX2), Integer.parseInt(rectY2))
                ));
                break;

            case "Cuadrado":
                String sqrX1 = JOptionPane.showInputDialog("Valor x1:");
                String sqrY1 = JOptionPane.showInputDialog("Valor y1:");
                String size = JOptionPane.showInputDialog("Lado:");
                int sizeInt = Integer.parseInt(size);

                DrawPoints(Rectangle.RectangleToPointsXY(
                        new PointXY(Integer.parseInt(sqrX1), Integer.parseInt(sqrY1)),
                        new PointXY(Integer.parseInt(sqrX1) + sizeInt, Integer.parseInt(sqrY1) + sizeInt)
                ));
                break;

            case "Triangulo":
                String trianguloX1 = JOptionPane.showInputDialog("Valor x1:");
                String trianguloY1 = JOptionPane.showInputDialog("Valor y1:");
                String trianguloX2 = JOptionPane.showInputDialog("Valor x2:");
                String trianguloY2 = JOptionPane.showInputDialog("Valor y2:");
                String trianguloX3 = JOptionPane.showInputDialog("Valor x3:");
                String trianguloY3 = JOptionPane.showInputDialog("Valor y3:");

                Line lineTriangle = new Line(
                        new PointXY(Integer.parseInt(trianguloX1), Integer.parseInt(trianguloY1)),
                        new PointXY(Integer.parseInt(trianguloX2), Integer.parseInt(trianguloY2)));
                DrawPoints(lineTriangle.LineToPointsXY());

                lineTriangle = new Line(
                        new PointXY(Integer.parseInt(trianguloX2), Integer.parseInt(trianguloY2)),
                        new PointXY(Integer.parseInt(trianguloX3), Integer.parseInt(trianguloY3)));
                DrawPoints(lineTriangle.LineToPointsXY());

                lineTriangle = new Line(
                        new PointXY(Integer.parseInt(trianguloX3), Integer.parseInt(trianguloY3)),
                        new PointXY(Integer.parseInt(trianguloX1), Integer.parseInt(trianguloY1)));
                DrawPoints(lineTriangle.LineToPointsXY());
                break;
            case "Circulo":
                String circleX1 = JOptionPane.showInputDialog("Valor centro x:");
                String circleY1 = JOptionPane.showInputDialog("Valor centro y:");
                String radius = JOptionPane.showInputDialog("Radio:");
                int radiusInt = Integer.parseInt(radius);

                MidPointCircle circle = new MidPointCircle(
                        new PointXY(Integer.parseInt(circleX1), Integer.parseInt(circleY1)), radiusInt);
                DrawPoints(circle.CircleToPointsXY());
                break;

            case "Elipse":
                String ellipseX1 = JOptionPane.showInputDialog("Valor centro x:");
                String ellipseY1 = JOptionPane.showInputDialog("Valor centro y:");
                String radiusX = JOptionPane.showInputDialog("Radio x:");
                String radiusY = JOptionPane.showInputDialog("Radio y:");

                DrawPoints(
                        Ellipse.EllipseToPointsXY(new PointXY(Integer.parseInt(ellipseX1), Integer.parseInt(ellipseY1)),
                                Integer.parseInt(radiusX), Integer.parseInt(radiusY))
                );
                break;
            default: break;
        }
    }

    private void DrawPoints(ArrayList<PointXY> points)
    {
        for (PointXY point : points)
            PutPixel(point.x, point.y);
    }

    private void PutPixel(int x, int y)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
    }
}
