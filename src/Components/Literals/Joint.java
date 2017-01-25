package Components.Literals;

import Components.Component;
import Components.Wire;

import java.util.ArrayList;

/**
 * Created by danielkim802 on 1/17/17.
 */
public class Joint extends Component {
    private int outputs;

    public Joint() {
        super(0, 0);
        outputs = 0;
    }

    public void connect(String input, Component other) {
        super.connect(""+outputs, input, other);
        outputs ++;
    }

    public Joint copy() {
        return new Joint();
    }

    public void propagate() {
        if (allInputsAssigned()) {
            for (Wire wire : getOutputs().get("to")) {
                wire.set(getInputs().get("from").value());
            }
        }
    }

    public void setDotPositions() {
        getInputDots().get("from").setXYRelative(-4, 0);
        getOutputDots().get("to").setXYRelative(4, 0);
    }
    public void setIO(int ins, int outs) {
        getInputs().put("from", new Wire());
        getOutputs().put("to", new ArrayList<>());
    }
}