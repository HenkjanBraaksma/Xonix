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
    private int homing = 0;
    Context context = new Context(new OperationNormal());
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
    
    public int getHoming ()
    {
        return homing;
    }

    public void setHoming (int homing)
    {
        this.homing = homing;
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
   @Override public java.awt.geom.Point2D.Float nextLocation (float delta)
    {
        double radians;
        switch (homing)
        {
            case (2):
                context = new Context(new OperationFollow());
                break;
            case (1):
                context = new Context(new OperationOrbit());
                break;
            case (3):
                context = new Context(new OperationScared());
                break;
            case(0):
            default:
                context = new Context(new OperationNormal());
                break;
        }
//        if (homing == 2)
//             context = new Context(new OperationFollow());
//        else if (homing == 1)
//             context = new Context(new OperationOrbit());
//        else if (homing == 3)
//             context = new Context(new OperationScared());
//        else
//             context = new Context(new OperationNormal());
        radians = context.executeStrategy(getLocation().x, getLocation().y, GameWorld.getInstance().car.getLocation().x, GameWorld.getInstance().car.getLocation().y, getHeading ());
        float newx = getLocation ().x + delta * getSpeed () * (float) Math.cos (radians);
        if (newx < 0)
            newx = 0;
        else if (newx > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newx = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        float newy = getLocation ().y - delta * getSpeed () * (float) Math.sin (radians);
        if (newy < 0)
            newy = 0;
        else if (newy > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newy = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        return new java.awt.geom.Point2D.Float (newx, newy);
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
