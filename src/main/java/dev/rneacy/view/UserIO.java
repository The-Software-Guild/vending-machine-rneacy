package dev.rneacy.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserIO implements IUserIO {

    public void print(String message) {
        System.out.println(message);
    }

    public String readString(String prompt) {
        System.out.printf("%s ", prompt);
        return new Scanner(System.in).nextLine();
    }

    public int readInt(String prompt) throws InputMismatchException {
        System.out.printf("%s ", prompt);
        return new Scanner(System.in).nextInt();
    }

    public int readInt(String prompt, int min, int max) throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        int response;

        do{
            System.out.printf("%s ", prompt);
            response = scan.nextInt();
        }
        while(!(response >= min && response <= max));

        return response;
    }

    public double readDouble(String prompt) throws InputMismatchException {
        System.out.printf("%s ", prompt);
        return new Scanner(System.in).nextDouble();
    }

    public double readDouble(String prompt, double min, double max) throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        double response;

        do{
            System.out.printf("%s ", prompt);
            response = scan.nextDouble();
        }
        while(!(response >= min && response <= max));

        return response;
    }

    public float readFloat(String prompt) throws InputMismatchException {
        System.out.printf("%s ", prompt);
        return new Scanner(System.in).nextFloat();
    }

    public float readFloat(String prompt, float min, float max) throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        float response;

        do{
            System.out.printf("%s ", prompt);
            response = scan.nextFloat();
        }
        while(!(response >= min && response <= max));

        return response;
    }

    public long readLong(String prompt) throws InputMismatchException {
        System.out.printf("%s ", prompt);
        return new Scanner(System.in).nextLong();
    }

    public long readLong(String prompt, long min, long max) throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        long response;

        do{
            System.out.printf("%s ", prompt);
            response = scan.nextLong();
        }
        while(!(response >= min && response <= max));

        return response;
    }

}
