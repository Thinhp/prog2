/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sound;

import Exception.NoSoundPlayException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author TanThinh
 */
public class MySound {

    private URL sound;
    private static MySound uniqueSound;

    public static MySound getInstance() {
        if (uniqueSound == null) {
            uniqueSound = new MySound();
        }
        return uniqueSound;
    }

    private URL checkURL(URL url) throws NoSoundPlayException {
        if (url == null) {
            throw new NoSoundPlayException("No sound audio found.");
        }
        return url;
    }

    public void playHitFire() {
        try {
            URL soundcheck = getClass().getResource("/Sound/hitfire-sound2.wav");
            sound = checkURL(soundcheck);
            String s = sound.toString();
            String soundhitLocation = s.substring(5);
            InputStream in = new FileInputStream(soundhitLocation);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (NoSoundPlayException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playMissFire() {
        try {
            URL soundcheck = getClass().getResource("/Sound/hitmiss-sound.wav");
            sound = checkURL(soundcheck);
            String s = sound.toString();
            String soundhitLocation = s.substring(5);
            InputStream in = new FileInputStream(soundhitLocation);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (NoSoundPlayException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playButtonClick() {
        try {
            URL soundcheck = getClass().getResource("/Sound/button-click.wav");
            sound = checkURL(soundcheck);
            String s = sound.toString();
            String soundhitLocation = s.substring(5);
            InputStream in = new FileInputStream(soundhitLocation);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (NoSoundPlayException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playHoverButtonClick() {
        try {
            URL soundcheck = getClass().getResource("/Sound/button-hover.wav");
            sound = checkURL(soundcheck);
            String s = sound.toString();
            String soundhitLocation = s.substring(5);
            InputStream in = new FileInputStream(soundhitLocation);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (NoSoundPlayException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playPlacingShip() {
        try {
            URL soundcheck = getClass().getResource("/Sound/placingship.wav");
            sound = checkURL(soundcheck);
            String s = sound.toString();
            String soundhitLocation = s.substring(5);
            InputStream in = new FileInputStream(soundhitLocation);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (NoSoundPlayException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playDifficultClick() {
        try {
            URL soundcheck = getClass().getResource("/Sound/difficult-click.wav");
            sound = checkURL(soundcheck);
            String s = sound.toString();
            String soundhitLocation = s.substring(5);
            InputStream in = new FileInputStream(soundhitLocation);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (NoSoundPlayException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
