/**
 * This class will focus on the implementation of the whether the move picked by
 * a player is valid ( It must not contain any gui related component and nothing
 * of gui can be changed from here). you can define other methods to assist your
 * self and manage your code.
 *
 * You can make constructor of this class if you feel need for it.
 *
 */
package bomberman;

import bomberman.Tipos.*;
import java.awt.Rectangle;

public class EvaluadorMov {

    static int border;
    static int topBorder;
    static Movible player;

    public static boolean isValidMove(Movible _player, Movimiento move) {
        player = _player;
        border = 5;
        topBorder = 20;
        int height = MapaGui.panel.getHeight();
        int width = MapaGui.panel.getWidth();

        int[][] map = BomberMan.intMap;

        int x = player.getX();
        int y = player.getY();
        int footX = x + Img.playerWidth;
        int footY = y + Img.playerHeight;

        if (move == Movimiento.STOP) {
            return false;
        }



        if (player instanceof Enemigo) {
            if (BomberMan.jugadores.get(0).getBounds(Movimiento.STOP).intersects(player.getBounds(move))) {
                System.out.println("DEAD");
                BomberMan.jugadores.get(0).die();
            }
            for (Jugador i : BomberMan.jugadores) {
                for (Bomba b : i.bombas) {
                    if (b.getBounds().intersects(player.getBounds(move))) {
                        return false;
                    }
                }
            }
        }

        if (player instanceof Jugador) {
            for (Poder power : Poder.allPoderes) {
                if (player.getBounds(move).intersects(power.getBounds())) {
                    ((Jugador) player).tomarPoder(power);
                }
            }



            for (Jugador i : BomberMan.jugadores) {
                for (Bomba b : i.bombas) {
                    if (!b.puesta) {
                        if (b.getBounds().intersects(player.getBounds(move))) {
                            return false;
                        }
                    } else {
                        if (!b.getBounds().intersects(player.getBounds(move))) {
                            b.puesta = false;
                        }
                    }
                }
            }


        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 1 || map[i][j] == 2) {
                    switch (move) {
                        case UP:
                            if (player.getBounds(move).intersects(new Rectangle(j * 50, i * 50, 50, 50))) {
                                return false;
                            }
                            break;
                        case DOWN:
                            if (player.getBounds(move).intersects(new Rectangle(j * 50, i * 50, 50, 50))) {
                                return false;
                            }
                            break;
                        case RIGHT:
                            if (player.getBounds(move).intersects(new Rectangle(j * 50, i * 50, 50, 50))) {
                                return false;
                            }
                            break;
                        case LEFT:
                            if (player.getBounds(move).intersects(new Rectangle(j * 50, i * 50, 50, 50))) {
                                return false;
                            }
                            break;
                    }

                }
            }
        }
        return true;
    }

    static int getX(int i) {
        return (50 * i);
    }

    static int getY(int i) {
        return (50 * i);
    }

    static int getLeft(int j) {
        return (50 * j);
    }

    static int getRight(int j) {
        return ((50 * (j + 1)));
    }

    static int getTop(int i) {
        return (50 * (i));
    }

    static int getBottom(int i) {
        return (((i + 1) * 50));
    }

    static void correctY(int y) {
        player.setY((getBottom(y) + getBottom(y - 1) - Img.playerHeight) / 2);
    }

    static void correctX(int x) {
        player.setX((getLeft(x) + getLeft(x - 1) - Img.playerWidth) / 2);
    }

    static void correctX2(int x) {
        player.setX((getLeft(x) + getLeft(x + 1) - Img.playerWidth) / 2);
    }

    static public Rectangle getBounds(int i, int j) {
        return new Rectangle(getX(i), getY(j), 50, 50);
    }
}
