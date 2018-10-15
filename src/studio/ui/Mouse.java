package studio.ui;

public final class Mouse {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     *
     */
    public int realX = 0;
    public int realY = 0;

    /*
     *
     */
    public int viewX = 0;
    public int viewY = 0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Mouse() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setRealPosition(int realX, int realY) {
        this.realX = realX;
        this.realY = realY;
    }

    public void setViewPosition(int viewX, int viewY) {
        this.viewX = viewX;
        this.viewY = viewY;
    }
}
