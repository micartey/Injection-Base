package me.clientastisch.injector.injection;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Injection {

    public List<VirtualMachineDescriptor> getVirtualMachines() {
        return new ArrayList<>(VirtualMachine.list());
    }

    @SneakyThrows
    public void inject(VirtualMachineDescriptor descriptor, File agentFile) {
        val vm = VirtualMachine.attach(descriptor);

        System.out.println("[Agent] Loading...");
        val time = System.currentTimeMillis();

        vm.loadAgent(agentFile.getAbsolutePath());

        System.out.println("[Agent] Initialized in " + (System.currentTimeMillis() - time) + "ms");
        vm.detach();
    }
}
