# Injection-Base

### Disclaimer

This project is a template to inject into another VM

```text
usage: java [-vmOptions] -jar <Jar>
    --file <arg>   File to attach
    --list         List of all VMs
    --search       Name/ID of the VirtualMachine to attach
```

### How to use

Edit the `Agent.java` file in package `me.clientastisch.injector.main`.

```java
public class Agent {

    public static void agentmain(String args, Instrumentation instrumentation) {
        System.out.println("Agent injected!");
    }

}
```

Write your code in `agentmain`, but **DO NOT** rename the method.
