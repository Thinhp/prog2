/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShipPlace;

import Ship.ShipModel;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author TanThinh
 */
public class ShipPlaceModel extends Observable {

    private int chosenShip = 0;
    private int[] cor = new int[2];
    private String clickName = "";
    private int checkAllShip = 0;
    private ShipModel shipmodel = new ShipModel();
    private ArrayList<String> chosenPosition = new ArrayList<String>();

    public ArrayList<String> getChosenPosition() {
        return chosenPosition;
    }

    public void setChosenPosition(ArrayList<String> chosenPosition) {
        this.chosenPosition = chosenPosition;
    }

    public void change() {
        setChanged();
        notifyObservers();
    }

    public int getChosenShip() {
        return chosenShip;
    }

    public void setChosenShip(int chosenShip) {
        this.chosenShip = chosenShip;
        setChanged();
        notifyObservers(chosenShip);
    }

    public void SetCor(int x, int y, String name) {
        cor[0] = x;
        cor[1] = y;
        clickName = name;
        setChanged();
        notifyObservers(cor);
    }

    public int[] getCor() {
        return cor;
    }

    public void setCor(int[] cor) {
        this.cor = cor;
    }

    public ShipModel getShipmodel() {
        return shipmodel;
    }

    public void setShipmodel(ShipModel shipmodel) {
        this.shipmodel = shipmodel;
    }

    public String getClickName() {
        return clickName;
    }

    public void setClickName(String clickedPanel) {
        this.clickName = clickedPanel;
    }

    public int getCheckAllShip() {
        return checkAllShip;
    }

    public void setCheckAllShip(int checkAllShip) {
        this.checkAllShip = checkAllShip;
    }
}
