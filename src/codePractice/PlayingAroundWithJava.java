package codePractice;

import java.util.Scanner;

public class PlayingAroundWithJava {
    public static void main(String args[]){
        System.out.println("Hello, World!");    // Prints Hello World
        System.out.println("Hello, World!".length());   // Prints the length of "Hello, World!"

        // Working with variables
        int totalValue = 12;
        System.out.println(totalValue);
        totalValue++;
        System.out.println(totalValue);

        // Working with if else statements
        if (totalValue >= 14){
            System.out.println("Yikes! Your number \"" + totalValue + "\" is greater than or equal to 14!");
        }
        else {
            System.out.println("Yikes! Your number \"" + totalValue + "\" is less than 14!");
        }

        // Trying to read inputs and dealing with them
        Scanner in = new Scanner(System.in);
        System.out.println("How old are you?");
        int age = in.nextInt();
        System.out.println("Wow! Are you really " + age + " years old?");

        // Making a constant value
        final int DAYS_PER_WEEK = 7;

        // Tried to enum Weekdays... // Must not be local...
        // enum Weekday {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};

        // Converting a string into a number
        String str = "11010";
        int n = Integer.parseInt(str);
        System.out.println(n + 14);

        // Playing with Strings.
        String s1 = "Hello";
        String s2 = "BYU";
        String s3 = String.format("%s %s", s1, s2);
        String s4 = s1 + " " + s3;
        System.out.println(s3);
        System.out.println(s4);

    }
}