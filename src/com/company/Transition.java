package com.company;

/**
 * Represents a transition between two states.
 * This differs significantly from the mathematical model, which can ba said of the entire model to be honest.
 * But the way it differs is that the mathematical model assumes that a singular transition holds both the source <code>State</code>
 * and the destination <code>State</code>, whereas in this model the transition BELONGS to a <i>specific</i> <code>State</code>.
 *
 * Other than that, each <code>Transition</code> has:
 * <ul>
 *     <li>A transitionTrigger: The input that launches/triggers this transition.</li>
 *     <li>A transitionOutput: The output that results for this transition being taken.</li>
 *     <li>A transitionValid: The boolean flag that determines if this transition is valid and applicable.</li>
 *     <li>A destinationState: The <code>State</code> taken after this transition takes place.</li>
 * </ul>
 * @author zenAndroid
 */
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
