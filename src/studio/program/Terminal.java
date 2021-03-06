package studio.program;

import javafx.scene.canvas.GraphicsContext;
import studio.ui.View;

public class Terminal {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum Type {
        ANCHOR,
        CORNER,
        JUNCTION
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     *
     */
    public Section parent = null;

    /*
     *
     */
    public Terminal.Type type = Terminal.Type.CORNER;

    /*
     *
     */
    public int x = 0;
    public int y = 0;

    /*
     * if this terminal is a corner type, this variable will be the other terminal this is touching on the next section
     */
    public Terminal next = null;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Terminal(Section parent) {
        this.parent = parent;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC FUNCTIONS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.translate(x - 5, y - 5);
        switch (type) {
            case ANCHOR:
//                gc.setStroke(View.COLOR_DARK);
//                gc.strokeLine(0, 0, 10, 10);
//                gc.strokeLine(10, 0, 0, 10);
//                break;
            case CORNER:
                break;
            case JUNCTION:
                gc.setFill(View.COLOR_DARK);
                gc.fillOval(0, 0, 10, 10);
                break;
        }
        gc.restore();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setPosition(int nx, int ny) {
        x = nx;
        y = ny;
    }
}
