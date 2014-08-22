package Play;

import Ship.ShipModel;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JPanel;

/**
 *
 * @author TanThinh
 */
public class PlayModel extends Observable {

    private ArrayList<JPanel> playerPosition = new ArrayList<JPanel>();
    private ArrayList<JPanel> enemyPosition = new ArrayList<JPanel>();
    private ShipModel shipmodel = new ShipModel();

    public ArrayList<JPanel> getEnemyPosition() {
        return enemyPosition;
    }

    public void setEnemyPosition(ArrayList<JPanel> enemyPosition) {
        this.enemyPosition = enemyPosition;
    }

    public ArrayList<JPanel> getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(ArrayList<JPanel> playerPosition) {
        this.playerPosition = playerPosition;
    }

    public ShipModel getShipmodel() {
        return shipmodel;
    }

    public void setShipmodel(ShipModel shipmodel) {
        this.shipmodel = shipmodel;
    }

    public void change() {
        setChanged();
        notifyObservers();
    }
}
