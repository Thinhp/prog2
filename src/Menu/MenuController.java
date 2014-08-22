/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import CustomButton.MyButton;
import Frame.AboutFrame;
import Frame.BattleShipFrame;
import Sound.MySound;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author TanThinh
 */
public class MenuController implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof MyButton) {
            MyButton mybut = (MyButton) e.getSource();
            MySound.getInstance().playButtonClick();
            if (mybut.getName().equals("Exit")) {
                System.exit(0);
            } else if (mybut.getName().equals("New Game")) {
                BattleShipFrame battle = BattleShipFrame.getInstance();
                battle.remove(BattleShipFrame.getMenu());
                battle.add(BattleShipFrame.getShipPlace());
                battle.validate();
                battle.repaint();
            } else if (mybut.getName().equals("About")) {
                AboutFrame abo = AboutFrame.getInstance();
                abo.initialize();
            }
            mybut.setColor(Color.RED);
            mybut.setURLImage(getClass().getResource("/Images/button-size2.png"));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof MyButton) {
            MyButton mybut = (MyButton) e.getSource();
            mybut.setColor(Color.ORANGE);
            mybut.setURLImage(getClass().getResource("/Images/button-size3.png"));
            MySound.getInstance().playHoverButtonClick();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof MyButton) {
            MyButton mybut = (MyButton) e.getSource();
            mybut.setColor(Color.RED);
            mybut.setURLImage(getClass().getResource("/Images/button-size2.png"));

        }
    }
}
