package xonix;
/**
 * Car class
 * @author Jeroen
 */

class Car extends MovingObject implements Colorable, Moveable, Steerable
{
    static private java.awt.geom.Point2D.Float loc = new java.awt.geom.Point2D.Float (GameWorld.SQUARE_LENGTH / 2 * GameWorld.SQUARE_UNITS, (GameWorld.SQUARE_LENGTH - 1) * GameWorld.SQUARE_UNITS);
    static private java.awt.Color color =  GameWorld.CAR_COLOR;
    static private int heading = 270;
    static private float speed = 50;
    static private int width = GameWorld.SQUARE_UNITS;
    static private int height = GameWorld.SQUARE_UNITS;
    
    static private FieldSquare fsprev;
    static private FieldSquare fsnext;
    
    /**
     * Car constructor
     * @param loc
     * @param color
     * @param heading
     * @param speed
     * @param width
     * @param height 
     */
    private static Car car = new Car(loc,color,heading,speed,width,height);
    
    private Car (final java.awt.geom.Point2D.Float loc, final java.awt.Color color, final int heading, final float speed, final int width, final int height)
    {
        super(loc, color, heading, speed);
        setWidth (width);
        setHeight (height);
    }
    
    public static Car getInstance()
    {
        return car;
    }
/**
 * Reset car position, speed etc.
 */
    public void reset ()
    {
        setLocation (new java.awt.geom.Point2D.Float (GameWorld.SQUARE_LENGTH / 2 * GameWorld.SQUARE_UNITS, (GameWorld.SQUARE_LENGTH - 1) * GameWorld.SQUARE_UNITS));
        setColor (GameWorld.CAR_COLOR);
        setHeading (270);
        setSpeed (50);
        setWidth (GameWorld.SQUARE_UNITS);
        setHeight (GameWorld.SQUARE_UNITS);
    }
    
    /**
     * Get Width
     * @return width
     */
    public int getWidth ()
    {
        return width;
    }

    public final void setWidth (final int width)
    {
        this.width = width;
    }

    public int getHeight ()
    {
        return height;
    }

    public final void setHeight (final int height)
    {
        this.height = height;
    }
    /**
     * changes car to new car position
     * @param fss fieldsquares
     * @param state
     * @param delta 
     */
    public void changeLocation (FieldSquares fss, RealState state, float delta)
    {
        java.awt.geom.Point2D.Float prev = getLocation ();
        java.awt.geom.Point2D.Float next = nextLocation (delta);
        fsprev = fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5));
        fsnext = fss.elementAt ((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5));
        if (fsnext.getColor () == GameWorld.SQUARE_COLOR)
            fsnext.setColor (GameWorld.LINE_COLOR);
        else if (fsnext.getColor () == GameWorld.PLAYER_COLOR && fsprev.getColor () == GameWorld.LINE_COLOR)
            state.addcscore (fss.fillSquares ());
        getLocation ().setLocation (next);
    }
    
    public void redsquare()
    {
        if (fsnext.getColor () == GameWorld.SQUARE_COLOR)
            fsnext.setColor (GameWorld.LINE_COLOR);
    }
    
    public void fillAllSquares(FieldSquares fss)
    {
        if (fsnext.getColor () == GameWorld.PLAYER_COLOR && fsprev.getColor () == GameWorld.LINE_COLOR)
            GameWorld.getInstance().AddScore(fss.fillSquares ());
    }

    @Override
    public String toString ()
    {
        return "loc=" + getLocation ().x + "," + getLocation ().y + " color=[" + color.getRed () + "," + color.getGreen () + "," + color.getBlue () + "]" + " heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height;
    }
}
