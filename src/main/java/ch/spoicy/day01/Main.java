package ch.spoicy.day01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader;
        int current = 0;
        ArrayList<Elf> elfList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("E:\\aoc2022inputs\\day01.txt"));
            String line = reader.readLine();

            while (line != null) {
                if (line.length() == 0) {
                    elfList.add(new Elf(current));
                    current = 0;
                    // read next line
                    line = reader.readLine();
                    continue;
                }
                current += Integer.parseInt(line);
                // read next line
                line = reader.readLine();
            }
            elfList.add(new Elf(current));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Collections.sort(elfList);
        System.out.println("First Elf: " + elfList.get(0).getTotal());
        System.out.println("Second Elf: " + elfList.get(1).getTotal());
        System.out.println("Third Elf: " + elfList.get(2).getTotal());
        System.out.println("Total calories of top three: " + (elfList.get(0).getTotal() + elfList.get(1).getTotal() + elfList.get(2).getTotal()));
    }
}
