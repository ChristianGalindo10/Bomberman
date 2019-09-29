
package logica;


import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MapaGui extends JFrame {
    Timer timer;
    Timer timer2;
    static JPanel panel;
    Bomba bomba;
    static Mapa mapa;
    Jugador jugador;
    Fuego fuego;

    public MapaGui(String title, Mapa mapa) {
        jugador = BomberMan.jugador;
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creando Panel
        this.mapa = mapa;
        GridLayout gL = new GridLayout(15, 15);
        panel = new JPanel(gL) {
            public void paint(Graphics g) {
                super.paint(g);
                panelPaint(g);
            }
        };


        for (int i = 0; i < mapa.size; i++) {
            for (int j = 0; j < mapa.size; j++) {
                panel.add(mapa.getLabel(i, j));
            }
        }


        panel.setDoubleBuffered(true);
        setFocusable(true);

        this.add(panel);
        this.getContentPane().add(panel, BorderLayout.NORTH);

        
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.repaint();
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
      //  this.revalidate();
        //Timers
        timer = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //for (Jugador i : BomberMan.jugadores) {   
                Mover.Move(jugador);
                //}
     
                for (Enemigo enem : BomberMan.enemigos) {
                    enem.move();
                }
                panel.repaint();
            }
        });

        timer2 = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //for (Jugador i : BomberMan.jugadores) {       
                jugador.cycle();

                //}
            }
        });
        
        startTimers();
    }

    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key != KeyEvent.VK_SPACE) {
                BomberMan.jugador.Stop();
            }
        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    BomberMan.jugador.direccion = Tipos.Movimiento.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    BomberMan.jugador.direccion = Tipos.Movimiento.RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    BomberMan.jugador.direccion = Tipos.Movimiento.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    BomberMan.jugador.direccion = Tipos.Movimiento.DOWN;
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("SPACE");
                    BomberMan.jugador.plantBomb();
                    break;
            }
        }
    }

    public void dialogue(){
        JOptionPane.showMessageDialog(this,"Bye Bye","Game Over",JOptionPane.ERROR_MESSAGE);
    }
    public void panelPaint(Graphics g) {
        //if(BomberMan.jugador.isAlive()==false){
            //timer.stop();
            //timer2.stop();
            //dispose();
            //return;
        //}
        Graphics2D g2d = (Graphics2D) g;
        //Dibujar enemigos
        for (Enemigo enemigo : BomberMan.enemigos) {
            g2d.drawImage(enemigo.getImage(), enemigo.getX(), enemigo.getY(), null);
        }
        //Dibujar jugadores 
        //for (Jugador jugador : BomberMan.jugadores) { 
            g2d.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), null);
            for(Bomba bomba : jugador.bombas){
                g2d.drawImage(bomba.getImage(), bomba.getX(), bomba.getY(), panel);
            }
        //}
        
        
        //     g2d.draw3DRect(WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
        g.drawRect(BomberMan.jugador.getX(), BomberMan.jugador.getY() + Img.playerHeight - 30, Img.playerWidth, 30);
        //   g.drawRect(enemy.getX(), enemy.getY(), 37, 40);

        for (Fuego f : Fuego.fuego) {
            g2d.drawImage(f.getImage(), f.getX(), f.getY(), null);
        }

        for(Poder power:Poder.allPoderes){
            g2d.drawImage(power.getImage(), power.getX(), power.getY(), null);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }
    
    public void stopTimers(){
        timer.stop();
        timer2.stop();
    }
    
    public void startTimers(){
        timer.start();
        timer2.start();
    }
}