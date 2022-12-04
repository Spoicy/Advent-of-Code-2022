package ch.spoicy.day04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static final Logger LOG =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BufferedReader reader;
        int sumContains = 0;
        int sumOverlaps = 0;
        try {
            reader = new BufferedReader(new FileReader("E:\\aoc2022inputs\\day04.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] pairs = line.split(",");
                String[] sections1 = pairs[0].split("-");
                String[] sections2 = pairs[1].split("-");
                Elf elf1 = new Elf(Integer.parseInt(sections1[0]), Integer.parseInt(sections1[1]));
                Elf elf2 = new Elf(Integer.parseInt(sections2[0]), Integer.parseInt(sections2[1]));
                if (elf1.containsElf(elf2) || elf2.containsElf(elf1)) {
                    sumContains++;
                }
                if (elf1.overlapsElf(elf2) || elf2.overlapsElf(elf1)) {
                    sumOverlaps++;
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sum of contains: " + sumContains);
        System.out.println("Sum of overlaps: " + sumOverlaps);
    }
}
