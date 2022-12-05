package ch.spoicy.day05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final Logger LOG =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BufferedReader reader;
        List<Deque<Character>> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("C:\\aoc2022inputs\\day05.txt"));
            String line = reader.readLine();
            // Read stacks
            for (int i = 1; i < line.length(); i+=4) {
                list.add(new ArrayDeque<>());
            }
            while (line.charAt(1) != '1') {
                for (int i = 0; i < list.size(); i++) {
                    if (line.charAt(i*4+1) != ' ') {
                        list.get(i).add(line.charAt(i*4+1));
                    }
                }
                line = reader.readLine();
            }
            line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] test = line.split("move | from | to ");
                for (int i = 0; i < Integer.parseInt(test[1]); i++) {
                    char c = list.get(Integer.parseInt(test[2]) - 1).pop();
                    list.get(Integer.parseInt(test[3]) - 1).push(c);
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += list.get(i).getFirst();
        }
        System.out.println(output);
    }
}
