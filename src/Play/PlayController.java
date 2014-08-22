package Play;

import CustomButton.MyButton;
import Exception.BackgroundImageNotFoundException;
import Exception.SeaFieldImageNotFoundException;
import Exception.ShipImageNotFoundException;
import Frame.BattleShipFrame;
import ShipPlace.ShipPlaceView;
import Sound.MySound;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author TanThinh
 */
public class PlayController implements MouseListener {

    private static PlayView playViewUnique;
    private ArrayList<JPanel> playerRemainingField = new ArrayList<JPanel>();
    private ArrayList<String> position = new ArrayList<String>();
    private ArrayList<ArrayList<String>> rememberPosition = new ArrayList<ArrayList<String>>();
    private JLabel aim = new JLabel();
    private JLabel missfire = new JLabel();
    private JLabel hitfire = new JLabel();
    private String currentPos = "";
    private boolean shootCon = false;
    private boolean shootDirection = false;
    private boolean turnHead = false;

    public PlayController(PlayView v) {
        playViewUnique = v;
    }

    public void setRemainingField(ArrayList<JPanel> g) {
        for (JPanel pa : g) {
            if (pa.getName() != null) {
                playerRemainingField.add(pa);
            }
        }

    }

    public void enemyAttack() {
        if (playViewUnique.getLevel() == 1) {
            easyLevel();
        } else {
            hardLevel();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JPanel) {
            int check = 0;
            JPanel b = (JPanel) e.getSource();
            b.setOpaque(false);
            b.remove(aim);

            missfire = new JLabel(new ImageIcon(getClass().getResource("/Images/missfire-pts.png")));
            missfire.setBounds(0, 0, 36, 36);
            hitfire = new JLabel(new ImageIcon(getClass().getResource("/Images/hitfire-pts.png")));
            hitfire.setBounds(0, 0, 36, 36);

            String hitlog = "-You have hit an enemy's ship at " + b.getName();
            String misslog = "-You have fired at " + b.getName();


            String toRemove = "";
            for (String t : playViewUnique.getPlayModel().getShipmodel().getTotalEnemyShip()) {
                if (t.equals(b.getName())) {
                    toRemove = t;
                    break;
                }
            }

            if (!toRemove.equals("")) {
                playViewUnique.getPlayModel().getShipmodel().getTotalEnemyShip().remove(toRemove);
                playViewUnique.getPlayModel().getShipmodel().removeEnemyShipIndividual(toRemove);
                b.add(hitfire);
                playViewUnique.setStatus(false);
                MySound.getInstance().playHitFire();
                try {

                    if (playViewUnique.getPlayModel().getShipmodel().getEnemyShip1().isEmpty()) {
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Green"));
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-You have destroyed enemy's Patrol boat " + "\n", playViewUnique.getLogText().getStyle("Blue"));
                        playViewUnique.getPlayModel().getShipmodel().getEnemyShip1().add("done");
                        playViewUnique.setDeadStatus(playViewUnique.getAllStatus(6));
                    } else if (playViewUnique.getPlayModel().getShipmodel().getEnemyShip2().isEmpty()) {
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Green"));
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-You have destroyed enemy's Destroyer " + "\n", playViewUnique.getLogText().getStyle("Blue"));
                        playViewUnique.getPlayModel().getShipmodel().getEnemyShip2().add("done");
                        playViewUnique.setDeadStatus(playViewUnique.getAllStatus(7));
                    } else if (playViewUnique.getPlayModel().getShipmodel().getEnemyShip3().isEmpty()) {
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Green"));
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-You have destroyed enemy's Submarine " + "\n", playViewUnique.getLogText().getStyle("Blue"));
                        playViewUnique.getPlayModel().getShipmodel().getEnemyShip3().add("done");
                        playViewUnique.setDeadStatus(playViewUnique.getAllStatus(8));
                    } else if (playViewUnique.getPlayModel().getShipmodel().getEnemyShip4().isEmpty()) {
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Green"));
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-You have destroyed enemy's Battleship" + "\n", playViewUnique.getLogText().getStyle("Blue"));
                        playViewUnique.getPlayModel().getShipmodel().getEnemyShip4().add("done");
                        playViewUnique.setDeadStatus(playViewUnique.getAllStatus(9));
                    } else if (playViewUnique.getPlayModel().getShipmodel().getEnemyShip5().isEmpty()) {
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Green"));
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-You have destroyed enemy's Aircraft Carrier" + "\n", playViewUnique.getLogText().getStyle("Blue"));
                        playViewUnique.getPlayModel().getShipmodel().getEnemyShip5().add("done");
                        playViewUnique.setDeadStatus(playViewUnique.getAllStatus(10));
                    } else {
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Green"));
                    }
                } catch (Exception d) {
                    System.out.println(d.getMessage());
                }
            } else {
                b.add(missfire);
                playViewUnique.setStatus(true);
                MySound.getInstance().playMissFire();
                try {
                    playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), misslog + "\n", null);
                } catch (Exception d) {
                    System.out.println(d.getMessage());
                }
            }

            if (playViewUnique.getPlayModel().getShipmodel().getTotalEnemyShip().isEmpty()) {
                check = 1;
                playerWinMessage();
            }

            if (check == 0) {
                for (JPanel pa : playViewUnique.getPlayModel().getEnemyPosition()) {
                    if (b == pa) {
                        b.setName(null);
                        break;
                    }
                }

                b.removeMouseListener(this);
                playViewUnique.getPlayModel().change();
            }


        }

        if (e.getSource() instanceof MyButton) {
            MyButton bu = (MyButton) e.getSource();
            if (bu.getName().equals("Back to Menu")) {
                MySound.getInstance().playButtonClick();
                int option = JOptionPane.showConfirmDialog(null, "You will lose the current game if you go back.", "Are you sure?", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    BattleShipFrame battle = BattleShipFrame.getInstance();
                    battle.remove(BattleShipFrame.getPlay());
                    BattleShipFrame.setShipPlace(new ShipPlaceView());
                    BattleShipFrame.setPlay(new PlayView());

                    try {
                        BattleShipFrame.getShipPlace().settings();

                    } catch (BackgroundImageNotFoundException c) {
                        System.out.println(c.getMessage());
                    } catch (SeaFieldImageNotFoundException d) {
                        System.out.println(d.getMessage());
                    } catch (ShipImageNotFoundException c) {
                        System.out.println(c.getMessage());
                    }

                    battle.add(BattleShipFrame.getMenu());
                    battle.validate();
                    battle.repaint();
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JPanel) {
            JPanel b = (JPanel) e.getSource();
            b.setOpaque(false);
            b.setLayout(null);
            aim = new JLabel(new ImageIcon(getClass().getResource("/Images/aim-icon-pts.png")));
            aim.setBounds(0, 0, 36, 36);
            b.add(aim);
            playViewUnique.validate();
            playViewUnique.repaint();
        }

        if (e.getSource() instanceof MyButton) {
            MyButton mybut = (MyButton) e.getSource();
            MySound.getInstance().playHoverButtonClick();
            mybut.setColor(Color.ORANGE);
            mybut.setURLImage(getClass().getResource("/Images/button-size3.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JPanel) {
            JPanel b = (JPanel) e.getSource();
            b.setOpaque(false);
            b.remove(aim);
            playViewUnique.validate();
            playViewUnique.repaint();
        }

        if (e.getSource() instanceof MyButton) {
            MyButton mybut = (MyButton) e.getSource();
            MySound.getInstance().playHoverButtonClick();
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

    public void easyLevel() {

        Random ran = new Random();
        int num = ran.nextInt(playerRemainingField.size());

        for (int i = 0; i < playerRemainingField.size(); i++) {
            if (i == num) {
                int check = 0;
                JPanel b = playerRemainingField.get(i);
                b.setOpaque(false);
                b.setLayout(null);
                missfire = new JLabel(new ImageIcon(getClass().getResource("/Images/missfire-pts.png")));
                missfire.setBounds(0, 0, 36, 36);
                hitfire = new JLabel(new ImageIcon(getClass().getResource("/Images/hitfire-pts.png")));
                hitfire.setBounds(0, 0, 36, 36);

                String toRemove = "";
                for (String t : playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip()) {
                    if (t.equals(b.getName())) {
                        toRemove = t;
                        break;
                    }
                }

                String hitlog = "-The enemy has hit an your ship at " + b.getName();
                String misslog = "-The enemy has fired at " + b.getName();

                if (!toRemove.equals("")) {
                    playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip().remove(toRemove);
                    playViewUnique.getPlayModel().getShipmodel().removePlayerShipIndividual(toRemove);
                    b.add(hitfire);
                    playViewUnique.setStatus(true);
                    MySound.getInstance().playHitFire();
                    try {

                        if (playViewUnique.getPlayModel().getShipmodel().getPlayerShip1().isEmpty()) {
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Orange"));
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-The enemy has destroyed your Patrol boat " + "\n", playViewUnique.getLogText().getStyle("Red"));
                            playViewUnique.getPlayModel().getShipmodel().getPlayerShip1().add("done");
                            playViewUnique.setDeadStatus(playViewUnique.getAllStatus(1));
                        } else if (playViewUnique.getPlayModel().getShipmodel().getPlayerShip2().isEmpty()) {
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Orange"));
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-The enemy has destroyed your Destroyer " + "\n", playViewUnique.getLogText().getStyle("Red"));
                            playViewUnique.getPlayModel().getShipmodel().getPlayerShip2().add("done");
                            playViewUnique.setDeadStatus(playViewUnique.getAllStatus(2));
                        } else if (playViewUnique.getPlayModel().getShipmodel().getPlayerShip3().isEmpty()) {
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Orange"));
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-The enemy has destroyed your Submarine " + "\n", playViewUnique.getLogText().getStyle("Red"));
                            playViewUnique.getPlayModel().getShipmodel().getPlayerShip3().add("done");
                            playViewUnique.setDeadStatus(playViewUnique.getAllStatus(3));
                        } else if (playViewUnique.getPlayModel().getShipmodel().getPlayerShip4().isEmpty()) {
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Orange"));
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-The enemy has destroyed your Battleship" + "\n", playViewUnique.getLogText().getStyle("Red"));
                            playViewUnique.getPlayModel().getShipmodel().getPlayerShip4().add("done");
                            playViewUnique.setDeadStatus(playViewUnique.getAllStatus(4));
                        } else if (playViewUnique.getPlayModel().getShipmodel().getPlayerShip5().isEmpty()) {
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Orange"));
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), "-The enemy has destroyed your Aircraft Carrier" + "\n", playViewUnique.getLogText().getStyle("Red"));
                            playViewUnique.getPlayModel().getShipmodel().getPlayerShip5().add("done");
                            playViewUnique.setDeadStatus(playViewUnique.getAllStatus(5));
                        } else {
                            playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), hitlog + "\n", playViewUnique.getLogText().getStyle("Orange"));
                        }
                    } catch (Exception d) {
                        System.out.println(d.getMessage());
                    }
                } else {
                    b.add(missfire);
                    playViewUnique.setStatus(false);

                    try {
                        playViewUnique.getDoc().insertString(playViewUnique.getDoc().getLength(), misslog + "\n", null);
                    } catch (Exception d) {
                        System.out.println(d.getMessage());
                    }
                }

                if (playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip().isEmpty()) {
                    if (playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip().isEmpty()) {
                        check = 1;
                        playerLoseMessage();
                    }
                }

                if (check == 0) {
                    for (JPanel pa : playViewUnique.getPlayModel().getPlayerPosition()) {
                        if (b == pa) {
                            b.setName(null);
                            break;
                        }
                    }

                    playerRemainingField.remove(b);
                    System.out.println(playerRemainingField.size());
                    playViewUnique.getPlayModel().change();
                    break;
                }

            }
        }
    }

    public void hardLevel() {
        Random ran = new Random();

        missfire = new JLabel(new ImageIcon(getClass().getResource("/Images/missfire-pts.png")));
        missfire.setBounds(0, 0, 36, 36);
        hitfire = new JLabel(new ImageIcon(getClass().getResource("/Images/hitfire-pts.png")));
        hitfire.setBounds(0, 0, 36, 36);

        if (position.isEmpty()) {
            shootCon = false;
        }

        if (shootDirection) {
            JPanel b = new JPanel();
            String toRemove = "";
            String posi = "";
            if (!rememberPosition.isEmpty()) {
                posi = rememberPosition.get(0).get(0);
                for (String t : playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip()) {
                    if (t.equals(posi)) {
                        toRemove = t;
                        break;
                    }
                }

            } else {
                shootDirection = false;
                shootCon = false;
                hardLevel();
                return;
            }

            for (int i = 0; i < playerRemainingField.size(); i++) {
                if (playerRemainingField.get(i).getName().equals(posi)) {
                    b = playerRemainingField.get(i);
                    break;
                }
            }

            b.setOpaque(false);
            b.setLayout(null);

            if (!toRemove.equals("")) {
                playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip().remove(toRemove);
                b.add(hitfire);
                playViewUnique.setStatus(true);
            } else {
                b.add(missfire);
                playViewUnique.setStatus(false);
            }

            playerRemainingField.remove(b);
            playViewUnique.getPlayModel().change();
        } else if (shootCon) {
            String posi = "";
            String toRemove = "";
            JPanel b = new JPanel();
            b.setName("no");
            int pos = ran.nextInt(position.size());
            if (pos == 0) {
                posi = position.get(0);
            } else if (pos == 1) {
                posi = position.get(1);
            } else if (pos == 2) {
                posi = position.get(2);
            } else if (pos == 3) {
                posi = position.get(3);
            }

            for (String t : playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip()) {
                if (t.equals(posi)) {
                    toRemove = t;
                    break;
                }
            }

            for (int i = 0; i < playerRemainingField.size(); i++) {
                if (playerRemainingField.get(i).getName().equals(posi)) {
                    b = playerRemainingField.get(i);
                    break;
                }
            }

            if (b.getName().equals("no")) {
                position.remove(posi);
                hardLevel();
                return;
            }


            b.setOpaque(false);
            b.setLayout(null);

            if (!toRemove.equals("")) {

                b.add(hitfire);

                String north = returnNorth(currentPos);
                String south = returnSouth(currentPos);
                String west = returnWest(currentPos);
                String east = returnEast(currentPos);
                position.removeAll(position);

                if (b.getName().equals(north)) {
                    positionRemember(north);
                    positionRemember(south);
                } else if (b.getName().equals(south)) {
                    positionRemember(south);
                    positionRemember(north);
                } else if (b.getName().equals(west)) {
                    positionRemember(west);
                    positionRemember(east);
                } else if (b.getName().equals(east)) {
                    positionRemember(east);
                    positionRemember(west);
                }

                for (int i = 0; i < rememberPosition.size(); i++) {
                    for (int j = 0; j < rememberPosition.get(i).size(); j++) {
                        boolean checkremove = true;
                        for (int z = 0; z < playerRemainingField.size(); z++) {
                            if (rememberPosition.get(i).get(j).equals(playerRemainingField.get(z).getName())) {
                                checkremove = false;
                                break;
                            }
                        }
                        if (checkremove) {
                            rememberPosition.get(i).remove(rememberPosition.get(i).get(j));
                        }
                    }
                }

                shootCon = false;
                shootDirection = true;
                playViewUnique.setStatus(true);
            } else {
                b.add(missfire);
                playViewUnique.setStatus(false);
            }
            playerRemainingField.remove(b);
            playViewUnique.getPlayModel().change();
        } else {
            int num = ran.nextInt(playerRemainingField.size());

            for (int i = 0; i < playerRemainingField.size(); i++) {
                if (i == num) {
                    int check = 0;
                    JPanel b = playerRemainingField.get(i);
                    b.setOpaque(false);
                    b.setLayout(null);

                    String toRemove = "";
                    for (String t : playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip()) {
                        if (t.equals(b.getName())) {
                            toRemove = t;
                            break;
                        }
                    }
                    if (!toRemove.equals("")) {
                        String north = returnNorth(toRemove);
                        String south = returnSouth(toRemove);
                        String east = returnEast(toRemove);
                        String west = returnWest(toRemove);
                        position.add(north);
                        position.add(south);
                        position.add(east);
                        position.add(west);
                        shootCon = true;
                        currentPos = toRemove;
                        playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip().remove(toRemove);
                        b.add(hitfire);
                        playViewUnique.setStatus(true);
                    } else {
                        b.add(missfire);
                        playViewUnique.setStatus(false);
                    }

                    if (playViewUnique.getPlayModel().getShipmodel().getTotalPlayerShip().isEmpty()) {
                        check = 1;
                        playerLoseMessage();
                    }

                    if (check == 0) {
                        for (JPanel pa : playViewUnique.getPlayModel().getPlayerPosition()) {
                            if (b == pa) {
                                b.setName(null);
                                break;
                            }
                        }

                        playerRemainingField.remove(b);
                        playViewUnique.getPlayModel().change();
                        break;
                    }

                }
            }
        }

    }

    public void positionRemember(String pos) {
        String temp = pos;
        ArrayList<String> templist = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            String toAdd = returnNorth(temp);
            templist.add(toAdd);
            temp = toAdd;
        }
        rememberPosition.add(templist);
    }

    //Return the top cordinate of the given string
    public String returnNorth(String pos) {
        char temp = 'A';
        int count = 0;

        String toReturn = "";
        for (int i = 1; i <= 100; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }

            String save = "" + temp + count;
            if (save.equals(pos)) {
                if (temp != 'A') {
                    char minus = --temp;
                    toReturn = "" + minus + count;
                    return toReturn;
                } else {
                    break;
                }
            }
        }
        return "";

    }

    //Return the bottom cordinate of the given string
    public String returnSouth(String pos) {
        char temp = 'A';
        int count = 0;

        String toReturn = "";
        for (int i = 1; i <= 100; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }

            String save = "" + temp + count;
            if (save.equals(pos)) {
                if (temp != 'J') {
                    char plus = ++temp;
                    toReturn = "" + plus + count;
                    return toReturn;
                } else {
                    break;
                }
            }
        }
        return "";
    }

    //Return the left cordinate of the given string
    public String returnWest(String pos) {
        char temp = 'A';
        int count = 0;

        String toReturn = "";
        for (int i = 1; i <= 100; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }

            String save = "" + temp + count;
            if (save.equals(pos)) {
                if (count != 1) {
                    int minus = count - 1;
                    toReturn = "" + temp + minus;
                    return toReturn;
                } else {
                    break;
                }
            }
        }
        return "";
    }

    //Return the right cordinate of the given string
    public String returnEast(String pos) {
        char temp = 'A';
        int count = 0;

        String toReturn = "";
        for (int i = 1; i <= 100; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }

            String save = "" + temp + count;
            if (save.equals(pos)) {
                if (count != 10) {
                    int plus = count + 1;
                    toReturn = "" + temp + plus;
                    return toReturn;
                } else {
                    break;
                }
            }
        }
        return "";
    }

    public void playerLoseMessage() {
        String[] choices = {"OK"};
        int mychoice = JOptionPane.showOptionDialog(null, "You lose! Too bad :(! Click OK to back to main menu", "Game message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, "OK");
        if (mychoice == 0) {
            BattleShipFrame battle = BattleShipFrame.getInstance();
            battle.remove(BattleShipFrame.getPlay());
            BattleShipFrame.setShipPlace(new ShipPlaceView());
            BattleShipFrame.setPlay(new PlayView());

            try {
                BattleShipFrame.getShipPlace().settings();

            } catch (BackgroundImageNotFoundException c) {
                System.out.println(c.getMessage());
            } catch (SeaFieldImageNotFoundException d) {
                System.out.println(d.getMessage());
            } catch (ShipImageNotFoundException c) {
                System.out.println(c.getMessage());
            }
            battle.add(BattleShipFrame.getMenu());
            battle.validate();
            battle.repaint();
        }
    }

    public void playerWinMessage() {
        String[] choices = {"OK"};
        int mychoice = JOptionPane.showOptionDialog(null, "You win! Amazing! Click OK to back to main menu", "Game message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, "OK");
        if (mychoice == 0) {
            BattleShipFrame battle = BattleShipFrame.getInstance();
            battle.remove(BattleShipFrame.getPlay());
            BattleShipFrame.setShipPlace(new ShipPlaceView());
            BattleShipFrame.setPlay(new PlayView());

            try {
                BattleShipFrame.getShipPlace().settings();

            } catch (BackgroundImageNotFoundException c) {
                System.out.println(c.getMessage());
            } catch (SeaFieldImageNotFoundException d) {
                System.out.println(d.getMessage());
            } catch (ShipImageNotFoundException c) {
                System.out.println(c.getMessage());
            }

            battle.add(BattleShipFrame.getMenu());
            battle.validate();
            battle.repaint();
        }
    }

    public static void waitTime(int time) {
        long time1;
        long time2;
        time1 = System.currentTimeMillis();
        do {
            time2 = System.currentTimeMillis();
        } while ((time2 - time1) < time * 1000);
    }
}
