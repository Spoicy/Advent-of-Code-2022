package ch.spoicy.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader;
        int sumIndividual = 0;
        int sumGroups = 0;
        try {
            reader = new BufferedReader(new FileReader("E:\\aoc2022inputs\\day03.txt"));
            String line = reader.readLine();
            int i = 0;
            List<Rucksack> list = new ArrayList<>();
            while (line != null) {
                Rucksack r = new Rucksack(line);
                list.add(r);
                if (i == 2) {
                    ElfGroup group = new ElfGroup(list);
                    sumGroups += Rucksack.getPriorityValue(group.getCommonCharacter());
                    i = 0;
                    list.clear();
                } else {
                    i++;
                }
                sumIndividual += Rucksack.getPriorityValue(r.getCommonCharacter());
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sum of individual elves: " + sumIndividual);
        System.out.println("Sum of elf groups: " + sumGroups);
    }
}
