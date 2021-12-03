package graphicalElements;

import gameCommons.IFrog;
import gameCommons.IFrog2;
import util.Direction;
import util.Direction2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
    private ArrayList<Element> elementsToDisplay;
    private int pixelByCase = 16;
    private int width;
    private int height;
    private IFrog frog;
    private IFrog2 frog2;
    private JFrame frame;

    public FroggerGraphic(int width, int height) {
        this.width = width;
        this.height = height;
        elementsToDisplay = new ArrayList<Element>();
        setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));
        setBackground(Color.GRAY);
        JFrame frame = new JFrame("Frogger");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;
        BufferedImage frogPng = null;
        BufferedImage frogPng1 = null;
        BufferedImage frogPng2 = null;
        BufferedImage frogPng3 = null;
        BufferedImage frog2Png = null;
        BufferedImage frog2Png1 = null;
        BufferedImage frog2Png2 = null;
        BufferedImage frog2Png3 = null;
        BufferedImage coin = null;
        BufferedImage toile = null;
        BufferedImage ground = null;
        BufferedImage arbre = null;
        BufferedImage water = null;
        BufferedImage flower = null;
        BufferedImage log2 = null;
        BufferedImage log3 = null;
        BufferedImage cars = null;
        BufferedImage car1 = null;
        BufferedImage car2 = null;
        BufferedImage bus = null;
        try {
            frogPng = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_up_0.png"));
            frogPng1 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_down_0.png"));
            frogPng2 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_left_0.png"));
            frogPng3 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_right_0.png"));
            frog2Png = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_up_1.png"));
            frog2Png1 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_down_1.png"));
            frog2Png2 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_left_1.png"));
            frog2Png3 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/frog_right_1.png"));
            coin = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/coin.png"));
            toile = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/webSpider .png"));
            ground = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/ground.png"));
            arbre = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/arbre.png"));
            water = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/water.png"));
            flower = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/flower.png"));
            log2 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/log2.png"));
            log3 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/log3.png"));
            cars = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/cars.png"));
            bus = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/bus.png"));
            car1 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/car1.png"));
            car2 = ImageIO.read(new File("/Users/Pika/Desktop/Frogger5Double/src/Image/car2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Element e : elementsToDisplay) {
            g.setColor(e.color);
            if(e.color == Color.GREEN) {
                if (this.frog.getDirection() == Direction.up) {
                    g2.drawImage(frogPng, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
                if (this.frog.getDirection() == Direction.down) {
                    g2.drawImage(frogPng1, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
                if (this.frog.getDirection() == Direction.left) {
                    g2.drawImage(frogPng2, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
                if (this.frog.getDirection() == Direction.right) {
                    g2.drawImage(frogPng3, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
            }
            else if(e.color.equals(new Color(0x20208A))) {
                if (this.frog2.getDirection() == Direction2.Z) {
                    g2.drawImage(frog2Png, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
                if (this.frog2.getDirection() == Direction2.S) {
                    g2.drawImage(frog2Png1, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
                if (this.frog2.getDirection() == Direction2.Q) {
                    g2.drawImage(frog2Png2, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
                if (this.frog2.getDirection() == Direction2.D) {
                    g2.drawImage(frog2Png3, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
                }
            }
            else if(e.color == Color.ORANGE){
                g2.drawImage(coin, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.WHITE){
                g2.drawImage(toile, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.LIGHT_GRAY){
                g2.drawImage(ground, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.CYAN){
                g2.drawImage(arbre, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.BLUE){
                g2.drawImage(water, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.PINK){
                g2.drawImage(flower, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.RED){
                g2.drawImage(log2, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color.equals(new Color(0xA6115C))){
                g2.drawImage(log3, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.BLACK){
                g2.drawImage(car1, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.DARK_GRAY){
                g2.drawImage(car2, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.MAGENTA){
                g2.drawImage(cars, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else if(e.color == Color.YELLOW){
                g2.drawImage(bus, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
            }
            else{
                g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
            }
        }
        }


    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                frog.move(Direction.up);
                break;
            case KeyEvent.VK_DOWN:
                frog.move(Direction.down);
                break;
            case KeyEvent.VK_LEFT:
                frog.move(Direction.left);
                break;
            case KeyEvent.VK_RIGHT:
                frog.move(Direction.right);
                break;
            case KeyEvent.VK_Z:
                frog2.move(Direction2.Z);
                break;
            case KeyEvent.VK_S:
                frog2.move(Direction2.S);
                break;
            case KeyEvent.VK_Q:
                frog2.move(Direction2.Q);
                break;
            case KeyEvent.VK_D:
                frog2.move(Direction2.D);
        }
    }

    public void clear() {
        this.elementsToDisplay.clear();
    }

    public void add(Element e) {
        this.elementsToDisplay.add(e);
    }

    public void setFrog(IFrog frog) {
        this.frog = frog;
    }
    public void setFrog2(IFrog2 frog2) {
        this.frog2 = frog2;
    }

    public void endGameScreen(String s) {
        frame.remove(this);
        JLabel label = new JLabel(s);
        label.setFont(new Font("Verdana", 1, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    public void remove(Element e){
        this.elementsToDisplay.remove(e);
    }


}
