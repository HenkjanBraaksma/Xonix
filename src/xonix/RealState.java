package xonix;
/**
 * Gives the state of lvl,clock,lives,currentscore,requiredscore, time and game-over
 * @author Jeroen
 */
class RealState implements State
{
    private int level;
    private float clock;
    private int lives;
    private int cscore;
    private int rscore;
    private int tTime;
    private boolean gameOver;
    
    public RealState ()
    {
        this.reset ();
    }

    public void reset ()
    {
        setLevel (1);
    }

    public int getLevel ()
    {
        return level;
    }
/**
 * sets new level
 * @param level 
 */
    public void setLevel (int level)
    {
        this.level = level;
        this.clock = (40 - level) * 2;
        this.lives = 3;
        this.cscore = 0;
        this.rscore = (40 + level * 10) * 100;
        this.tTime = level - 1;
        this.gameOver = false;
    }

    public float getClock ()
    {
        return clock;
    }
/**
 * sets new clock and checks if time is 0
 * @param clock 
 */
    public void setClock (float clock)
    {
        this.clock = clock;
        if ((int)clock == 0)
        {
            decLives ();
            this.clock = (40 - level) * 2;
        }
    }

    public void addClock (float clock)
    {
        setClock (this.clock + clock);
    }

    public int getLives ()
    {
        return lives;
    }

    public void setLives (int lives)
    {
        this.lives = lives;
    }
    /**
     * Decreases lives when time is zero
     */
    public void decLives ()
    {
        setLives (getLives () - 1);
        if (getLives () == 0)
            gameOver = true;
    }

    public int getcscore ()
    {
        return cscore;
    }
    /**
     * sets new score and checks if cscore is bigger then required score
     * @param cscore 
     */
    public void setcscore (int cscore)
    {
        this.cscore = cscore;
        if (cscore > rscore){
            setLevel (level + 1);
        }
    }

    public void addcscore (int cscore)
    {
        setcscore (this.cscore + cscore);
    }

    public int getrscore ()
    {
        return rscore;
    }

    public void setrscore (int rscore)
    {
        this.rscore = rscore;
    }

    public boolean isGameOver ()
    {
        return gameOver;
    }

    public void setGameOver (boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    @Override
    public String toString ()
    {
        return "Current level=" + this.getLevel ()
                + " Remaining lives=" + this.getLives ()
                + " Remaining time=" + this.getClock ()
                + " Current score=" + this.getcscore ()
                + " Required score=" + this.getrscore ();
    }
}
