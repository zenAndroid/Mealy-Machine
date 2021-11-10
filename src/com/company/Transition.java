package com.company;

public class Transition {
    Character transitionTrigger;
    Character transitionOutput;
    Boolean transitionValid;
    State destinationState;

    public Transition(Character transitionTrigger, Character transitionOutput) {
        commonInit();
        this.transitionTrigger = transitionTrigger;
        this.transitionOutput = transitionOutput;
        destinationState = null;
    }

    public Transition(Character transitionTrigger, Character transitionOutput, State destinationState) {
        commonInit();
        this.transitionTrigger = transitionTrigger;
        this.transitionOutput = transitionOutput;
        this.destinationState = destinationState;
    }

    public void commonInit() {

        /* All transitions start out valid */
        transitionValid = Boolean.valueOf(true);
    }

    public Character getTransitionTrigger() {
        return transitionTrigger;
    }

    public void setTransitionTrigger(Character transitionTrigger) {
        this.transitionTrigger = transitionTrigger;
    }

    public Character getTransitionOutput() {
        return transitionOutput;
    }

    public void setTransitionOutput(Character transitionOutput) {
        this.transitionOutput = transitionOutput;
    }

    public Boolean getTransitionValid() {
        return transitionValid;
    }

    public void setTransitionValid(Boolean transitionValid) {
        this.transitionValid = transitionValid;
    }

    public State getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(State destinationState) {
        this.destinationState = destinationState;
    }

    public Boolean isValid() {
        return transitionValid;
    }

    public Boolean isTriggeredBy(Character trigger) {
        return getTransitionTrigger().equals(trigger);
    }
}
