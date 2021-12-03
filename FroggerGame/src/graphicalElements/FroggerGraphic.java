package graphicalElements;

import gameCommons.IFrog;
import gameCommons.Music;
import graphicalElements2.IFroggerGraphics2;
import util.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics,KeyListener {
    private ArrayList<Element> elementsToDisplay;
    private int pixelByCase = 16;
    private int width;
    private int height;
    private IFrog frog;
    private JFrame frame;
    private int nbOfRestart = 0;
    private Boolean jeuEnCour = false;// pour arreter de tester la game si on clique sur home
    private int straw;
    private int nbJoueur = 1;
    private IFroggerGraphics2 graph;
    private Music music = new Music();

    public FroggerGraphic(int width, int height, IFroggerGraphics2 graph) {
        this.graph = graph;
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
        beginGameScreen("Comment Voulez-vous jouer ? ");
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    public int getStraw() {
        return this.straw;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setStraw(int straw) {
        this.straw = straw;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }

    public int getNbJoueur() {
        return this.nbJoueur;
    }

    public Boolean getJeuEnCour() {
        return jeuEnCour;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon frogPng = new ImageIcon(getClass().getClassLoader().getResource("Image/frog_up_0.png"));
        ImageIcon frogPng1 = new ImageIcon(getClass().getClassLoader().getResource("Image/frog_down_0.png"));
        ImageIcon frogPng2 = new ImageIcon(getClass().getClassLoader().getResource("Image/frog_left_0.png"));
        ImageIcon frogPng3 = new ImageIcon(getClass().getClassLoader().getResource("Image/frog_right_0.png"));
        ImageIcon coin = new ImageIcon(getClass().getClassLoader().getResource("Image/coin.png"));
        ImageIcon toile = new ImageIcon(getClass().getClassLoader().getResource("Image/webSpider .png"));
        ImageIcon ground = new ImageIcon(getClass().getClassLoader().getResource("Image/ground.png"));
        ImageIcon arbre = new ImageIcon(getClass().getClassLoader().getResource("Image/arbre.png"));
        ImageIcon water = new ImageIcon(getClass().getClassLoader().getResource("Image/water.png"));
        ImageIcon flower = new ImageIcon(getClass().getClassLoader().getResource("Image/flower.png"));
        ImageIcon log2 = new ImageIcon(getClass().getClassLoader().getResource("Image/log2.png"));
        ImageIcon log3 = new ImageIcon(getClass().getClassLoader().getResource("Image/log3.png"));
        ImageIcon cars = new ImageIcon(getClass().getClassLoader().getResource("Image/cars.png"));
        ImageIcon bus = new ImageIcon(getClass().getClassLoader().getResource("Image/bus.png"));
        ImageIcon car1 = new ImageIcon(getClass().getClassLoader().getResource("Image/car1.png"));
        ImageIcon car2 = new ImageIcon(getClass().getClassLoader().getResource("Image/car2.png"));
        ImageIcon highWay = new ImageIcon(getClass().getClassLoader().getResource("Image/highWay.png"));


        for (Element e : elementsToDisplay) {
            g.setColor(e.color);
            if (e.color == Color.GREEN) {
                if (this.frog.getDirection() == Direction.up) {
                    frogPng.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
                }
                if (this.frog.getDirection() == Direction.down) {
                    frogPng1.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
                }
                if (this.frog.getDirection() == Direction.left) {
                    frogPng2.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
                }
                if (this.frog.getDirection() == Direction.right) {
                    frogPng3.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
                }
            } else if (e.color == Color.ORANGE) {
                coin.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.WHITE) {
                toile.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.LIGHT_GRAY) {
                ground.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.CYAN) {
                arbre.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.BLUE) {
                water.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.PINK) {
                flower.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.RED) {
                log2.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color.equals(new Color(0xA6115C))) {
                log3.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.BLACK) {
                car1.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.DARK_GRAY) {
                car2.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.MAGENTA) {
                cars.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color == Color.YELLOW) {
                bus.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            } else if (e.color.equals(new Color(0x9A9A19))) {
                highWay.paintIcon(this, g, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord));
            }else {
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

    public void endGameScreen(String s) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException ie){
        }
        frame.remove(this);
        JLabel label = new JLabel(s);
        frame.getContentPane().removeAll();
        bouttonRestart();
        bouttonMenu();
        label.setFont(new Font("Verdana", 1, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    public void beginGameScreen(String s) {
        frame.remove(this);
        if(nbOfRestart > 0){
            frame.getContentPane().removeAll();
            JLabel label = new JLabel(s);
            bouttonNombreJoueur();
            label.setFont(new Font("Verdana", 1, 15));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setSize(this.getSize());
            frame.getContentPane().add(label);
            frame.repaint();
        }else{
            JLabel label = new JLabel(s);
            bouttonNombreJoueur();
            label.setFont(new Font("Verdana", 1, 15));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setSize(this.getSize());
            frame.getContentPane().add(label);
            frame.repaint();
        }
    }
    public void beginGameScreen2(String s) {
        frame.remove(this);
        frame.getContentPane().removeAll();
        JLabel label = new JLabel(s);
        typeDeJeu();
        //bouttonDifficulty();
        label.setFont(new Font("Verdana", 1, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }
    public void beginGameScreen3(String s) {
        frame.remove(this);
        frame.getContentPane().removeAll();
        JLabel label = new JLabel(s);
        //typeDeJeuSimple();
        bouttonDifficulty();
        label.setFont(new Font("Verdana", 1, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    public void bouttonRestart() {
        JButton btn = new JButton("Rejouer");
        btn.setBounds(160, 300, 100, 40);
        frame.getContentPane().add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                URL test = getClass().getResource("/Music/HereWeGoAgain.wav");
                music.playSound(test);
                restart();
            }
        });
    }

    public void bouttonNombreJoueur() {
        JButton btn = new JButton("Jouer en Solo");
        JButton btn1 = new JButton("Jouer en Duo");
        btn.setBounds(160, 240, 100, 40);
        btn1.setBounds(160, 282, 100, 40);
        frame.getContentPane().add(btn);
        frame.getContentPane().add(btn1);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nbJoueur = 1;
                beginGameScreen2("Veuillez choisir votre niveau de difficulté :");
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nbJoueur = 2;
                beginGameScreen2("Veuillez choisir votre niveau de difficulté :");
                frame.setVisible(false);
                graph.getFrame().setVisible(true);
            }
        });
    }
    public void typeDeJeu() {
        JButton btn = new JButton("Jeu Normal");
        JButton btn1 = new JButton("Jeu Infini");
        btn.setBounds(160, 240, 100, 40);
        btn1.setBounds(160, 282, 100, 40);
        frame.getContentPane().add(btn);
        frame.getContentPane().add(btn1);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nbJoueur = 1;
                beginGameScreen3("Veuillez choisir votre niveau de difficulté :");
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nbJoueur = 3;
                //graph.setNbJoueur(3);
                //beginGameScreen2("Veuillez choisir votre niveau de difficulté :");
                frame.setVisible(false);
                //graph.getFrame().setVisible(true); ///////   !!!!!!!!!! a enlever
            }
        });
    }

    public void bouttonDifficulty() {
        //if(this.nbJoueur == 1) {
            JButton difficulte1 = new JButton("Difficulté 1");
            JButton difficulte2 = new JButton("Difficulté 2");
            JButton difficulte3 = new JButton("Difficulté 3");
            difficulte1.setBounds(160, 220, 100, 40);
            difficulte2.setBounds(160, 262, 100, 40);
            difficulte3.setBounds(160, 304, 100, 40);
            frame.getContentPane().add(difficulte1);
            frame.getContentPane().add(difficulte2);
            frame.getContentPane().add(difficulte3);

            difficulte1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    straw = 1;
                    restart();
                }
            });
            difficulte2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    straw = 2;
                    restart();
                }
            });
            difficulte3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    straw = 3;
                    restart();
                }
            });
       // }
    }

    public void bouttonMenu() {
        Icon img = new ImageIcon(getClass().getClassLoader().getResource("Image/homeIcon.png"));
        JButton btn = new JButton(img);
        btn.setBounds(0, 0, 50, 50);
        frame.getContentPane().add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeuEnCour = true;
                nbOfRestart++;
                beginGameScreen("Comment Voulez-vous jouer ? ");
            }
        });
    }

    public void remove(Element e){
        this.elementsToDisplay.remove(e);
    }

    public void restart(){
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.dispose();
        this.frame = new JFrame("Frogger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frog.setRestart(true);
        frog.setLife(true);
        jeuEnCour = false;
        frame.addKeyListener(this);
    }

}
