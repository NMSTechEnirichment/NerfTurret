package com.nerf.turret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Turret.
 *
 */
public class Turret
{

    /** String ot identify turrets in a restlet context map. */
    public static final String IDENTIFIER = "Turret";

    /** The pan servo for moving left and right. */
    private final Servo panServo;

    /** The tilt Servo for moving up and down. */
    private final Servo tiltServo;

    /**
     * Constructor.
     *
     * @param panServo The pan servo.
     * @param tiltServo The tilt servo.
     */
    private Turret(Servo panServo, Servo tiltServo)
    {
        this.panServo = panServo;
        this.tiltServo = tiltServo;
    }

    /**
     * Initialize the Turret.
     *
     */
    public void init()
    {
        this.panServo.init();
        this.tiltServo.init();
    }

    /**
     * Move the turret up.
     *
     */
    public void moveUp()
    {
        // TODO Just too a guess at direction, need to figure out how the Servos actually move and how far.
        tiltServo.move(tiltServo.getPosition() + 5);
    }

    /**
     * Move the turret down.
     *
     */
    public void moveDown()
    {
        tiltServo.move(tiltServo.getPosition() - 5);
    }

    /**
     * Move the turret right.
     *
     */
    public void moveRight()
    {
        panServo.move(panServo.getPosition() + 5);
    }

    /**
     * Move the turret left.
     *
     */
    public void moveLeft()
    {
        panServo.move(panServo.getPosition() - 5);
    }

    public void setPosition(Position position)
    {
        panServo.setPosition(position.pan);
        tiltServo.setPosition(position.tilt);
    }

    public Position getPosition()
    {
        return new Position(panServo.getPosition(), tiltServo.getPosition());
    }

    /**
     * Create a new Turret.
     *
     * @return A new Turret.
     */
    public static Turret create()
    {
        Servo panServo = new Servo("Pan",15, 0);
        Servo tiltServo = new Servo("Tilt",16, 0);

        return new Turret(panServo, tiltServo);
    }

    public static void main(String[] args) throws IOException
    {
        // Create the turret.
        Turret turret = Turret.create();
        // Initialize it
        turret.init();

        // Read the keyboard for movement.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while(!"q".equals(input))
        {
            input = br.readLine();

            if("w".equals(input)) {
                System.out.println("Moving Up.");
                turret.moveUp();
            }
            else if("s".equals(input)) {
                System.out.println("Moving Down.");
                turret.moveDown();
            }
            else if("a".equals(input)) {
                System.out.println("Moving Left.");
                turret.moveLeft();
            }
            else if("d".equals(input)) {
                System.out.println("Moving Right.");
                turret.moveRight();
            }
        }

        System.out.println("Quitting.");

    }

    public static class Position
    {
        public int pan;

        public int tilt;

        public Position(int pan, int tilt)
        {
            this.pan = pan;
            this.tilt = tilt;
        }
    }

}
