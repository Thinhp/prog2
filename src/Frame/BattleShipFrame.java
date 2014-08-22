/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Exception.BackgroundImageNotFoundException;
import Exception.SeaFieldImageNotFoundException;
import Exception.ShipImageNotFoundException;
import Menu.MenuView;
import Play.PlayView;
import ShipPlace.ShipPlaceView;
import javax.swing.JFrame;

/**
 *
 * @author TanThinh
 * @student id : s3357678
 */
public class BattleShipFrame extends JFrame {

    private static BattleShipFrame unique;
    private static MenuView menu = MenuView.getInstance();
    private static ShipPlaceView shipPlace = new ShipPlaceView();
    private static PlayView play = new PlayView();

    public static BattleShipFrame getInstance() {
        if (unique == null) {
            unique = new BattleShipFrame();
        }
        return unique;
    }

    public void initialize() {
        try {
            menu.settings();
            shipPlace.settings();
        } catch (BackgroundImageNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SeaFieldImageNotFoundException b) {
            System.out.println(b.getMessage());
        } catch (ShipImageNotFoundException c) {
            System.out.println(c.getMessage());
        }

        add(menu);

        setTitle("Battle Ship - Stormy War");
        setSize(1200, 750);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static MenuView getMenu() {
        return menu;
    }

    public static void setMenu(MenuView menu) {
        BattleShipFrame.menu = menu;
    }

    public static PlayView getPlay() {
        return play;
    }

    public static void setPlay(PlayView play) {
        BattleShipFrame.play = play;
    }

    public static ShipPlaceView getShipPlace() {
        return shipPlace;
    }

    public static void setShipPlace(ShipPlaceView shipPlace) {
        BattleShipFrame.shipPlace = shipPlace;
    }

    public static BattleShipFrame getUnique() {
        return unique;
    }

    public static void setUnique(BattleShipFrame unique) {
        BattleShipFrame.unique = unique;
    }
}
