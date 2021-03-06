package com.company;

import java.util.ArrayList;
import java.util.Set;

/**
 * Class represents a Mealy Machine, a kind of finite-state-machine.
 * The model used in this program is such that:
 * A machine is a class that has:
 * <ul>
 *     <li>A set of states (Implemented as an Arraylist)</li>
 *     <li>An initial <code>State</code></li>
 *     <li>A current <code>State</code></li>
 *     <li>An <code>inputSequence</code>, representing the input sequence fed to the machine.</li>
 *     <li>Two non-modifiable sets: (whether these will remain in the implementation as is, remains to be seen)
 *          <ul> <li><code>inputAlphabet</code>, A set of Character representing the input alphabet</li>
 *               <li><code>inputAlphabet</code>, A set of Character representing the output alphabet</li>
 *          </ul>
 *     </li>
 *     <li><code>pendingInput</code>, a boolean flag representing whether there is still input.</li>
 * </ul>
 *
 * @author zenAndroid
 */
public class Machine {
    ArrayList<State> states;
    State initialState;
    State currentState;
    ArrayList<Character> inputSequence;
    Set<Character> inputAlphabet;
    Set<Character> outputAlphabet;
    Boolean pendingInput;

    /**
     * Constructor for an object of the <code>Machine</code> class.
     * <br />
     * Note that this constructor sets the <code>pendingInput</code> flag to true, since the input sequence is passed in as an argument to this constructor.
     *
     * @param states         The states.
     * @param initialState   The initial state of this machine.
     * @param inputSequence  The current sequence of inputs.
     * @param inputAlphabet  The input alphabet.
     * @param outputAlphabet The output alphabet.
     */
    public Machine(ArrayList<State> states, State initialState, ArrayList<Character> inputSequence, Set<Character> inputAlphabet, Set<Character> outputAlphabet) {
        this.states = states;
        this.initialState = initialState;
        this.currentState = initialState;
        this.inputSequence = inputSequence;
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
        this.pendingInput = true;
    }

    /**
     * Constructor for an object of the <code>Machine</code> class, when the input sequence isn't known at creation-time.
     * <p>
     * Note that this constructor sets the <code>pendingInput</code> flag to <code>false</code>.
     *
     * @param states         The states.
     * @param initialState   The initial state of this machine.
     * @param inputAlphabet  The input alphabet.
     * @param outputAlphabet The output alphabet.
     */
    public Machine(ArrayList<State> states, State initialState, Set<Character> inputAlphabet, Set<Character> outputAlphabet) {
        this.states = states;
        this.initialState = initialState;
        this.currentState = initialState;
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
        this.pendingInput = false; /* Set to false because no inputSequence was passed in, so there is no input */
    }

    /**
     * Default constructor, initializes no field except the <code>pendingInput</code> flag to <code>false</code>.
     */
    public Machine() {
        this.pendingInput = false;
    }

    /**
     * Standard getter for the <code>State</code>s of this machine.
     *
     * @return <code>State</code>s of the machine.
     */
    public ArrayList<State> getStates() {
        return states;
    }

    /**
     * Standard setter for the Array of <code>State</code>s.
     * <br />
     * This setter then gives all the passed states the calling instantiated <code>Machine</code> as the 'owner' machine.
     *
     * @param states Array of <code>State</code>s
     */
    public void setStates(ArrayList<State> states) {
        this.states = new ArrayList<>();
        for (State s : states) { // These state do not have a machine as an owner, so we set it for them.
            s.setMach(this);
            this.states.add(s);
        }
        // TODO: Test this functionality!
        //  And I assume people that will come after me will (hopefully) make a proper testing suite.
    }

    /**
     * Setter for the states that utilizes the var-arg capabilities to allow for ease of construction of <code>State</code>s.
     * <br />
     * This setter then gives all the passed states the calling instantiated <code>Machine</code> as the 'owner' machine.
     *
     * @param states Array of <code>State</code>s
     */
    public void setStates(State... states) {
        this.states = new ArrayList<>(); // Not sure this is even needed ... EDIT: It's crucial, let it be

        for (State s : states) {
            s.setMach(this);
            this.states.add(s);
        }
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public ArrayList<Character> getInputSequence() {
        return inputSequence;
    }

    public void setInputSequence(ArrayList<Character> inputSequence) {
        this.inputSequence = inputSequence;
    }

    public Set<Character> getInputAlphabet() {
        return inputAlphabet;
    }

    public void setInputAlphabet(Set<Character> inputAlphabet) {
        this.inputAlphabet = inputAlphabet;
    }

    public Set<Character> getOutputAlphabet() {
        return outputAlphabet;
    }

    public void setOutputAlphabet(Set<Character> outputAlphabet) {
        this.outputAlphabet = outputAlphabet;
    }

    public Boolean getPendingInput() {
        return pendingInput;
    }

    public void setPendingInput(Boolean pendingInput) {
        this.pendingInput = pendingInput;
    }

    /**
     * Returns whether there is still input to be processed by the machine.
     *
     * @return the boolean value that represents the availability of input.
     */
    public boolean isPending() {
        return getPendingInput(); // Auto-boxing helps us out!
    }

    /**
     * Here goes, the starting point of the execution of the Mealy Machine. <br />
     * It is passed an <code>ArrayList</code> of <code>Character</code>s of inputs, so steps are rather straight-forward:
     * 1- Set the machine input sequence to this.
     * 2- Set the machine's <code>pendingInput</code> flag to true, since this is the case (the input was just initialized after all)
     * 3- Notify the initial <code>State</code> so that it starts consuming the next input token.
     * 4- Consumption of the <code>inputSequence</code> continues, until there ie none.
     * In other words, while input exists, consume it.
     *
     * @param input The input fed to the machine.
     * @author zenAndroid
     */
    public void consume(ArrayList<Character> input) {
        setInputSequence(input);
        setPendingInput(true);
        initialState.consumeInputToken();
        while (isPending()) {
            currentState.consumeInputToken();
        }
    }

    /**
     * This method reads the next token and passes it down to its current state. <br />
     * The method removes also returns the removed object, so it can be used for this purpose.
     * todo: What if there IS no input? deal with this case. EDIT: DONE (I think ...)
     *
     * @return Next input token.
     */
    public Character getNextInputToken() {
        // Removes also returns the removed element, so we can use it to achieve two actions;
        // (knowing about the next input token, and removing it as well)
        Character tok = getInputSequence().remove(0);

        if (getInputSequence().isEmpty()) {
            setPendingInput(false); // Set to false given the fact the inputSequence is empty
        }
        return tok;
    }

    /**
     * Methods implementing the business logic that deals with incoming input.
     * Depending on the program/application being developed, the machine's reaction to a transition can change.
     * This method allows the description of such a reaction.
     * For our test case right here, we only want to print it out.
     * Example: Maybe on transition, our machine
     * <ol>
     *     <li>contacts an API and sends it a specific header request</li>
     *     <li>appends an Object to a Collection</li>
     *     <li>prints it out to a specific stream (file/StringBuilder/socket/etc.)</li>
     *     <li>or some other operation ...</li>
     * </ol>
     *
     * @param sourceTransition The output of the machine, given its global state.
     */
    public void processOutput(Character sourceTransition) {
        System.out.println(sourceTransition);
    }

    /**
     * @return A <a href="http://www.research.att.com/sw/tools/graphviz/" target="_top">Graphviz Dot</a>
     * representation of this automaton.
     */
    public String toDot() {
        StringBuilder b = new StringBuilder("digraph Automaton {\n");
        b.append("    node [shape=circle];\n");
        b.append("    node [shape=point] INIT;\n");
        b.append("    rankdir = LR;\n");
        b.append("    INIT -> " + getInitialState().getName() + ";\n");
        ArrayList<State> machineStates = getStates();
        for (State s : machineStates){
            var stateTranses = s.getStateTransitions();
            for (Transition t : stateTranses) {
                b.append("    " + s.getName() + " -> " + t.getDestinationState().getName() + " ");
                b.append("[label=\"" + t.getTransitionTrigger() + "/" + t.getTransitionOutput() + "\"];\n");
            }
        }
        return b.append("}\n").toString();
    }
}