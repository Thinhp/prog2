/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import CustomButton.MyButton;
import Exception.BackgroundImageNotFoundException;
import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author TanThinh
 */
public class MenuView extends JPanel {

    private static MenuView unique;
    private JPanel wrapall = new JPanel();
    private MyButton single = new MyButton("New Game", Color.RED);
    private MyButton about = new MyButton("About", Color.RED);
    private MyButton exit = new MyButton("Exit", Color.RED);
    private JLabel background = new JLabel();
    private MenuController controller = new MenuController();

    public static MenuView getInstance() {
        if (unique == null) {
            unique = new MenuView();
        }
        return unique;
    }

    public void settings() throws BackgroundImageNotFoundException {

        this.setLayout(null);
        wrapall.setLayout(null);
        wrapall.setBounds(540, 480, 150, 250);


        //Set Background 
        URL imagetemp = getClass().getResource("/Images/BattleShipBackground10.jpg");

        if (imagetemp == null) {
            throw new BackgroundImageNotFoundException("No background image found.");
        } else {
            ImageIcon backgroundIcon = new ImageIcon(imagetemp);
            background = new JLabel(backgroundIcon);
        }

        //Set bounds 
        single.setBounds(0, 0, 150, 50);
        about.setBounds(0, 70, 150, 50);
        exit.setBounds(0, 140, 150, 50);
        background.setBounds(0, -100, 1280, 1024);

        //add to wrap panel
        wrapall.add(single);
        wrapall.add(about);
        wrapall.add(exit);

        //Set Opaque
        wrapall.setOpaque(false);


        //add Action
        exit.addMouseListener(controller);
        single.addMouseListener(controller);
        about.addMouseListener(controller);

        unique.add(wrapall);
        unique.add(background);

    }
}