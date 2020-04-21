package course.labs.single;

import javax.swing.*;
import java.awt.*;

public class GraphicsDemo extends JPanel {

    static float ax = 10, ay = 180;
    static float velocity = 1f;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Graphics Demonstration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        GraphicsDemo gd = new GraphicsDemo();
        frame.add(gd);
        frame.setVisible(true);
        while (true) {
            Thread.sleep(17);
            frame.repaint();
        }
    }

    public void paint(Graphics g) {
        ax+= velocity;
        if (ax > 145)
            velocity = -1f;
        else if (ax < 5)
            velocity = 1f;
        Graphics2D g2d = (Graphics2D)g;
        float dash1[] = {2.0f};
        BasicStroke stroke = new BasicStroke(2.5f);
        g2d.setStroke(stroke);
        draw_arrow(g2d);
        draw_pentagon(g2d);
        BasicStroke stroke2 = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                10.0f, dash1, 0.0f);
        g2d.setStroke(stroke2);
        draw_cube(g2d);
        stroke = new BasicStroke(1.0f);
        g2d.setStroke(stroke);
        draw_triangular_prism(g2d);
        stroke = new BasicStroke(3.0f);
        g2d.setStroke(stroke);
        g2d.fillRect(0, 170, 5, 60);
        g2d.fillRect(190, 170, 5, 60);
        if (ax > 80) {
            g2d.drawOval(70, 170, 60, 60);
            draw_sphere(g2d, (int)ax, (int)ay, 15);
        } else {
            draw_sphere(g2d, (int) ax, (int) ay, 15);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(70, 170, 60, 60);
        }
    }

    public void draw_arrow (Graphics2D g2d) {
        Polygon p = new Polygon();
        p.addPoint(12,0);
        p.addPoint(0,12);
        p.addPoint(24,12);
        g2d.fill(p);
        Rectangle r = new Rectangle(6,12, 12, 15);
        g2d.fill(r);
    }

    public void draw_pentagon (Graphics2D g2d) {
        Polygon p = new Polygon();
        Polygon p2 = new Polygon();
        Polygon p3 = new Polygon();
        p.addPoint(20, 30);
        p.addPoint(0, 50);
        p.addPoint(10, 70);
        p.addPoint(29, 70);
        p.addPoint(40, 50);
        g2d.setColor(Color.GRAY);
        g2d.fill(p);
        g2d.setColor(Color.BLACK);
        g2d.draw(p);
    }

    public void draw_cube (Graphics2D g2d) {
        Rectangle rect = new Rectangle(10,80,20,20);
        Rectangle rect2 = new Rectangle(0,90,20,20);
        g2d.setColor(Color.GRAY);
        g2d.fill(rect);
        g2d.setColor(Color.BLACK);
        g2d.draw(rect);
        g2d.setColor(Color.GRAY);
        g2d.fill(rect2);
        g2d.setColor(Color.BLACK);
        g2d.draw(rect2);
        Polygon p = new Polygon();
        Polygon p2 = new Polygon();
        p.addPoint(10,80);
        p.addPoint(0, 90);
        p.addPoint(20,90);
        p.addPoint(30,80);
        g2d.setColor(Color.GRAY);
        g2d.fill(p);
        g2d.setColor(Color.BLACK);
        g2d.draw(p);
        p2.addPoint(30,80);
        p2.addPoint(20,90);
        p2.addPoint(20,110);
        p2.addPoint(30,100);
        g2d.setColor(Color.GRAY);
        g2d.fill(p2);
        g2d.setColor(Color.BLACK);
        g2d.draw(p2);
    }

    public void draw_triangular_prism (Graphics2D g2d)  {
        Polygon back_t = new Polygon();
        Polygon front_t = new Polygon();
        back_t.addPoint(30,130);
        back_t.addPoint(20,140);
        back_t.addPoint(40,140);
        front_t.addPoint(10,142);
        front_t.addPoint(0, 152);
        front_t.addPoint(20, 152);
        g2d.draw(back_t);
        g2d.drawLine(30,130,10,142);
        g2d.drawLine(20,140,0,152);
        g2d.drawLine(40,140,20,152);
        g2d.draw(front_t);
    }

    public void draw_sphere (Graphics2D g2d, int x, int y, int size) {
        Color c = new Color(65, 65, 65);
        for (int i = size; i > 5; i--) {
            g2d.setColor(c);
            g2d.fillOval(x, y, i*3, i*3);
            c = new Color(c.getRed()+180/size, c.getGreen()+180/size, c.getBlue()+180/size);
            x+=1; y+=1;
        }
    }


}
