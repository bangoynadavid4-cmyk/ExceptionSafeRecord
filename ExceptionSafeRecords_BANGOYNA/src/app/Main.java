package app;

import exception.DuplicateRecordException;
import java.util.Scanner;
import model.ItemRecord;
import service.RecordManager;

/**
 * Week 13 console application: accepts records without terminating
 * when the user enters unsuitable values.
 * @author BANGOYNA
 */
public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final RecordManager MANAGER = new RecordManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = INPUT.nextLine().trim();

            switch (choice) {
                case "1" -> addRecord();
                case "2" -> listRecords();
                case "0" -> {
                    running = false;
                    System.out.println("Application closed.");
                }
                default -> System.out.println("Choose 1, 2, or 0.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nEXCEPTION SAFE RECORD ENTRY - BANGOYNA");
        System.out.println("1 - Add record");
        System.out.println("2 - List records");
        System.out.println("0 - Exit");
        System.out.print("Choice: ");
    }

    private static void addRecord() {
        try {
            System.out.print("Record ID: ");
            String id = INPUT.nextLine();
            System.out.print("Record name: ");
            String name = INPUT.nextLine();
            int quantity = readQuantity();

            MANAGER.add(new ItemRecord(id, name, quantity));
            System.out.println("Record added successfully.");
        } catch (NumberFormatException exception) {
            System.out.println("Unable to add: Quantity must be a whole number.");
        } catch (DuplicateRecordException exception) {
            System.out.println("Unable to add: " + exception.getMessage());
        } catch (IllegalArgumentException exception) {
            System.out.println("Unable to add: " + exception.getMessage());
        }
    }

    private static int readQuantity() {
        System.out.print("Quantity: ");
        return Integer.parseInt(INPUT.nextLine().trim());
    }

    private static void listRecords() {
        if (MANAGER.findAll().isEmpty()) {
            System.out.println("No records are available.");
            return;
        }
        System.out.println("\nID | NAME | QUANTITY");
        MANAGER.findAll().forEach(System.out::println);
    }
}
