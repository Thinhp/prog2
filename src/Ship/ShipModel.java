
package Ship;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author TanThinh
 */
public class ShipModel {

    private int level = 0;
    private ArrayList<String> totalPlayerShip = new ArrayList<String>();
    private ArrayList<String> totalEnemyShip = new ArrayList<String>();
    private ArrayList<String> playerShip1 = new ArrayList<String>();
    private ArrayList<String> playerShip2 = new ArrayList<String>();
    private ArrayList<String> playerShip3 = new ArrayList<String>();
    private ArrayList<String> playerShip4 = new ArrayList<String>();
    private ArrayList<String> playerShip5 = new ArrayList<String>();
    private ArrayList<String> enemyShip1 = new ArrayList<String>();
    private ArrayList<String> enemyShip2 = new ArrayList<String>();
    private ArrayList<String> enemyShip3 = new ArrayList<String>();
    private ArrayList<String> enemyShip4 = new ArrayList<String>();
    private ArrayList<String> enemyShip5 = new ArrayList<String>();
    private ArrayList<JPanel> passShip = new ArrayList<JPanel>();

    public ArrayList<String> getEnemyShip1() {
        return enemyShip1;
    }

    public void setEnemyShip1(ArrayList<String> enemyShip1) {
        this.enemyShip1 = enemyShip1;
    }

    public ArrayList<String> getEnemyShip2() {
        return enemyShip2;
    }

    public void setEnemyShip2(ArrayList<String> enemyShip2) {
        this.enemyShip2 = enemyShip2;
    }

    public ArrayList<String> getEnemyShip3() {
        return enemyShip3;
    }

    public void setEnemyShip3(ArrayList<String> enemyShip3) {
        this.enemyShip3 = enemyShip3;
    }

    public ArrayList<String> getEnemyShip4() {
        return enemyShip4;
    }

    public void setEnemyShip4(ArrayList<String> enemyShip4) {
        this.enemyShip4 = enemyShip4;
    }

    public ArrayList<String> getEnemyShip5() {
        return enemyShip5;
    }

    public void setEnemyShip5(ArrayList<String> enemyShip5) {
        this.enemyShip5 = enemyShip5;
    }

    public ArrayList<String> getPlayerShip1() {
        return playerShip1;
    }

    public void setPlayerShip1(ArrayList<String> playerShip1) {
        this.playerShip1 = playerShip1;
    }

    public ArrayList<String> getPlayerShip2() {
        return playerShip2;
    }

    public void setPlayerShip2(ArrayList<String> playerShip2) {
        this.playerShip2 = playerShip2;
    }

    public ArrayList<String> getPlayerShip3() {
        return playerShip3;
    }

    public void setPlayerShip3(ArrayList<String> playerShip3) {
        this.playerShip3 = playerShip3;
    }

    public ArrayList<String> getPlayerShip4() {
        return playerShip4;
    }

    public void setPlayerShip4(ArrayList<String> playerShip4) {
        this.playerShip4 = playerShip4;
    }

    public ArrayList<String> getPlayerShip5() {
        return playerShip5;
    }

    public void setPlayerShip5(ArrayList<String> playerShip5) {
        this.playerShip5 = playerShip5;
    }

    public ArrayList<String> getTotalEnemyShip() {
        return totalEnemyShip;
    }

    public void setTotalEnemyShip(ArrayList<String> totalEnemyShip) {
        this.totalEnemyShip = totalEnemyShip;
    }

    public ArrayList<String> getTotalPlayerShip() {
        return totalPlayerShip;
    }

    public void setTotalPlayerShip(ArrayList<String> totalPlayerShip) {
        this.totalPlayerShip = totalPlayerShip;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<JPanel> getPassShip() {
        return passShip;
    }

    public void setPassShip(ArrayList<JPanel> passShip) {
        this.passShip = passShip;
    }

    public void combineShip(ArrayList<String> t) {

        if (t == totalPlayerShip) {
            totalPlayerShip.addAll(playerShip1);
            totalPlayerShip.addAll(playerShip2);
            totalPlayerShip.addAll(playerShip3);
            totalPlayerShip.addAll(playerShip4);
            totalPlayerShip.addAll(playerShip5);
        } else if (t == totalEnemyShip) {
            totalEnemyShip.addAll(enemyShip1);
            totalEnemyShip.addAll(enemyShip2);
            totalEnemyShip.addAll(enemyShip3);
            totalEnemyShip.addAll(enemyShip4);
            totalEnemyShip.addAll(enemyShip5);
        } else {
            System.out.println("Wrong use");
        }
    }

    public void removePlayerShipIndividual(String m) {
        for (String t : playerShip1) {
            if (t.equals(m)) {
                playerShip1.remove(t);
                return;
            }
        }
        for (String t : playerShip2) {
            if (t.equals(m)) {
                playerShip2.remove(t);
                return;
            }
        }
        for (String t : playerShip3) {
            if (t.equals(m)) {
                playerShip3.remove(t);
                return;
            }
        }
        for (String t : playerShip4) {
            if (t.equals(m)) {
                playerShip4.remove(t);
                return;
            }
        }
        for (String t : playerShip5) {
            if (t.equals(m)) {
                playerShip5.remove(t);
                return;
            }
        }
    }

    public void removeEnemyShipIndividual(String m) {
        for (String t : enemyShip1) {
            if (t.equals(m)) {
                enemyShip1.remove(t);
                return;
            }
        }
        for (String t : enemyShip2) {
            if (t.equals(m)) {
                enemyShip2.remove(t);
                return;
            }
        }
        for (String t : enemyShip3) {
            if (t.equals(m)) {
                enemyShip3.remove(t);
                return;
            }
        }
        for (String t : enemyShip4) {
            if (t.equals(m)) {
                enemyShip4.remove(t);
                return;
            }
        }
        for (String t : enemyShip5) {
            if (t.equals(m)) {
                enemyShip5.remove(t);
                return;
            }
        }
    }
}
