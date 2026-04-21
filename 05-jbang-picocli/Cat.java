///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.7.7
//JAVA 25+

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "cat", mixinStandardHelpOptions = true, version = "cat 0.1", description = "cat made with jbang")
class Cat implements Callable<Integer> {

    @Parameters(index = "0", description = "The File to print")
    private String path;

    @Option(names = { "-n", "--numbers" }, description="display line numbers")
    private boolean numbers;

    public static void main(String... args) {
        int exitCode = new CommandLine(new Cat()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        List<String> lines = Files.readAllLines(Path.of(path));
        for (int i = 0; i < lines.size(); i++) {
            if(numbers) {
                IO.print((i + 1) + "  ");
            }
            IO.println(lines.get(i));
            
        }
        return 0;
    }
}
