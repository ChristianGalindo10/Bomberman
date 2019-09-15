/*
 * their are alos enemies to the player that will keep on moving randomly for 
 * ( You are welcome to make them intelligent and give yourself a tough time ;) )
 * if the player touches them then player will die. When the die by the fire/explosion
 * they will also give a powerup but on random basis.
 */
package bomberman;

import java.awt.Image;
import bomberman.Tipos.Movimiento;
import java.awt.Rectangle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Enemigo extends Movible{

    boolean vivo;
    int velocidad = 1;
    Movimiento direction = randomDirection();
    Image img = Img.enemigo;

    public Enemigo(Posicion _position) {
        super(Tipos.TipoBloque.JUGADOR, _position);
        vivo = true;
    }

    public Image getImage() {
        return img;
    }
    
        public void setImage(Image im) {
        img = im;
    }

    public void move() {
        if(direction == Movimiento.STOP) return;
        int r = 0 + (int) (Math.random() * ((25 - 0) + 1));

        if (EvaluadorMov.isValidMove(this, direction)) {
            switch (direction) {
                case UP:
                    this.setY(getY() - velocidad);
                    break;
                case DOWN:
                    this.setY(getY() + velocidad);
                    break;
                case LEFT:
                    this.setX(getX() - velocidad);
                    break;
                case RIGHT:
                    this.setX(getX() + velocidad);
                    break;
            }
        } else {
            direction = randomDirection();
        }
    }

    private Movimiento randomDirection() {
        int r = 0 + (int) (Math.random() * ((3 - 0) + 1));
        if (r == 0) {
            return Movimiento.UP;
        } else if (r == 1) {
            return Movimiento.DOWN;
        } else if (r == 2) {
            return Movimiento.LEFT;
        } else {
            return Movimiento.RIGHT;
        }
    }

    public Rectangle getBounds(Movimiento move) {
        Rectangle rect = null;
        switch (move) {
            case UP:
                rect = new Rectangle(getX(), getY() - velocidad, 37, 40);
                break;
            case DOWN:
                rect = new Rectangle(getX(), getY() + velocidad, 37, 40);
                break;
            case LEFT:
                rect = new Rectangle(getX() - velocidad, getY(), 37, 40);
                break;
            case RIGHT:
                rect = new Rectangle(getX() + velocidad, getY(), 37, 40);
                break;
            case STOP:
                rect = new Rectangle(getX(), getY(), 37, 40);
                break;
        }
        return rect;
    }

    public void die() {
        direction= Movimiento.STOP;
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int c = 0; c < 5; c++) {
                    try {
                        setImage(Img.muerteEnemigo[c]);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            System.out.println("Enemy -> Die -> Join");
        }
        BomberMan.enemigos.remove(this);
    }
}
