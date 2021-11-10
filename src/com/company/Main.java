package com.company;


import java.util.ArrayList;
import java.util.Set;

/**
 * @author zenAndroid
 */
public class Main {

    public static void main(String[] args) {
        // write your code here

        // Creating the states;

        Machine M = new Machine();

        State s0 = new State("q0");
        State s1 = new State("q1");
        State s2 = new State("q2");

        // Creating the transitions

        Transition t1 = new Transition('a', '0');
        Transition t2 = new Transition('b', '1');
        Transition t3 = new Transition('a', '0');
        Transition t4 = new Transition('b', '1');
        Transition t5 = new Transition('a', '0');
        Transition t6 = new Transition('b', '1');

        // Setting the destinations states

        t1.setDestinationState(s1);
        t2.setDestinationState(s2);
        t3.setDestinationState(s2);
        t4.setDestinationState(s0);
        t5.setDestinationState(s0);
        t6.setDestinationState(s0);

        // Giving the States their transitions

        s0.setStateTransitions(t1, t2);
        s1.setStateTransitions(t3, t4);
        s2.setStateTransitions(t5, t6);

        M.setInitialState(s0);
        M.setInputAlphabet(Set.of('a', 'b'));
        M.setOutputAlphabet(Set.of('0', '1'));
        M.setStates(s0, s1, s2);

        var inputSeq = new ArrayList<Character>();
        inputSeq.add('a');
        inputSeq.add('a');
        inputSeq.add('a');
        inputSeq.add('b');
        inputSeq.add('a');

        M.consume(inputSeq);

        System.out.println("Actually works, i cant believe it");

    }
}
