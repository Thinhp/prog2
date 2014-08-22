/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Frame.BattleShipFrame;

/**
 *
 * @author TanThinh
 * @student id 
 */
public class Main {
    public static void main(String[] args) {
        BattleShipFrame ship = BattleShipFrame.getInstance();
        ship.initialize();
    }
}
