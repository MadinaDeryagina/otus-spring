package otus.deryagina.spring.question.iostreams;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOStreamsProviderImpl implements IOStreamsProvider {

    private InputStream inputStream;
    private PrintStream printStream;
    private Scanner scanner;
    public IOStreamsProviderImpl() {
        this.inputStream = System.in;
        this.printStream = System.out;
        scanner = new Scanner(inputStream);
    }

    public void printInfo(String info) {
        printStream.println(info);
    }

    public String readNexLineData() {
        return scanner.nextLine();
    }
}
