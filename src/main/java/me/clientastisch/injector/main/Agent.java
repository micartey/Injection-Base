package me.clientastisch.injector.main;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void agentmain(String args, Instrumentation instrumentation) {
        System.out.println("Agent injected!");
    }

}
