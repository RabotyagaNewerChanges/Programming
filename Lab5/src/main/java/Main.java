
import control_unit.Controller;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new TreeSet<>());
        Scanner terminalScanner = new Scanner(System.in);

        while(controller.getProgramState()) {
            controller.parseCommand(terminalScanner);
        }

        terminalScanner.close();

    }
}
