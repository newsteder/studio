package studio.ui.command;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studio.program.*;
import studio.ui.InteractionManager;
import studio.ui.View;

import java.util.ArrayList;

public final class CLink extends Command {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final static Logger LOGGER = LoggerFactory.getLogger(Link.class);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     *
     */
    private Pin start = null;

    /*
     *
     */
    private Element end = null;

    /*
     *
     */
    private Section csec = null;

    /*
     *
     */
    private ArrayList<Section> sections;

    /*
     *
     */
    private int startX = 0;
    private int startY = 0;

    /*
     *
     */
    private int endX = 0;
    private int endY = 0;

    /*
     *
     */
    private Link link = null;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public CLink(InteractionManager manager) {
        super(manager);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC FUNCTIONS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onActivate() {
        start = (Pin)manager.hover;
        start.linked = true;

        sections = new ArrayList<>();

        csec = new Section();
        csec.a.setPosition(
                start.x,
                start.y
        );
        csec.b.setPosition(
                start.x,
                start.y
        );
        csec.a.type = Terminal.Type.ANCHOR;
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (Section ls : sections) {
            ls.stroke(gc);
        }
        csec.stroke(gc);
    }

    @Override
    public void onMouseMoved(MouseEvent event) {
        updateCurrentSection();
        updateEndElement();
    }

    @Override
    public void onMouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            attemptNextSection();
        }
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            if (start != null) start.linked = false;
            abort();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE FUNCTIONS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void updateCurrentSection() {
        int dx = Math.abs(mouse.gridX - csec.a.x);
        int dy = Math.abs(mouse.gridY - csec.a.y);

        // more vertical than horizontal
        if (dy > dx) {
            csec.b.setPosition(csec.a.x, mouse.gridY);
            csec.orientation = Section.Orientation.VERTICAL;
        // more horizontal than vertical
        } else {
            csec.b.setPosition(mouse.gridX, csec.a.y);
            csec.orientation = Section.Orientation.HORIZONTAL;
        }
    }

    private void updateEndElement() {
        Element nh = manager.collider.check(
                csec.b.x,
                csec.b.y
        );
        Element oh = end;

        //
        if (nh == oh) return;

        if (oh != null) {
            if (oh.eid == Pin.EID) {
                ((Pin)oh).linked = false;
            }

            csec.b.type = Terminal.Type.CORNER;
            oh.onExit();
        }

        if (nh == null) {
            end = nh;
            return;
        }

        if (nh.eid == Pin.EID) {
            if (((Pin)nh).linked) {
                return;
            }

            ((Pin)nh).linked = true;
            csec.b.type = Terminal.Type.ANCHOR;
            end = nh;
            return;
        }

        if (nh.eid == Link.EID) {
            csec.b.type = Terminal.Type.JUNCTION;
            end = nh;
            return;
        }

        end = null;
        return;
    }

    private void attemptNextSection() {
        sections.add(csec);

        if (end != null) {
            attemptLink();
            return;
        }

        // new section
        Section nsec = new Section();

        nsec.a.setPosition(
                csec.b.x,
                csec.b.y
        );

        nsec.b.setPosition(
                csec.b.x,
                csec.b.y
        );

        nsec.a.type = Terminal.Type.CORNER;

        nsec.a.next = csec.b;
        csec.b.next = nsec.a;

        csec = nsec;
    }

    private void attemptLink() {
        if (end.eid == Pin.EID) {
            linkToPin();
        }

        if (end.eid == Link.EID) {
            linkToLink();
        }
    }

    private void linkToPin() {
        Pin sp = start;
        Pin ep = (Pin)end;

        link = new Link();

        if (sp.var.type != ep.var.type) {
            LOGGER.warn("INCOMPATIBLE VARIABLE TYPES BETWEEN PINS");
            return;
        }

        if (sp.flow == ep.flow) {
            LOGGER.warn("INCOMPATIBLE FLOW BETWEEN PINS");
            return;
        }

        boolean valid = true;

        valid &= link.addPin(sp);
        valid &= link.addPin(ep);

        if (valid) {
            link.sections = sections;
            manager.program.addElement(link);
            sp.link(link);
            ep.link(link);
            finish();
        } else {
            abort();
            LOGGER.error("fix me please");
        }
    }

    private void linkToLink() {
        Link l = (Link)end;

        if (l.addPin(start)) {
            start.link(l);

            for (Section s : sections) {
                l.addSection(s);
            }

            // add csec.b terminal as a junction of the section we are linking to
            l.hitSection.junctions.add(csec.b);

            finish();
        } else {
            LOGGER.error("FIX ME");
        }
    }
}
