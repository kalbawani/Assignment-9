/**
 * This code solves Exercise 5.3 of Assignment 5, but it contains 3 bugs.
 * Review the code and report the bugs by writing a comment below the right code line.
 */
import java.util.Scanner;

public class ParkingCharges {
    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int MIN_DURATION = 3;
    public static final int MAX_DURATION = 24;
    public static final double MIN_DAILY_CHARGE = 2.0;
    public static final double MAX_DAILY_CHARGE = 10.0;
    public static final double ADDITIONAL_CHARGE_PER_HOUR = 0.50;

    public static void main (String[] args) {
        int startTime = readTime("Enter your parking start time:");
        int endTime = readTime("Enter your parking end time:");

        int durationInMinutes = computeParkingDuration(startTime, endTime);

        if (durationInMinutes > MAX_DURATION * MINUTES_PER_HOUR) {
            System.out.printf("Your parking duration exceeded %d hours. Please refer to the garage administration!%n",
                    MAX_DURATION);
            return;
        }

        double parkingCharges = computeParkingCharges(durationInMinutes);

        printResults(durationInMinutes, parkingCharges);
    }

    public static int readTime(String message){
        Scanner input = new Scanner(System.in);

        System.out.printf("%s ", message);
        int time = input.nextInt();

        while (time < 0 || time > 2359) {
            System.out.print("The entered value is not a valid time, please enter a new value: ");
            time = input.nextInt();
        }

        return time;
    }

    public static int computeParkingDuration(int startTime, int endTime) {
        int durationInMinutes = 0;

        int startMinute = (startTime / 100) * MINUTES_PER_HOUR + startTime % 100;
        int endMinute = (endTime / 100) * MINUTES_PER_HOUR + endTime % 100;

        if (endMinute >= startMinute) {
            durationInMinutes = endMinute - startMinute;
        } else {
            durationInMinutes = HOURS_PER_DAY * MINUTES_PER_HOUR - startMinute;
            durationInMinutes += endMinute;
        }

        return durationInMinutes;
    }

    public static double computeParkingCharges(int durationInMinutes) {
        double durationInWholeHours = Math.ceil(durationInMinutes / MINUTES_PER_HOUR);
        double charges = MIN_DAILY_CHARGE;

        if ( durationInWholeHours > MIN_DURATION) {
            charges += (durationInWholeHours - MIN_DURATION) * ADDITIONAL_CHARGE_PER_HOUR;
        }

        return (charges > MAX_DAILY_CHARGE ? MAX_DAILY_CHARGE : charges);
    }

    public static void printResults(int durationInMinutes, double charges) {
        System.out.printf("%nYour parking duration is %d hour(s) and %d minute(s).%n",
                durationInMinutes / 100, durationInMinutes % 100);
        System.out.printf("You have to pay €%.2f%n",charges);
    }
}
