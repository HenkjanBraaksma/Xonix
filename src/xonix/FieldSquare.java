package xonix;
/**
 * A square in the gamefield
 * @author Jeroen
 */
class FieldSquare extends BaseObject implements Colorable
{

    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    private float size;
    /**
     * constructor of Fieldsquare
     * @param loc location
     * @param color
     * @param size 
     */
    public FieldSquare (final java.awt.geom.Point2D.Float loc, final java.awt.Color color, float size)
    {
        super(loc, color);
        setSize (size);
    }

    public float getSize ()
    {
        return size;
    }

    public void setSize (float size)
    {
        this.size = size;
    }

    @Override
    public String toString ()
    {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed () + "," + color.getGreen () + "," + color.getBlue () + "]" + " size=" + size;
    }
}
