package me.clientastisch.injector.main;

import lombok.val;
import me.clientastisch.injector.injection.Injection;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) throws Throwable {

        val search = OptionBuilder.withLongOpt("search").hasArg().withDescription("Name/ID of the VirtualMachine to attach").create();
        val file = OptionBuilder.withLongOpt("file").hasArg().withDescription("File to attach").create();
        val list = OptionBuilder.withLongOpt("list").hasArg(false).withDescription("List of all VMs").create();

        val options = new Options();
        options.addOption(search);
        options.addOption(file);
        options.addOption(list);

        CommandLineParser parser = new PosixParser();
        val formatter = new HelpFormatter();

        try {
            val line = parser.parse(options, args, true);
            val injection = new Injection();

            if(line.hasOption("list")) {
                injection.getVirtualMachines().forEach(vm -> {
                    System.out.println(vm.id() + " " + vm.displayName().split(" ")[0]);
                });

                return;
            }

            if(!line.hasOption("search") || !line.hasOption("file")) {
                formatter.printHelp("java [-vmOptions] -jar <Jar>", options);
                return;
            }

            val optional = injection.getVirtualMachines().stream().filter(desc -> desc.displayName().split(" ")[0].equals(line.getOptionValue("search")) || desc.id().equals(line.getOptionValue("search"))).findFirst();

            injection.inject(optional.orElseThrow((Supplier<Throwable>) () -> {
                throw new IllegalArgumentException("Machine not found.");
            }), new File(line.getOptionValue("file")));

        } catch (ParseException e) {
            formatter.printHelp("java [-vmOptions] -jar <Jar>", options);
            System.exit(-1);
        }
    }
}
