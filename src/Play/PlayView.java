/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Play;

import CustomButton.MyButton;
import Exception.BackgroundImageNotFoundException;
import Exception.SeaFieldImageNotFoundException;
import Frame.BattleShipFrame;
import Ship.ShipModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author TanThinh
 */
public class PlayView extends JPanel implements Observer {

    private int setInitial = 0;
    private int level = 0;
    private int turnNumber = 1;
    private boolean status = false;
    private PlayModel playModel = new PlayModel();
    private JLabel playerName = new JLabel("PLAYER");
    private JLabel compName = new JLabel("COMPUTER");
    private JLabel turn;
    private JPanel playerField = new JPanel();
    private JPanel enemyField = new JPanel();
    private MyButton backMain = new MyButton("Back to Menu", Color.RED);
    private PlayController playController = new PlayController(this);
    private JLabel seafield1 = new JLabel();
    private JLabel seafield2 = new JLabel();
    private JLabel background = new JLabel();
    private JTextPane logText = new JTextPane();
    private StyledDocument doc = logText.getStyledDocument();
    private JScrollPane scrollLog = new JScrollPane(logText);
    private ArrayList<JPanel> recieveShip = new ArrayList<JPanel>();
    private JPanel playerStatusBox = new JPanel();
    private JPanel enemyStatusBox = new JPanel();
    private JLabel patrolText = new JLabel("Patrol Boat");
    private JLabel destroyerText = new JLabel("Destroyer");
    private JLabel submarineText = new JLabel("Submarine");
    private JLabel battleshipText = new JLabel("Battleship");
    private JLabel aircraftText = new JLabel("Aircraft Carrier");
    private JLabel patrol2Text = new JLabel("Patrol Boat");
    private JLabel destroyer2Text = new JLabel("Destroyer");
    private JLabel submarine2Text = new JLabel("Submarine");
    private JLabel battleship2Text = new JLabel("Battleship");
    private JLabel aircraft2Text = new JLabel("Aircraft Carrier");
    private JLabel patrolPlayerStatus = new JLabel("Alive");
    private JLabel destroyerPlayerStatus = new JLabel("Alive");
    private JLabel submarinePlayerStatus = new JLabel("Alive");
    private JLabel battleshipPlayerStatus = new JLabel("Alive");
    private JLabel aircraftPlayerStatus = new JLabel("Alive");
    private JLabel patrolEnemyStatus = new JLabel("Alive");
    private JLabel destroyerEnemyStatus = new JLabel("Alive");
    private JLabel submarineEnemyStatus = new JLabel("Alive");
    private JLabel battleshipEnemyStatus = new JLabel("Alive");
    private JLabel aircraftEnemyStatus = new JLabel("Alive");

    public void setShipModelInit(ShipModel mo) {
        this.playModel.setShipmodel(mo);
        recieveShip = playModel.getShipmodel().getPassShip();
        level = playModel.getShipmodel().getLevel();

        for (String t : playModel.getShipmodel().getTotalPlayerShip()) {
            System.out.print(t + " ");
        }
    }

    public void settings() throws BackgroundImageNotFoundException, SeaFieldImageNotFoundException {
        this.setLayout(null);
        playModel.addObserver(this);
        turn = new JLabel("TURN: " + turnNumber);

        URL imagetemp = getClass().getResource("/Images/seaplay3.jpg");
        URL sea1temp = getClass().getResource("/Images/seafield-expanded2.jpg");
        URL sea2temp = getClass().getResource("/Images/seafield-expanded2.jpg");

        if (imagetemp == null) {
            throw new BackgroundImageNotFoundException("No image play found.");
        } else if (sea1temp == null | sea2temp == null) {
            throw new SeaFieldImageNotFoundException("No seafield image found.");
        } else {
            ImageIcon sea1Icon = new ImageIcon(sea1temp);
            ImageIcon sea2Icon = new ImageIcon(sea2temp);
            seafield1 = new JLabel(sea1Icon);
            seafield2 = new JLabel(sea2Icon);
            ImageIcon backgroundIcon = new ImageIcon(imagetemp);
            background = new JLabel(backgroundIcon);
        }



        turn.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        playerName.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        compName.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        logText.setEditable(false);
        logText.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
        logText.setBackground(new Color(244, 244, 244));
        playerStatusBox.setBackground(new Color(102, 179, 255));
        enemyStatusBox.setBackground(new Color(255, 230, 204));

        Style styleRed = logText.addStyle("Red", null);
        Style styleGreen = logText.addStyle("Green", null);
        Style styleBlue = logText.addStyle("Blue", null);
        Style styleOrage = logText.addStyle("Orange", null);
        StyleConstants.setForeground(styleRed, Color.RED);
        StyleConstants.setForeground(styleGreen, new Color(51, 204, 0));
        StyleConstants.setForeground(styleBlue, Color.BLUE);
        StyleConstants.setForeground(styleOrage, Color.ORANGE);

        setBoundsAll();
        playerField.setOpaque(false);
        enemyField.setOpaque(false);

        //Set panel
        playerField.setLayout(new GridLayout(11, 11));
        enemyField.setLayout(new GridLayout(11, 11));

        //Set fields and buttons
        if (setInitial == 0) {
            randomEnemyShip(1);
            randomEnemyShip(2);
            randomEnemyShip(3);
            randomEnemyShip(4);
            randomEnemyShip(5);
            setForeGroundAll();
            for (String t : playModel.getShipmodel().getTotalEnemyShip()) {
                System.out.println("Enemy ship : " + t);
            }
            setPlayerFieldInitialize();
            setEnemyFieldInitialize();
            playController.setRemainingField(playModel.getPlayerPosition());
            backMain.addMouseListener(playController);

        } else {
            for (JPanel a : playModel.getPlayerPosition()) {
                playerField.add(a);
            }
            for (JPanel b : playModel.getEnemyPosition()) {
                enemyField.add(b);
            }
        }
        setInitial = 1;

        //add to this JPanel
        add(playerName);
        add(compName);
        add(turn);
        add(backMain);
        add(playerField);
        add(enemyField);
        add(scrollLog);
        add(patrolText);
        add(destroyerText);
        add(submarineText);
        add(battleshipText);
        add(aircraftText);
        add(patrol2Text);
        add(destroyer2Text);
        add(submarine2Text);
        add(battleship2Text);
        add(aircraft2Text);
        add(patrolPlayerStatus);
        add(destroyerPlayerStatus);
        add(battleshipPlayerStatus);
        add(submarinePlayerStatus);
        add(aircraftPlayerStatus);
        add(patrolEnemyStatus);
        add(destroyerEnemyStatus);
        add(battleshipEnemyStatus);
        add(submarineEnemyStatus);
        add(aircraftEnemyStatus);
        add(playerStatusBox);
        add(enemyStatusBox);
        for (JPanel pa : this.recieveShip) {
            pa.removeMouseListener(BattleShipFrame.getShipPlace().getShipController());
            add(pa);
        }
        add(seafield1);
        add(seafield2);
        add(background);

    }

    @Override
    public void update(Observable o, Object arg) {
        turnNumber++;

        try {
            this.removeAll();
            enemyField.removeAll();
            this.settings();
            turn.setForeground(Color.GREEN);
            this.validate();
            this.repaint();
        } catch (BackgroundImageNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SeaFieldImageNotFoundException e) {
            System.out.println(e.getMessage());
        }


        if (status == true) {
            playController.enemyAttack();
        }

    }

    public void setPlayerFieldInitialize() {
        char mark = 'A';
        int markCount = 1;
        char rowMark = 'A';
        int rowCount = 0;

        for (int i = 0; i < 121; i++) {
            JPanel a = new JPanel();
            a.setBorder(BorderFactory.createLineBorder(Color.blue));

            if (i <= 10 && i != 0) {
                a.add(new JLabel(markCount + ""));
                markCount++;
            } else if (i % 11 == 0 && i != 0) {
                a.add(new JLabel(mark + ""));
                mark++;
            }

            if ((i - 1) % 11 == 0 && i != 1) {
                rowCount = 1;
                a.setName(rowMark + "" + rowCount);
                a.setOpaque(false);
            } else if (i % 11 == 0 && i != 0 && i != 11) {
                rowMark++;
            } else if (!(i >= 0 && i <= 11)) {
                rowCount++;
                a.setName(rowMark + "" + rowCount);
                a.setOpaque(false);
            }

            if (i >= 0 && i <= 10 || i % 11 == 0) {
                a.setBackground(new Color(0, 128, 255));
            }


            playerField.add(a);
            playModel.getPlayerPosition().add(a);
        }
    }

    public void setEnemyFieldInitialize() {
        char mark = 'A';
        int markCount = 1;
        char rowMark = 'A';
        int rowCount = 0;

        for (int i = 0; i < 121; i++) {
            JPanel a = new JPanel();
            a.setBorder(BorderFactory.createLineBorder(Color.red));

            if (i <= 10 && i != 0) {
                a.add(new JLabel(markCount + ""));
                markCount++;
            } else if (i % 11 == 0 && i != 0) {
                a.add(new JLabel(mark + ""));
                mark++;
            }

            if ((i - 1) % 11 == 0 && i != 1) {
                rowCount = 1;
                a.setName(rowMark + "" + rowCount);
                a.setOpaque(false);
                a.addMouseListener(playController);
            } else if (i % 11 == 0 && i != 0 && i != 11) {
                rowMark++;
            } else if (!(i >= 0 && i <= 11)) {
                rowCount++;
                a.setName(rowMark + "" + rowCount);
                a.setOpaque(false);
                a.addMouseListener(playController);
            }

            if (i >= 0 && i <= 10 || i % 11 == 0) {
                a.setBackground(new Color(245, 61, 0));
            }

            enemyField.add(a);
            playModel.getEnemyPosition().add(a);

        }
    }

    public PlayModel getPlayModel() {
        return playModel;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getLevel() {
        return level;
    }

    public StyledDocument getDoc() {
        return doc;
    }

    public JTextPane getLogText() {
        return logText;
    }

    public void setForeGroundAll() {
        turn.setForeground(Color.GREEN);
        playerName.setForeground(Color.BLUE);
        compName.setForeground(Color.RED);
        patrolText.setForeground(Color.BLACK);
        destroyerText.setForeground(Color.BLACK);
        submarineText.setForeground(Color.BLACK);
        battleshipText.setForeground(Color.BLACK);
        aircraftText.setForeground(Color.BLACK);
        patrol2Text.setForeground(Color.BLACK);
        destroyer2Text.setForeground(Color.BLACK);
        submarine2Text.setForeground(Color.BLACK);
        battleship2Text.setForeground(Color.BLACK);
        aircraft2Text.setForeground(Color.BLACK);
        patrolPlayerStatus.setForeground(new Color(51, 152, 0));
        destroyerPlayerStatus.setForeground(new Color(51, 153, 0));
        submarinePlayerStatus.setForeground(new Color(51, 153, 0));
        battleshipPlayerStatus.setForeground(new Color(51, 153, 0));
        aircraftPlayerStatus.setForeground(new Color(51, 153, 0));
        patrolEnemyStatus.setForeground(new Color(51, 204, 51));
        destroyerEnemyStatus.setForeground(new Color(51, 204, 51));
        submarineEnemyStatus.setForeground(new Color(51, 204, 51));
        battleshipEnemyStatus.setForeground(new Color(51, 204, 51));
        aircraftEnemyStatus.setForeground(new Color(51, 204, 51));
    }

    public void setBoundsAll() {
        background.setBounds(0, -100, 1280, 1024);
        playerName.setBounds(255, 20, 150, 50);
        compName.setBounds(845, 20, 150, 50);
        turn.setBounds(560, 20, 120, 50);
        playerField.setBounds(100, 90, 400, 400);
        enemyField.setBounds(700, 90, 400, 400);
        backMain.setBounds(530, 660, 150, 50);
        seafield1.setBounds(98, 105, 400, 400);
        seafield2.setBounds(698, 105, 400, 400);
        scrollLog.setBounds(420, 500, 360, 150);
        playerStatusBox.setBounds(100, 500, 300, 120);
        enemyStatusBox.setBounds(800, 500, 300, 120);
        patrolText.setBounds(120, 500, 100, 50);
        destroyerText.setBounds(220, 500, 100, 50);
        submarineText.setBounds(320, 500, 100, 50);
        battleshipText.setBounds(120, 550, 100, 50);
        aircraftText.setBounds(220, 550, 100, 50);
        patrol2Text.setBounds(820, 500, 100, 50);
        destroyer2Text.setBounds(920, 500, 100, 50);
        submarine2Text.setBounds(1020, 500, 100, 50);
        battleship2Text.setBounds(820, 550, 100, 50);
        aircraft2Text.setBounds(920, 550, 100, 50);
        patrolPlayerStatus.setBounds(140, 525, 100, 50);
        destroyerPlayerStatus.setBounds(240, 525, 100, 50);
        submarinePlayerStatus.setBounds(340, 525, 100, 50);
        battleshipPlayerStatus.setBounds(140, 575, 100, 50);
        aircraftPlayerStatus.setBounds(240, 575, 100, 50);
        patrolEnemyStatus.setBounds(840, 525, 100, 50);
        destroyerEnemyStatus.setBounds(940, 525, 100, 50);
        submarineEnemyStatus.setBounds(1040, 525, 100, 50);
        battleshipEnemyStatus.setBounds(840, 575, 100, 50);
        aircraftEnemyStatus.setBounds(940, 575, 100, 50);
    }

    public void setDeadStatus(JLabel la) {
        la.setForeground(Color.RED);
        la.setText("Dead");
    }

    public JLabel getAllStatus(int num) {
        if (num == 1) {
            return patrolPlayerStatus;
        } else if (num == 2) {
            return destroyerPlayerStatus;
        } else if (num == 3) {
            return submarinePlayerStatus;
        } else if (num == 4) {
            return battleshipPlayerStatus;
        } else if (num == 5) {
            return aircraftPlayerStatus;
        } else if (num == 6) {
            return patrolEnemyStatus;
        } else if (num == 7) {
            return destroyerEnemyStatus;
        } else if (num == 8) {
            return submarineEnemyStatus;
        } else if (num == 9) {
            return battleshipEnemyStatus;
        } else if (num == 10) {
            return aircraftEnemyStatus;
        }
        return null;
    }

    public ArrayList<String> getAllField() {
        ArrayList<String> toReturn = new ArrayList<String>();
        char temp = 'A';
        int count = 0;

        for (int i = 1; i <= 100; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }
            String save = "" + temp + count;
            toReturn.add(save);
        }
        return toReturn;
    }

    public void refreshAllEnemyShips() {
        this.playModel.getShipmodel().setTotalEnemyShip(new ArrayList<String>());
        this.playModel.getShipmodel().combineShip(playModel.getShipmodel().getTotalEnemyShip());
    }

    public void addingShipToModel(ArrayList<String> theship, int size) {
        if (size == 1) {
            playModel.getShipmodel().getEnemyShip1().addAll(theship);
            refreshAllEnemyShips();
        } else if (size == 2) {
            playModel.getShipmodel().getEnemyShip2().addAll(theship);
            refreshAllEnemyShips();
        } else if (size == 3) {
            playModel.getShipmodel().getEnemyShip3().addAll(theship);
            refreshAllEnemyShips();
        } else if (size == 4) {
            playModel.getShipmodel().getEnemyShip4().addAll(theship);
            refreshAllEnemyShips();
        } else if (size == 5) {
            playModel.getShipmodel().getEnemyShip5().addAll(theship);
            refreshAllEnemyShips();
        }

    }

    public void randomEnemyShip(int size) {
        ArrayList<String> allfield = getAllField();
        Random ran = new Random();
        int num = ran.nextInt(allfield.size());

        String thefield = allfield.get(num);
        for (String t : playModel.getShipmodel().getTotalEnemyShip()) {
            if (thefield.equals(t)) {
                randomEnemyShip(size);
                return;
            }
        }

        int shipCounter = 0;

        if (size == 1) {
            shipCounter = 2;
        } else if (size == 2 || size == 3) {
            shipCounter = 3;
        } else if (size == 4) {
            shipCounter = 4;
        } else if (size == 5) {
            shipCounter = 5;
        }

        int co = 0;
        char temp = 'A';
        int count = 0;
        char rem = 'A';
        int countRem = 0;
        boolean con = false;
        int conver = ran.nextInt(2);
        ArrayList<String> theship = new ArrayList<String>();

        int checkCol = 0;
        for (int i = 1; i <= 100; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }
            String save = "" + temp + count;

            if (con) {
                if (conver == 0) {
                    if (rem == temp) {
                        for (String t : playModel.getShipmodel().getTotalEnemyShip()) {
                            if (save.equals(t)) {
                                randomEnemyShip(size);
                                return;
                            }
                        }
                        theship.add(save);
                        co++;
                        if (co == shipCounter) {
                            addingShipToModel(theship, size);
                            return;
                        }
                        if (i == 100 && co < shipCounter) {
                            randomEnemyShip(size);
                            return;
                        }
                    } else {
                        randomEnemyShip(size);
                        return;
                    }
                } else {
                    checkCol++;
                    if (checkCol % 10 == 0) {
                        if (countRem == count) {
                            for (String t : playModel.getShipmodel().getTotalEnemyShip()) {
                                if (save.equals(t)) {
                                    randomEnemyShip(size);
                                    return;
                                }
                            }
                            theship.add(save);
                            co++;

                            if (co == shipCounter) {
                                addingShipToModel(theship, size);
                                return;
                            }
                            if ((i >= 91 && i <= 100) && co < shipCounter) {
                                randomEnemyShip(size);
                                return;
                            }
                        } else {
                            randomEnemyShip(size);
                            return;
                        }
                    }

                }
            }

            if (save.equals(thefield)) {
                theship.add(save);
                rem = temp;
                countRem = count;
                con = true;
                co++;
                if (conver == 0) {
                    if (i == 100 && co < shipCounter) {
                        randomEnemyShip(size);
                        return;
                    }
                } else {
                    if ((i >= 91 && i <= 100) && co < shipCounter) {
                        randomEnemyShip(size);
                        return;
                    }
                }


            }
        }
    }
}
