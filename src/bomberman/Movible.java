/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import bomberman.Tipos.*;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Hassan
 */
public class Movible extends MapaBasico {

    public Movible(Tipos.TipoBloque _blockType, Posicion _position) {
        super(_blockType, _position);
    }



    public void setX(int x) {
        this.getPosition().setXPos(x);
    }

    public void setY(int y) {
        this.getPosition().setYPos(y);
    }

    public Rectangle getBounds(Movimiento move) {
        return null;
    }
}
