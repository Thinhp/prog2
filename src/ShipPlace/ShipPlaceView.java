/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShipPlace;

import CustomButton.MyButton;
import Exception.BackgroundImageNotFoundException;
import Exception.SeaFieldImageNotFoundException;
import Exception.ShipImageNotFoundException;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author TanThinh
 */
public class ShipPlaceView extends JPanel implements Observer {

    private int setInitial = 0;
    private int chosenShip = 0;
    private int[] cor = new int[2];
    private ShipPlaceModel shipPlaceModel = new ShipPlaceModel();
    private ShipPlaceController shipController = new ShipPlaceController(this);
    private MyButton next = new MyButton("Next", Color.RED);
    private MyButton backMain = new MyButton("Back", Color.RED);
    private JRadioButton easy = new JRadioButton("Beginner (Easy)");
    private JRadioButton hard = new JRadioButton("Challenge (Hard)");
    private ButtonGroup group = new ButtonGroup();
    private JLabel background = new JLabel();
    private JLabel seafield = new JLabel();
    private JLabel chooseLevel = new JLabel("Choose difficulty:");
    private JLabel patrolLabel = new JLabel("Patrol Boat");
    private JLabel destroyerLabel = new JLabel("Destroyer");
    private JLabel subLabel = new JLabel("Submarine");
    private JLabel battleshipLabel = new JLabel("Battleship");
    private JLabel airLabel = new JLabel("Aircraft Carrier");
    private JPanel playerField = new JPanel();
    private JPanel shipChoosePanel = new JPanel();
    private JPanel patrolChoose = new JPanel();
    private JPanel patrolChooseVer = new JPanel();
    private JPanel destroyerChoose = new JPanel();
    private JPanel destroyerChooseVer = new JPanel();
    private JPanel subChoose = new JPanel();
    private JPanel subChooseVer = new JPanel();
    private JPanel battleshipChoose = new JPanel();
    private JPanel battleshipChooseVer = new JPanel();
    private JPanel airChoose = new JPanel();
    private JPanel airChooseVer = new JPanel();

    public void settings() throws BackgroundImageNotFoundException, SeaFieldImageNotFoundException, ShipImageNotFoundException {
        this.setLayout(null);
        shipPlaceModel.addObserver(this);

        //Settings
        chooseLevel.setForeground(Color.ORANGE);
        chooseLevel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        easy.setForeground(Color.green);
        easy.setActionCommand("Easy");
        hard.setForeground(Color.red);
        hard.setActionCommand("Hard");

        patrolChoose.setName("patrol");
        patrolChooseVer.setName("patrolver");
        destroyerChoose.setName("destroyer");
        destroyerChooseVer.setName("destroyerver");
        subChoose.setName("sub");
        subChooseVer.setName("subver");
        battleshipChoose.setName("battleship");
        battleshipChooseVer.setName("battleshipver");
        airChoose.setName("air");
        airChooseVer.setName("airver");

        group.add(easy);
        group.add(hard);

        shipChoosePanel.setBackground(new Color(244, 244, 244));

        //Check Background 
        URL imagetemp = getClass().getResource("/Images/BattleShipBackground.jpg");

        if (imagetemp == null) {
            throw new BackgroundImageNotFoundException("No background image found.");
        } else {
            ImageIcon backgroundIcon = new ImageIcon(imagetemp);
            background = new JLabel(backgroundIcon);
        }

        //Check Sea Field
        URL seatemp = getClass().getResource("/Images/seafield-expanded2.jpg");

        if (seatemp == null) {
            throw new SeaFieldImageNotFoundException("No seafield image found.");
        } else {
            ImageIcon seaIcon = new ImageIcon(seatemp);
            seafield = new JLabel(seaIcon);
        }

        //Check ship images
        URL patroltemp = getClass().getResource("/Images/patrolboat-pts.png");
        URL patroltempVer = getClass().getResource("/Images/patrolboat-pts-ver.png");
        URL destemp = getClass().getResource("/Images/destroyerboat-pts.png");
        URL destempVer = getClass().getResource("/Images/destroyerboat-pts-ver.png");
        URL subtemp = getClass().getResource("/Images/submarineboat-pts.png");
        URL subtempVer = getClass().getResource("/Images/submarineboat-pts-ver.png");
        URL battleshiptemp = getClass().getResource("/Images/baltteship-pts.png");
        URL battleshiptempVer = getClass().getResource("/Images/baltteship-pts-ver.png");
        URL airtemp = getClass().getResource("/Images/aircraft-pts.png");
        URL airtempVer = getClass().getResource("/Images/aircraft-pts-ver.png");

        if (patroltemp == null || patroltempVer == null || destemp == null || destempVer == null || subtemp == null
                || subtempVer == null || battleshiptemp == null || battleshiptempVer == null || airtemp == null || airtempVer == null) {
            throw new ShipImageNotFoundException("No ship(s) image found.");
        } else {
            ImageIcon patrolIcon = new ImageIcon(patroltemp);
            ImageIcon patrolVerIcon = new ImageIcon(patroltempVer);
            ImageIcon desIcon = new ImageIcon(destemp);
            ImageIcon desVerIcon = new ImageIcon(destempVer);
            ImageIcon subIcon = new ImageIcon(subtemp);
            ImageIcon subVerIcon = new ImageIcon(subtempVer);
            ImageIcon battleIcon = new ImageIcon(battleshiptemp);
            ImageIcon battleVerIcon = new ImageIcon(battleshiptempVer);
            ImageIcon airIcon = new ImageIcon(airtemp);
            ImageIcon airVerIcon = new ImageIcon(airtempVer);

            patrolChoose.add(new JLabel(patrolIcon));
            patrolChooseVer.add(new JLabel(patrolVerIcon));
            destroyerChoose.add(new JLabel(desIcon));
            destroyerChooseVer.add(new JLabel(desVerIcon));
            subChoose.add(new JLabel(subIcon));
            subChooseVer.add(new JLabel(subVerIcon));
            battleshipChoose.add(new JLabel(battleIcon));
            battleshipChooseVer.add(new JLabel(battleVerIcon));
            airChoose.add(new JLabel(airIcon));
            airChooseVer.add(new JLabel(airVerIcon));
        }

        //Set Opaque
        playerField.setOpaque(false);
        easy.setOpaque(false);
        hard.setOpaque(false);

        //Set panel
        playerField.setLayout(new GridLayout(11, 11));

        if (setInitial == 0) {
            setField();

        }

        //Set bounds
        background.setBounds(0, -100, 1280, 1024);
        next.setBounds(700, 650, 150, 50);
        backMain.setBounds(400, 650, 150, 50);
        playerField.setBounds(100, 150, 400, 400);
        chooseLevel.setBounds(530, 500, 200, 100);
        easy.setBounds(470, 550, 200, 100);
        hard.setBounds(670, 550, 200, 100);

        shipChoosePanel.setBounds(600, 90, 580, 400);
        patrolLabel.setBounds(620, 120, 150, 50);
        patrolChoose.setBounds(700, 120, 74, 36);
        patrolChooseVer.setBounds(800, 120, 36, 74);

        destroyerLabel.setBounds(620, 220, 150, 50);
        destroyerChoose.setBounds(700, 220, 111, 36);
        destroyerChooseVer.setBounds(820, 220, 36, 111);

        subLabel.setBounds(620, 340, 150, 50);
        subChoose.setBounds(700, 340, 111, 36);
        subChooseVer.setBounds(820, 340, 36, 111);

        battleshipLabel.setBounds(880, 120, 150, 50);
        battleshipChoose.setBounds(960, 120, 148, 36);
        battleshipChooseVer.setBounds(1120, 120, 36, 148);

        airLabel.setBounds(980, 260, 150, 50);
        airChoose.setBounds(910, 320, 148, 36);
        airChooseVer.setBounds(1100, 320, 36, 148);

        playerField.setBounds(100, 90, 400, 400);
        seafield.setBounds(98, 105, 400, 400);

        //add action
        backMain.addMouseListener(shipController);
        next.addMouseListener(shipController);
        easy.addActionListener(shipController);
        hard.addActionListener(shipController);
        patrolChoose.addMouseListener(shipController);
        patrolChooseVer.addMouseListener(shipController);
        destroyerChoose.addMouseListener(shipController);
        destroyerChooseVer.addMouseListener(shipController);
        subChoose.addMouseListener(shipController);
        subChooseVer.addMouseListener(shipController);
        battleshipChoose.addMouseListener(shipController);
        battleshipChooseVer.addMouseListener(shipController);
        airChoose.addMouseListener(shipController);
        airChooseVer.addMouseListener(shipController);

        setInitial = 1;

        add(chooseLevel);
        add(easy);
        add(hard);
        add(backMain);
        add(next);
        add(playerField);

        add(patrolLabel);
        add(destroyerLabel);
        add(subLabel);
        add(battleshipLabel);
        add(airLabel);

        add(patrolChoose);
        add(patrolChooseVer);

        add(destroyerChoose);
        add(destroyerChooseVer);

        add(subChoose);
        add(subChooseVer);

        add(battleshipChoose);
        add(battleshipChooseVer);

        add(airChoose);
        add(airChooseVer);

        add(shipChoosePanel);

        add(seafield);
        add(background);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            chosenShip = (Integer) arg;
        }

        if (arg instanceof int[]) {
            cor = (int[]) arg;
            placingShip();
            shipPlaceModel.setChosenShip(0);
        }

    }

    public void setField() {
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
                a.addMouseListener(shipController);
            } else if (i % 11 == 0 && i != 0 && i != 11) {
                rowMark++;
            } else if (!(i >= 0 && i <= 11)) {
                rowCount++;
                a.setName(rowMark + "" + rowCount);
                a.setOpaque(false);
                a.addMouseListener(shipController);
            }

            if (i >= 0 && i <= 10 || i % 11 == 0) {
                a.setBackground(new Color(0, 128, 255));
            }

            playerField.add(a);

        }
    }

    public ShipPlaceModel getShipPlaceModel() {
        return shipPlaceModel;
    }

    public ShipPlaceController getShipController() {
        return shipController;
    }

    public void placingShip() {
        if (chosenShip == 1) {
            boolean checkHon = checkHorizontal(2);
            if (checkHon == true) {
                String[] toReturn = returnCorHorizontal(2);
                boolean continued = checkCollision(toReturn);
                if (continued) {
                    patrolChoose.setBounds(cor[0] + 100, cor[1] + 90, 74, 36);
                    shipPlaceModel.getShipmodel().setPlayerShip1(new ArrayList<String>());

                    shipPlaceModel.getShipmodel().getPlayerShip1().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(patrolChoose);

                    patrolChooseVer.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }

        } else if (chosenShip == 2) {
            boolean checkVer = checkVertical(2);
            if (checkVer == true) {
                String[] toReturn = returnCorVertical(2);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    patrolChooseVer.setBounds(cor[0] + 100, cor[1] + 90, 36, 74);
                    shipPlaceModel.getShipmodel().setPlayerShip1(new ArrayList<String>());
                    shipPlaceModel.getShipmodel().getPlayerShip1().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(patrolChooseVer);
                    patrolChoose.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }

        } else if (chosenShip == 3) {
            boolean checkHon = checkHorizontal(3);
            if (checkHon == true) {
                String[] toReturn = returnCorHorizontal(3);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    destroyerChoose.setBounds(cor[0] + 100, cor[1] + 90, 111, 36);
                    shipPlaceModel.getShipmodel().setPlayerShip2(new ArrayList<String>());

                    shipPlaceModel.getShipmodel().getPlayerShip2().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(destroyerChoose);
                    destroyerChooseVer.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (chosenShip == 4) {
            boolean checkVer = checkVertical(3);
            if (checkVer == true) {
                String[] toReturn = returnCorVertical(3);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    destroyerChooseVer.setBounds(cor[0] + 100, cor[1] + 90, 36, 111);
                    shipPlaceModel.getShipmodel().setPlayerShip2(new ArrayList<String>());

                    shipPlaceModel.getShipmodel().getPlayerShip2().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(destroyerChooseVer);
                    destroyerChoose.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (chosenShip == 5) {
            boolean checkHon = checkHorizontal(3);
            if (checkHon == true) {
                String[] toReturn = returnCorHorizontal(3);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    subChoose.setBounds(cor[0] + 100, cor[1] + 90, 111, 36);
                    shipPlaceModel.getShipmodel().setPlayerShip3(new ArrayList<String>());

                    shipPlaceModel.getShipmodel().getPlayerShip3().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(subChoose);
                    subChooseVer.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (chosenShip == 6) {
            boolean checkVer = checkVertical(3);
            if (checkVer == true) {
                String[] toReturn = returnCorVertical(3);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    subChooseVer.setBounds(cor[0] + 100, cor[1] + 90, 36, 111);
                    shipPlaceModel.getShipmodel().setPlayerShip3(new ArrayList<String>());
                    shipPlaceModel.getShipmodel().getPlayerShip3().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(subChooseVer);
                    subChoose.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (chosenShip == 7) {
            boolean checkHon = checkHorizontal(4);
            if (checkHon == true) {
                String[] toReturn = returnCorHorizontal(4);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    battleshipChoose.setBounds(cor[0] + 100, cor[1] + 90, 148, 36);
                    shipPlaceModel.getShipmodel().setPlayerShip4(new ArrayList<String>());
                    shipPlaceModel.getShipmodel().getPlayerShip4().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(battleshipChoose);
                    battleshipChooseVer.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (chosenShip == 8) {
            boolean checkVer = checkVertical(4);
            if (checkVer == true) {
                String[] toReturn = returnCorVertical(4);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    battleshipChooseVer.setBounds(cor[0] + 100, cor[1] + 90, 36, 148);
                    shipPlaceModel.getShipmodel().setPlayerShip4(new ArrayList<String>());
                    shipPlaceModel.getShipmodel().getPlayerShip4().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(battleshipChooseVer);
                    battleshipChoose.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (chosenShip == 9) {
            boolean checkHon = checkHorizontal(5);
            if (checkHon == true) {
                String[] toReturn = returnCorHorizontal(5);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    airChoose.setBounds(cor[0] + 100, cor[1] + 90, 185, 36);
                    shipPlaceModel.getShipmodel().setPlayerShip5(new ArrayList<String>());
                    shipPlaceModel.getShipmodel().getPlayerShip5().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(airChoose);
                    airChooseVer.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (chosenShip == 10) {
            boolean checkVer = checkVertical(5);
            if (checkVer == true) {
                String[] toReturn = returnCorVertical(5);
                boolean continued = checkCollision(toReturn);

                if (continued) {
                    airChooseVer.setBounds(cor[0] + 100, cor[1] + 90, 36, 185);
                    shipPlaceModel.getShipmodel().setPlayerShip5(new ArrayList<String>());
                    shipPlaceModel.getShipmodel().getPlayerShip5().addAll(Arrays.asList(toReturn));
                    shipPlaceModel.setCheckAllShip(shipPlaceModel.getCheckAllShip() + 1);
                    shipPlaceModel.getShipmodel().getPassShip().add(airChooseVer);
                    airChoose.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean checkHorizontal(int size) {
        char temp = 'A';
        int count = 0;
        char rem = 'A';
        int countRem = 0;

        int numtime = 0;
        boolean check = false;

        for (int i = 1; i <= 101; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }
            String save = "" + temp + count;

            if (check == true) {
                numtime++;
            }

            if (save.equals(shipPlaceModel.getClickName())) {
                rem = temp;
                countRem = count;
                numtime++;
                check = true;
            }

            if (numtime == size) {
                if (rem != temp) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean checkVertical(int size) {
        char temp = 'A';
        int count = 0;
        int sizeInner = 0;
        if (size == 2) {
            sizeInner = 101;
        } else if (size == 3) {
            sizeInner = 102;
        } else if (size == 4) {
            sizeInner = 103;
        } else if (size == 5) {
            sizeInner = 104;
        }

        int numtime = 0;
        boolean check = false;

        int currentI = 0;

        for (int i = 1; i <= sizeInner; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }
            String save = "" + temp + count;

            if (check == true) {
                if (((i) - currentI) % 11 == 0) {
                    numtime++;
                }
            }

            if (save.equals(shipPlaceModel.getClickName())) {
                numtime++;
                check = true;
                currentI = i;
            }

        }
        if (numtime < size) {
            return false;
        } else {
            return true;
        }

    }

    public String[] returnCorHorizontal(int size) {
        char temp = 'A';
        int count = 0;

        int numtime = 0;
        boolean check = false;

        String[] toReturn = new String[size];

        for (int i = 1; i <= 101; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }

            String save = "" + temp + count;

            if (check == true) {
                toReturn[numtime] = save;
                numtime++;
            }

            if (save.equals(shipPlaceModel.getClickName())) {
                toReturn[numtime] = save;
                numtime++;
                check = true;

            }

            if (numtime == size) {
                return toReturn;
            }
        }

        return toReturn;
    }

    public String[] returnCorVertical(int size) {
        char temp = 'A';
        int count = 0;

        int numtime = 0;
        boolean check = false;

        String[] toReturn = new String[size];
        int currentI = 0;

        for (int i = 1; i <= 101; i++) {
            if ((i - 1) % 10 == 0 && i != 1) {
                temp++;
                count = 1;
            } else {
                count++;
            }

            String save = "" + temp + count;

            if (check == true) {
                if (((i) - currentI) % 10 == 0) {
                    toReturn[numtime] = save;
                    numtime++;
                }
            }

            if (save.equals(shipPlaceModel.getClickName())) {
                toReturn[numtime] = save;
                numtime++;
                check = true;
                currentI = i;
            }

            if (numtime == size) {
                return toReturn;
            }
        }


        return toReturn;
    }

    public boolean checkCollision(String[] toReturn) {
        shipPlaceModel.getShipmodel().setTotalPlayerShip(new ArrayList<String>());
        shipPlaceModel.getShipmodel().combineShip(shipPlaceModel.getShipmodel().getTotalPlayerShip());

        for (int i = 0; i < toReturn.length; i++) {
            boolean stop = false;
            for (String t : shipPlaceModel.getShipmodel().getTotalPlayerShip()) {
                if (t.equals(toReturn[i])) {
                    stop = true;
                    break;
                }
            }
            if (stop) {
                JOptionPane.showMessageDialog(null, "Cannot place it there", "Game message", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
