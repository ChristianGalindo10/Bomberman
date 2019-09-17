
package bomberman;

import bomberman.Tipos.*;


public class Mover {

    public static void Move(Jugador player) {
        int x = player.getX();
        int y = player.getY();
//
//        switch (move) {
//            case UP:
//                player.dy = -player.speed;
//                break;
//            case DOWN:
//                player.dy = player.speed;
//                break;
//            case LEFT:
//                player.dx = -player.speed;
//                break;
//            case RIGHT:
//                player.dx = player.speed;
//                break;
//        }
        if(!player.isAlive()){
            return;
        }
        
        if (EvaluadorMov.isValidMove(player, player.direccion)) {
            EjecutarMov.executeMove(player, player.direccion);
        }
    }
}
