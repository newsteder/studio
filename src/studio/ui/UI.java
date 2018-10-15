package studio.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import studio.program.Program;

public final class UI {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     *
     */
    public Program program = null;

    /*
     *
     */
    public Canvas canvas = null;

    /*
     *
     */
    public View view = null;

    /*
     *
     */
    public InteractionManager interactionManager = null;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public UI(Program program, Canvas canvas) {
        this.program = program;
        this.canvas = canvas;

        view = new View(this);
        interactionManager = new InteractionManager(this);

        view.interactionManager = interactionManager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC FUNCTIONS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void tick(double dt) {
        interactionManager.tick(dt);
    }

    public void draw(GraphicsContext gc) {
        view.draw(gc);
    }
}
