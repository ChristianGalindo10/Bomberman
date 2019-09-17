
package bomberman;


import bomberman.Tipos.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MapaBasico {

    private TipoBloque blockType;
    private Posicion position;
    private Image image;

    public MapaBasico(TipoBloque _blockType, Posicion _position) {

        blockType = _blockType;
        position = _position;
        switch (_blockType) {
            case ROMPIBLE:
                break;
            case IRROMPIBLE:
                break;
            case VACIO:
                break;
            case BOMBA:
                image = Img.bombaG;
                break;
            case FUEGO:
                image = Img.fuegoCentro;
                break;
            case JUGADOR:
                image = new ImageIcon("images/bm.gif").getImage();
                break;
        }
    }


    public TipoBloque getBlockType() {
        return blockType;
    }

    public Posicion getPosition() {
        return position;
    }

    public void setPosition(Posicion newPosition) {
        position = newPosition;
    }

    //YO
    public Image getImage() {
        return image;
    }
    
    public int getX(){
        return this.getPosition().getxPos();
    }
    public int getY(){
        return this.getPosition().getyPos();
    }
    
    public void setImage(Image im){
        image = im;
    }
    
    
    ///bomba
        public void setX(int x){
        this.getPosition().setXPos(x);
    }
    public void setY(int y){
        this.getPosition().setYPos(y);
    }
}
