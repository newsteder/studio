package studio.program.element;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import studio.App;
import studio.interaction.shape.Rectangle;
import studio.program.Program;
import studio.program.Var;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * a block is a rectangular element that has pins
 */
public abstract class Block extends Element {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String EID = "block";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     *
     */
    protected ArrayList<Pin> pins = null;

    /*
     *
     */
    protected HashMap<String, Var> vars = null;

    /*
     *
     */
    protected String text = "";

    /*
     * a unique name that identifies what type of block this is
     */
    protected String name = "";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Block() {
        super(EID);
        pins = new ArrayList<>();
        vars = new HashMap<>();
        shape = new Rectangle();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void draw(GraphicsContext gc) {
        shape.fill(gc);
        shape.stroke(gc);

        if (hover) {
            gc.save();
            gc.setFill(App.COLOR_HOVER_MASK);
            shape.fill(gc);
            gc.restore();
        }
    }

    @Override
    public void kill() {
        super.kill();

        // kill all pins attached to this block, we won't be needing them anymore
        for (Pin pin : pins) {
            pin.kill();
        }
    }

    /*
     *
     */
    public abstract void createPins(Program program);

    public void addPin(Pin pin) {
        pins.add(pin);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Pin> getPins() {
        return pins;
    }

    public HashMap<String, Var> getVars() {
        return vars;
    }

    public String getName() {
        return name;
    }
}
