/*
 * This class will contain the implementation of bomb, you have to keep in mind
 * the following points.
 * 1- there is a time duration between the bomb is placed and it explodes
 * 2- if an other bomb is in the range of the bomb that has just exploded
 *    then the other one will also explode.
 * 3- the range of bomb can change depending upon the powerup of the player.
 * 4- You have to extend this assignment for multiplayer afterwards so you do 
 *    have a system to identify that who installed this bomb but for this part 
 *    you may leave this implemntation 
 */
package bomberman;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bomba extends MapaBasico implements Runnable {

    boolean puesta = true;
    static CopyOnWriteArrayList<Bomba> allBombas = new CopyOnWriteArrayList<Bomba>();
    static int size = Img.BOMBSIZE;
    static int retraso = 200;
    static int temporizadorExplosion = 10;
    Thread pulsate;
    Thread explode;
    //Sonidos sonido;

    public Bomba(Posicion _position) {

        super(Tipos.TipoBloque.BOMBA, _position);
        //sonido = new Sonidos();
        /*
        pulsate = new Thread(new Runnable() {    
            public void run() {
                int timer = 0;
                while (timer < temporizadorExplosion) {
                    try {
                        Thread.sleep(retraso);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                    timer++;
                    pulsate();
                }
               // Sounds.getInstance().Explosion();
                explotar();
            }
        });
        pulsate.start();*/
        pulsate = new Thread(this);
        pulsate.start();


    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), size, size);
    }

    @Override
    public void run() {
        int timer = 0;
        while (timer < temporizadorExplosion) {
            try {
                Thread.sleep(retraso);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            timer++;
            pulsar();
        }
        explotar();
        AudioClip sonido;
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/musica/explosion.wav"));
        sonido.play();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bomba.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("BOMB THREAD STARTED");
    }

    public void explotar() {
        BomberMan.jugadores.get(0).bombas.remove(0);
        Fuego.startFire(getPosition());
    }

    public void pulsar() {
        if (getImage().equals(Img.bombaG)) {
            setX(getX() + 5);
            setY(getY() + 5);
            setImage(Img.bombaP);
        } else {
            setX(getX() - 5);
            setY(getY() - 5);
            setImage(Img.bombaG);
        }
    }

}
