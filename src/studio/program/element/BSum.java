package studio.program.element;

import studio.program.Program;
import studio.program.Signal;

public class BSum extends Block {
    public BSum() {
        super();
        text = "SUM";
        name = "sum";
    }

    @Override
    public void tick(double dt) {

    }

    @Override
    public void createPins(Program program) {
        Pin in1 = new Pin(this, Signal.Type.NUMBER, Pin.Flow.INPUT);
        in1.setSide(Pin.Side.LEFT);
        in1.setAttachmentPoint(-width / 2, -20);
        in1.setIndex(0);

        Pin in2 = new Pin(this, Signal.Type.NUMBER, Pin.Flow.INPUT);
        in2.setSide(Pin.Side.LEFT);
        in2.setAttachmentPoint(-width / 2, 20);
        in2.setIndex(1);

        Pin out1 = new Pin(this, Signal.Type.NUMBER, Pin.Flow.OUTPUT);
        out1.setSide(Pin.Side.RIGHT);
        out1.setAttachmentPoint(width / 2, 0);
        out1.setIndex(2);

        program.addElement(in1);
        program.addElement(in2);
        program.addElement(out1);

        addPin(in1);
        addPin(in2);
        addPin(out1);
    }
}