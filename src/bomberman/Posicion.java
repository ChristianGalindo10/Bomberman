
package bomberman;

/**
 *
 * @author USUARIO
 */
public class Posicion {
    
     private int yPos, xPos;
    
    public Posicion(int _xPos, int _yPos){
        yPos = _yPos;
        xPos = _xPos;
    }
    
    public boolean equals(Posicion position){
    
        if( position.getxPos() == xPos && position.getyPos() == yPos){
            return true;
        }else{
            return false;
        }
    }
    
    public int getxPos(){ return xPos;}
    
    public int getyPos(){ return yPos;}
    
    public void setPosition(Posicion position){
    
            yPos = position.getyPos();
            xPos = position.getxPos();
    }
    
    public void setPosicion(int _xPos, int _yPos){
    
            yPos = _yPos;
            xPos = _xPos;
    }
    
    public void setXPos(int x){
        xPos = x;
    }
    public void setYPos(int y){
        yPos= y;
    }
}
