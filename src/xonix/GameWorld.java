package xonix;
/**
 * Gameworld
 * @author Jeroen
 */
public class GameWorld extends java.util.Observable
{

    static final int SQUARE_LENGTH = 102;
    static final int SQUARE_UNITS = 5;
    static final int GAME_TICK_DELAY = 40;
    static final java.awt.Color NO_COLOR = java.awt.Color.white;
    static final java.awt.Color CAR_COLOR = java.awt.Color.red;
    static final java.awt.Color SQUARE_COLOR = java.awt.Color.black;
    static final java.awt.Color LINE_COLOR = java.awt.Color.red.darker ().darker ();
    static final java.awt.Color PLAYER_COLOR = java.awt.Color.cyan;
    static final java.awt.Color MONSTER_COLOR = java.awt.Color.orange;
    static final java.awt.Color TICKET_COLOR = java.awt.Color.green;
    static private int LEVEL_START = 1;
    static final int CLOCK_START = (6 - LEVEL_START) * 2;
    static final int LIVES_START = 3;
    static final int CSCORE_START = 0;
    static final int RSCORE_START = (40 + LEVEL_START * 10) * 100;
    static final int TTIME_START = 6 - LEVEL_START;

//    public final GameView gv;
    public final FieldSquares fss;
    public java.util.ArrayList<MonsterBall> mbs;
    public java.util.ArrayList<TimeTicket> tts;
    public final Car car;
    private RealState rstate;
    public ProxyState state;
    public final java.util.Random random;
    
    /**
     * initialization
     */
    private static GameWorld gameworld = new GameWorld();
    
    private GameWorld ()
    {
        this.random = new java.util.Random ();
        this.fss = FieldSquares.getInstance();
        createMonsterballs ();
        createTimeTickets ();
        this.car = Car.getInstance();
        this.rstate = new RealState();
        this.state = new ProxyState(rstate);

    }
    public void AddScore(int filledsquares)
    {
        rstate.addcscore(filledsquares);
    }
    
    public static GameWorld getInstance()
    {
        return gameworld;
    }
    /**
     * Creation of new Monsterballs (enemenies)
     */
    public void createMonsterballs ()
    {
        this.mbs = new java.util.ArrayList<> ();
        int number = random.nextInt (10) + 1;
        for (int i = 0; i < number; i ++)
            mbs.add (new MonsterBall (new java.awt.geom.Point2D.Float (random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), MONSTER_COLOR, random.nextInt (360), random.nextFloat () * 100 + 10, 6));
    }
    /**
     * Creation of new TimeTickets (power-up)
     */
    public void createTimeTickets ()
    {
        this.tts = new java.util.ArrayList<> ();
        int number = random.nextInt (SQUARE_UNITS) + 1;
        for (int i = 0; i < number; i ++)
            tts.add (new TimeTicket (new java.awt.geom.Point2D.Float (random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), TICKET_COLOR, TTIME_START, 7, 7));
    }

/**
 * Updates constantly
 * @param delta 
 */
    public void update (float delta)
    {
        /**
         * checks if not game over
         */
        if ( ! state.isGameOver ())
        {
            rstate.addClock ( - delta);
            /**
             * checks if a monsterball is hitting the line
             */
            for (MonsterBall mb : mbs)
                if (mb.changeLocation (fss, rstate, delta))
                {
                    rstate.decLives ();
                    mbs.remove (mb);
                    break;
                }
            car.changeLocation (fss, rstate, delta);
            /**
             * checks if car is on location of a timeticket
             */
            GameController.getInstance().new CollisionTimeticket().actionPerformed(null);
            /**
             * checks if start of new level
             */
            if (LEVEL_START < rstate.getLevel())
            {
                this.LEVEL_START = rstate.getLevel();
                this.fss.reset ();
                createMonsterballs ();
                createTimeTickets ();
                this.car.reset ();
            }
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    public boolean TimeTicketCollision()
    {
        for (TimeTicket tt : tts)
            if (tt.contains (car.getLocation ()))
            {
                rstate.setClock (rstate.getClock () + tt.getSeconds ());
                tts.remove (tt);
                return true;
            }
        return false;
    }
    
    /**
     * reset by gameover
     */
    public void reset ()
    {
        this.fss.reset ();
        createMonsterballs ();
        createTimeTickets ();
        this.car.reset ();
        this.rstate.reset ();
    }
    
    public FieldSquares getFieldSquares ()
    {
        return fss;
    }
}
