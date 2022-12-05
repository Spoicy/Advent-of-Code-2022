package ch.spoicy.day05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main2 {

    private static final Logger LOG =
            LogManager.getLogger(Main2.class);

    public static void main(String[] args) {
        BufferedReader reader;
        List<Deque<Character>> dequeList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("C:\\aoc2022inputs\\day05.txt"));
            String line = reader.readLine();
            // Read stacks
            for (int i = 1; i < line.length(); i+=4) {
                dequeList.add(new ArrayDeque<>());
            }
            while (line.charAt(1) != '1') {
                for (int i = 0; i < dequeList.size(); i++) {
                    if (line.charAt(i*4+1) != ' ') {
                        dequeList.get(i).add(line.charAt(i*4+1));
                    }
                }
                line = reader.readLine();
            }
            reader.readLine();
            line = reader.readLine();
            Deque<Character> tempList = new ArrayDeque<>();
            while (line != null) {
                tempList.clear();
                String[] lineSplit = line.split("move | from | to ");
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    char c = dequeList.get(Integer.parseInt(lineSplit[2]) - 1).pop();
                    tempList.push(c);
                }
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    dequeList.get(Integer.parseInt(lineSplit[3]) - 1).push(tempList.pop());
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output = "";
        for (int i = 0; i < dequeList.size(); i++) {
            output += dequeList.get(i).getFirst();
        }
        System.out.println(output);
    }
}
