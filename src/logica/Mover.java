
package logica;

import logica.Tipos.*;


public class Mover {

    public static void Move(Jugador player) {
        int x = player.getX();
        int y = player.getY();

        if(!player.isAlive()){
            return;
        }
        
        if (EvaluadorMov.isValidMove(player, player.direccion)) {
            EjecutarMov.executeMove(player, player.direccion);
        }
    }
}
