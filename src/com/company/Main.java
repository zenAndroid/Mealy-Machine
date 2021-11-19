package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zenAndroid
 */
public class Main {

    public static void main(String[] args) {
        // write your code here

        // Hardcoded machine (they are only for TEMPORARY testing purposes, i swear ...)

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

        System.out.println("Second consumption");

        Machine MM = new Machine(); // Think i'm gonna model the machine in the article.

        // 1- States.
        // 2- Transitions.
        // 3- Giving states their transitions.
        // 4- Set the initial state.
        // 5- Set the input alphabet (Still sus)
        // 6- Set the output alpahbet (still sus)
        // 7- Set the machine states
        // 8- Set the input sequence.
        // 9- CONSUME !!!

        // 1- States.
        State q1 = new State("1"), q2 = new State("2"), q3 = new State("3"), q4 = new State("4");
        // 2- Transitions
        Transition tr1 = new Transition('b', '0', q1); // q1
        Transition tr2 = new Transition('a', '0', q2); // q1
        Transition tr3 = new Transition('a', '1', q2); // q2
        Transition tr4 = new Transition('b', '0', q3); // q2
        Transition tr5 = new Transition('a', '1', q4); // q3
        Transition tr6 = new Transition('b', '0', q3); // q3
        Transition tr7 = new Transition('a', '1', q4); // q4
        Transition tr8 = new Transition('b', '1', q4); // q4
        // 3- Giving states their transitions.
        q1.setStateTransitions(tr1, tr2);
        q2.setStateTransitions(tr3, tr4);
        q3.setStateTransitions(tr5, tr6);
        q4.setStateTransitions(tr7, tr8);
        // 4- Set the initial State
        MM.setInitialState(q1);
        // 5- Set the input alphabet (Still sus)
        MM.setInputAlphabet(Set.of('a','b'));
        // 6- Set the output alphabet (still sus)
        M.setOutputAlphabet(Set.of('0', '1'));
        // 7- Set the machine states
        MM.setStates(q1,q2,q3,q4);
        // 8- Set the input sequence. and consume
        MM.consume(new ArrayList<>(List.of('a','b','b','a','b','b','b','a')));

        // This should give
        // 0 0 0 1 1 1 1 1

        System.out.println("Actually works, i cant believe it");

        System.out.println(MM.toDot());

        /*
         * Prints:
         * 0
         * 0
         * 0
         * 1
         * 0
         * Actually works, i cant believe it
         */

    }
}
