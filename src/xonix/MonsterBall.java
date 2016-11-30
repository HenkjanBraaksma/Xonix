package xonix;
/**
 * Monsterball, moving enemy balls
 * @author Jeroen
 */
class MonsterBall extends MovingObject implements Colorable, Moveable
{

    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    private int heading;
    private float speed;
    private float radius;
    private FieldSquare fsprev;
    private FieldSquare fsnext;
    private java.awt.geom.Point2D.Float prev;
    private java.awt.geom.Point2D.Float next;
    /**
     * Constructor of Monsterball
     * @param loc location
     * @param color
     * @param heading direction
     * @param speed
     * @param radius size
     */
    public MonsterBall (final java.awt.geom.Point2D.Float loc, final java.awt.Color color, final int heading, final float speed, final float radius)
    {
        super(loc, color, heading, speed);
        this.setRadius (radius);
    }

    public float getRadius ()
    {
        return radius;
    }

    public void setRadius (float radius)
    {
        this.radius = radius;
    }
    /**
     * Change Location of Monsterball
     * @param fss fieldsquares
     * @param state
     * @param delta
     * @return a boolean
     */
    public boolean changeLocation (FieldSquares fss, RealState state, float delta)
    {
        prev = getLocation ();
        next = nextLocation (delta);
        fsprev = fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5));
        fsnext = fss.elementAt ((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5));

        if (fsprev.getColor () == GameWorld.LINE_COLOR || fsnext.getColor () == GameWorld.LINE_COLOR)
            return true;
        Collision(fss);
        getLocation ().setLocation (nextLocation (delta));
        return false;
    }
    
    public void Collision(FieldSquares fss)
    {   
        if (fsprev.getColor () != fsnext.getColor ())
        {
            if (fsprev.getColor () != fss.elementAt ((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor ())
            //if (fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor () != fss.elementAt ((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor ())
                if (getHeading () < 180)
                    setHeading (180 - getHeading ());
                else
                    setHeading (540 - getHeading ());
            if (fsprev.getColor () != fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5)).getColor ())
            //if (fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor () != fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5)).getColor ())
                setHeading (360 - getHeading ());
        }
    }

    @Override
    public String toString ()
    {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed () + "," + color.getGreen () + "," + color.getBlue () + "]" + " heading=" + heading + " speed=" + speed + " radius=" + radius;
    }
}
