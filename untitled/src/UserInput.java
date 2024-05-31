import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public int getIntInput(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public long getLongInput(String message) {
        System.out.print(message);
        return scanner.nextLong();
    }

    public String getStringInput(String message) {
        System.out.print(message);
        return scanner.next();
    }
}