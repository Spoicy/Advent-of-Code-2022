package ch.spoicy.day02;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Logger LOG =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BufferedReader reader;
        ArrayList<Match> matchList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Mode mode = null;
        do {
            System.out.println("What should the second input indicate? (moves or state)");
            String input = scan.next();
            try {
                mode = Mode.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                LOG.error("Mode not available.");
            }
        } while (mode == null);
        int sum = 0;
        try {
            reader = new BufferedReader(new FileReader("E:\\aoc2022inputs\\day02.txt"));
            String line = reader.readLine();
            while (line != null) {
                matchList.add(new Match(line.charAt(0), line.charAt(2), mode));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (final Match m : matchList) {
            sum += m.getMatchValue();
        }
        System.out.println(sum);
    }
}
