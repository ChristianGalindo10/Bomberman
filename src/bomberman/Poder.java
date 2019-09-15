/*
 * Power ups are kind of bonuses or upgrade that palyer gets randomly when a 
 * breakble block is exploded by the explosion of bomb. Threre are three 
 * different types of power one increase your speed, the second one increases the 
 * range of the explosion of the bomb installed by the player and the third one 
 * increases the numbers of bombs a player can place/installed simultaneously.
 * 
 * If you want you can make three diffrent classes for each of the powerup.
 */
package bomberman;

import java.awt.Image;
import bomberman.Tipos.*;
import static bomberman.Tipos.Poderes.BOMBAS;
import static bomberman.Tipos.Poderes.RANGO;
import static bomberman.Tipos.Poderes.VELOCIDAD;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;





public class Poder extends MapaBasico{
    static CopyOnWriteArrayList<Poder> allPoderes = new CopyOnWriteArrayList<Poder>();
    static final int tiempoPoder = 10000;//10 Seconds
   
    private Poderes poder;
    
    public Poder(Posicion _position, Poderes _powerUp){
        
        super( TipoBloque.BOMBA,_position);
        poder = _powerUp;
        switch(_powerUp){
            case BOMBAS:
                this.setImage(Img.bombaPoder);
                break;
            case RANGO:
                this.setImage(Img.fuegoPoder);
                break;
            case VELOCIDAD:
                this.setImage(Img.velocidadPoder);
                break;
        }
        allPoderes.add(this);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(getX(),getY(),50,50);
    }
    public Poderes getPowerType(){
        return poder;
    }
    
}
