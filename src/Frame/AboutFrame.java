/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author TanThinh
 */
public class AboutFrame extends JFrame {

    private static AboutFrame unique;
    private JPanel wrap = new JPanel();

    public static AboutFrame getInstance() {
        if (unique == null) {
            unique = new AboutFrame();
        }
        return unique;
    }

    public void initialize() {
        this.setLayout(new GridLayout(6, 1));
        JLabel text = new JLabel("                                                                                 Battle Ship - Stormy War ver 1.2.4.");
        JLabel text2 = new JLabel("                                                                                                     Rules to play:");
        JLabel text3 = new JLabel("              - Each player will have 5 ships with different size and player must place all of them on the field.");
        JLabel text4 = new JLabel("              - Each player will take turn to shoot at the enemy's field. Messages and status will display in game to assist player.");
        JLabel text5 = new JLabel("              - If player hit an enemy's ship. They allow to continue shooting until they miss the shoot.");
        JLabel text6 = new JLabel("              - Whover clear our all the ships of the enemy field will be the winner.");
        add(text);
        add(text2);
        add(text3);
        add(text4);
        add(text5);
        add(text6);

        setTitle("About");
        setSize(700, 400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
