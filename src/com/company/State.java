package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class State {
    static int id = 0;
    Machine mach;
    String name;
    ArrayList<Transition> stateTransitions;

    public State(String name) {
        this.name = name;
        id = id++;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Transition> getStateTransitions() {
        return stateTransitions;
    }

    public void setStateTransitions(Transition @NotNull ... argTransitions) {
        stateTransitions = new ArrayList<Transition>();

        for (Transition tr : argTransitions) {
            stateTransitions.add(tr);
        }
    }

    public void setStateTransitions(ArrayList<Transition> argTransitions) {
        stateTransitions = argTransitions;
    }

    public Machine getMach() {
        return mach;
    }

    public void setMach(Machine mach) {
        this.mach = mach;
    }

    /**
     * Method that triggers the state's consumption of the next input token.
     * todo: make sure that this respects representation independence. (stateTransitions or by getter?)
     */
    public void consumeInputToken() {
        Character currTrigger = mach.getNextInputToken();

        for (Transition tr : stateTransitions) {
            if (tr.isValid() && tr.isTriggeredBy(currTrigger)) {
                emitOutput(tr.getTransitionOutput());
                changeMachineState(tr.getDestinationState());
            }
        }
    }

    private void changeMachineState(State destinationState) {
        mach.currentState = destinationState;
    }

    private void emitOutput(Character transitionOutput) {
        mach.processOutput(transitionOutput);
    }

}
