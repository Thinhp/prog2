/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShipPlace;

import CustomButton.MyButton;
import Exception.BackgroundImageNotFoundException;
import Exception.SeaFieldImageNotFoundException;
import Exception.ShipImageNotFoundException;
import Frame.BattleShipFrame;
import Play.PlayView;
import Sound.MySound;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author TanThinh
 */
public class ShipPlaceController implements MouseListener, ActionListener {

    private ShipPlaceView viewUnique;
    private Color background = Color.red;

    public ShipPlaceController(ShipPlaceView s) {
        this.viewUnique = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JRadioButton) {
            JRadioButton ra = (JRadioButton) e.getSource();
            MySound.getInstance().playDifficultClick();
            if (ra.getActionCommand().equals("Easy")) {
                viewUnique.getShipPlaceModel().getShipmodel().setLevel(1);
            }
            if (ra.getActionCommand().equals("Hard")) {
                viewUnique.getShipPlaceModel().getShipmodel().setLevel(2);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JPanel) {
            JPanel pa = (JPanel) e.getSource();

            if (pa.getName().equals("patrol")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(1);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("patrolver")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(2);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("destroyer")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(3);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("destroyerver")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(4);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("sub")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(5);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("subver")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(6);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("battleship")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(7);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("battleshipver")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(8);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("air")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(9);
                pa.setBackground(Color.green);
                background = Color.green;
            } else if (pa.getName().equals("airver")) {
                this.viewUnique.getShipPlaceModel().setChosenShip(10);
                pa.setBackground(Color.green);
                background = Color.green;
            } else {
                if (viewUnique.getShipPlaceModel().getChosenShip() == 0 && viewUnique.getShipPlaceModel().getCheckAllShip() == 5) {
                    JOptionPane.showMessageDialog(null, "Press Next to play.");
                } else if (this.viewUnique.getShipPlaceModel().getChosenShip() == 0) {
                    JOptionPane.showMessageDialog(null, "Please choose a ship to add");
                } else {
                    MySound.getInstance().playPlacingShip();
                    int buttonX = pa.getX();
                    int buttonY = pa.getY();
                    this.viewUnique.getShipPlaceModel().SetCor(buttonX, buttonY, pa.getName());
                }
            }


        }
        if (e.getSource() instanceof MyButton) {
            MyButton bu = (MyButton) e.getSource();
            MySound.getInstance().playButtonClick();
            if (bu.getName().equals("Back")) {
                BattleShipFrame battle = BattleShipFrame.getInstance();
                battle.remove(BattleShipFrame.getShipPlace());
                BattleShipFrame.setShipPlace(new ShipPlaceView());
                BattleShipFrame.setPlay(new PlayView());
                try {
                    BattleShipFrame.getShipPlace().settings();
                } catch (BackgroundImageNotFoundException c) {
                    System.out.println(c.getMessage());
                } catch (SeaFieldImageNotFoundException b) {
                    System.out.println(b.getMessage());
                } catch (ShipImageNotFoundException c) {
                    System.out.println(c.getMessage());
                }

                battle.add(BattleShipFrame.getMenu());
                battle.validate();
                battle.repaint();
            }

            if (bu.getName().equals("Next")) {
                if(viewUnique.getShipPlaceModel().getShipmodel().getLevel() == 0){
                    JOptionPane.showMessageDialog(null, "Please choose difficulty.");
                }else if(viewUnique.getShipPlaceModel().getCheckAllShip() == 5){
                BattleShipFrame battle = BattleShipFrame.getInstance();
                battle.remove(BattleShipFrame.getShipPlace());
                this.viewUnique.getShipPlaceModel().getShipmodel().setTotalPlayerShip(new ArrayList<String>());
                this.viewUnique.getShipPlaceModel().getShipmodel().combineShip(viewUnique.getShipPlaceModel().getShipmodel().getTotalPlayerShip());
                BattleShipFrame.getPlay().setShipModelInit(viewUnique.getShipPlaceModel().getShipmodel());
                try {
                    BattleShipFrame.getPlay().settings();
                } catch (BackgroundImageNotFoundException b) {
                    System.out.println(b.getMessage());
                } catch (SeaFieldImageNotFoundException d) {
                    System.out.println(d.getMessage());
                }
                battle.add(BattleShipFrame.getPlay());
                battle.validate();
                battle.repaint();
                }else {
                    JOptionPane.showMessageDialog(null, "Please place all five ships in the field.");
                }
                
            }

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
        if (e.getSource() instanceof JPanel) {
            JPanel b = (JPanel) e.getSource();
            b.setOpaque(true);
            background = b.getBackground();
            b.setBackground(Color.orange);
        }

        if (e.getSource() instanceof MyButton) {
            MyButton mybut = (MyButton) e.getSource();
            mybut.setColor(Color.ORANGE);
            mybut.setURLImage(getClass().getResource("/Images/button-size3.png"));
            MySound.getInstance().playHoverButtonClick();

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JPanel) {
            JPanel b = (JPanel) e.getSource();
            b.setOpaque(false);
            b.setBackground(background);
        }

        if (e.getSource() instanceof MyButton) {
            MyButton mybut = (MyButton) e.getSource();
            mybut.setColor(Color.RED);
            mybut.setURLImage(getClass().getResource("/Images/button-size2.png"));

        }
    }
}
