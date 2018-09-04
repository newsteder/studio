package studio.program.element;

import javafx.scene.canvas.GraphicsContext;
import studio.App;

public class Pin extends Element {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String ID = "pin";

    public enum Flow {
        INPUT,
        OUTPUT
    }

    public enum Side {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Block parent = null;

    private Side side;
    private Flow flow;

    private boolean linked = false;

    private double radius = 10;

    /*
     *
     */
    private double attachX = 0;
    private double attachY = 0;

    /*
     *
     */
    private double realX = 0;
    private double realY = 0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Pin(Block parent) {
        super();
        id = ID;
        this.parent = parent;
    }


    @Override
    public void tick(double dt) {

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.translate(x, y);
        gc.fillOval(-radius, -radius, radius * 2, radius * 2);
        gc.strokeOval(-radius, -radius, radius * 2, radius * 2);
        if (hover) {
            gc.setFill(App.COLOR_HOVER_MASK);
            gc.fillOval(-radius, -radius, radius * 2, radius * 2);
        }
        gc.restore();
    }

    @Override
    public boolean containsPoint(double x, double y) {
        double dx = x - this.x;
        double dy = y - this.y;

        if (dx > radius || dy > radius) return false;
        if (dx + dy <= radius) return true;
        if ((dx * dx) + (dy * dy) <= (radius * radius)) return true;

        return false;
    }

    public void setAttachmentPoint(double x, double y) {
        this.attachX = x;
        this.attachY = y;
    }

    public void updatePosition() {
        this.x = parent.getX() + attachX;
        this.y = parent.getY() + attachY;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return side;
    }
}