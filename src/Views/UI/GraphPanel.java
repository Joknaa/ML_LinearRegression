package Views.UI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {

    private final int width = 800;
    private final int heigth = 400;
    private final int padding = 25;
    private final int labelPadding = 25;
    private final Color lineColor = new Color(44, 102, 230, 180);
    private final Color pointColor = new Color(100, 100, 100, 180);
    private final Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth = 4;
    private final int numberYDivisions = 10;
    private final List<Float> scores;

    public GraphPanel(List<Float> scores) {
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D ScoreGraph = (Graphics2D) g;
        ScoreGraph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<Point> graphPoints = SetupGraphPoints();
        DrawBackground(ScoreGraph);
        DrawHatchMarks_AndGridLines_YAxis(ScoreGraph);
        DrawHatchMarks_AndGridLines_XAxis(ScoreGraph);
        DrawAxes(ScoreGraph);
        DrawFunction(ScoreGraph, graphPoints);
        DrawPoints(ScoreGraph, graphPoints);
    }

    private List<Point> SetupGraphPoints() {
        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());
        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }
        return graphPoints;
    }
    private void DrawBackground(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
                getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);
    }
    private void DrawHatchMarks_AndGridLines_YAxis(Graphics2D g2) {
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight()
                    - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore()
                        + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }
    }
    private void DrawHatchMarks_AndGridLines_XAxis(Graphics2D g2) {
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }
    }
    private void DrawAxes(Graphics2D g2) {
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding,
                getHeight() - padding - labelPadding);
    }
    private void DrawFunction(Graphics2D g2, List<Point> graphPoints) {
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
    }
    private void DrawPoints(Graphics2D g2, List<Point> graphPoints) {
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (Point graphPoint : graphPoints) {
            int x = graphPoint.x - pointWidth / 2;
            int y = graphPoint.y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }
    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Float score : scores) {
            minScore = Math.min(0, score);
        }
        return minScore;
    }
    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Float score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public static void createAndShowGui(List<Float> SquaredErrors, float alpha, float[] initialP) {
        MainPanel mainPanel = new MainPanel(SquaredErrors, alpha, initialP);
        mainPanel.setPreferredSize(new Dimension(600, 400));
        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    static class MainPanel extends JPanel {
        public MainPanel(List<Float> scores, float alpha, float[] initialP) {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Cost Per Iterations (Alpha: " + alpha + ", p0: " + initialP[0] + ", p1: " + initialP[1] + ")");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setHorizontalAlignment(JLabel.CENTER);

            JPanel graphPanel = new GraphPanel(scores);
            VerticalPanel vertPanel = new VerticalPanel();
            HorizontalPanel horiPanel = new HorizontalPanel();

            add(title, BorderLayout.NORTH);
            add(horiPanel, BorderLayout.SOUTH);
            add(vertPanel, BorderLayout.WEST);
            add(graphPanel, BorderLayout.CENTER);

        }
        static class VerticalPanel extends JPanel {

            public VerticalPanel() {
                setPreferredSize(new Dimension(25, 0));
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D gg = (Graphics2D) g;
                gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Font font = new Font("Arial", Font.PLAIN, 15);
                String string = "Cost";

                FontMetrics metrics = g.getFontMetrics(font);
                int width = metrics.stringWidth(string);
                int height = metrics.getHeight();
                gg.setFont(font);
                drawRotate(gg, getWidth(), (getHeight() + width) / 2, 270, string);
            }

            public void drawRotate(Graphics2D gg, double x, double y, int angle, String text) {
                gg.translate((float) x, (float) y);
                gg.rotate(Math.toRadians(angle));
                gg.drawString(text, 0, 0);
                gg.rotate(-Math.toRadians(angle));
                gg.translate(-(float) x, -(float) y);
            }

        }
        static class HorizontalPanel extends JPanel {
            public HorizontalPanel() {
                setPreferredSize(new Dimension(0, 25));
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D gg = (Graphics2D) g;
                gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Font font = new Font("Arial", Font.PLAIN, 15);

                String string = "Iterations";

                FontMetrics metrics = g.getFontMetrics(font);
                int width = metrics.stringWidth(string);
                int height = metrics.getHeight();

                gg.setFont(font);

                gg.drawString(string, (getWidth() - width) / 2, 11);
            }

        }
    }
}