package Printers;

import java.util.*;

public class PrinterControllerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Printer> printers = new ArrayList<>();
        int currentPrinter = 1;

        String symbols;
        do {
            System.out.println("Enter string #" + currentPrinter + ": ");
            symbols = scanner.nextLine();
            System.out.println("Enter timeout for printer " + currentPrinter + "(seconds): ");
            int interval = Integer.parseInt(scanner.nextLine());

            printers.add(new Printer(symbols, interval));

            System.out.println("Enter 's' for start printing, other - enter new printer");
            symbols = scanner.nextLine();
            currentPrinter++;
        } while (!symbols.toLowerCase().equals("s"));

        for (Printer printer : printers) {
            printer.start();
        }

        System.out.println("Enter 'q' for quit, <number> - change current symbol in Printer #<number>");

        Printer printer;
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.toLowerCase().equals("q"))
                break;
            int number = Integer.parseInt(line);
            printer = printers.get(number - 1);
            printer.interrupt();
        }
        scanner.close();
        System.out.println("FINISH");
    }
}