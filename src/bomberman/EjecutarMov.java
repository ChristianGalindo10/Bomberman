/*
 * This class will execute the move on the map and change the data and gui 
 * accordingly.
 * 
 * make constructor of this class or other methods if you feel need for it.
 * don't forget dividing your code into classes and methods is good standard
 * practice in professional world.
 */
package bomberman;


import bomberman.Tipos.*;


public class EjecutarMov{
    
    
    public static void executeMove( Jugador player, Movimiento move){
        int x = player.getPosition().getxPos();
        int y = player.getPosition().getyPos();

        switch(player.direccion){
            case UP:
                y-=player.velocidad;
                break;
            case DOWN:
                y+=player.velocidad;
                break;
            case LEFT:
                x-=player.velocidad;
                break;
            case RIGHT:
                x+=player.velocidad;
                break;
        }
        x+=player.dx;
        y+=player.dy;
        player.setPosition(new Posicion(x, y));
    }
    
    
}
