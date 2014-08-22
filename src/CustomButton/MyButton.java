/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomButton;

import Exception.NoButtonImageFoundException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author TanThinh
 */
public class MyButton extends JButton {

    private JLabel over;
    private BufferedImage image;
    private URL url;
    private Color c;

    public MyButton(String text, Color c) {
        super();
        try {
            URL temp = getClass().getResource("/Images/button-size2.png");
            url = checkURL(temp);
            String path = url.toString().substring(5);
            System.out.println(path);
            File f = new File(path);
            image = ImageIO.read(f);
            InitFirst(text, c);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoButtonImageFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.setSize(150, 50);
        return size;
    }

    public URL checkURL(URL u) throws NoButtonImageFoundException {
        URL checkU = u;
        if (checkU == null) {
            throw new NoButtonImageFoundException("No button image found");
        }
        return checkU;
    }

    public void InitFirst(String text, Color c) {
        this.setLayout(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setName(text);

        over = new JLabel(text, JLabel.CENTER);
        this.c = c;
        over.setForeground(this.c);
        over.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        over.setBounds(0, 0, 100, 50);
        over.setSize(150, 50);

        this.add(over, BorderLayout.CENTER);
    }

    @Override
    public void setText(String m) {
        over.setText(m);
    }

    public void setColor(Color c) {
        this.c = c;
        over.setForeground(this.c);
    }

    public void setURLImage(URL url) {
        try {
            URL temp = checkURL(url);
            this.url = temp;
            String path = url.toString().substring(5);
            File f = new File(path);
            image = ImageIO.read(f);
        } catch (NoButtonImageFoundException b) {
            b.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
