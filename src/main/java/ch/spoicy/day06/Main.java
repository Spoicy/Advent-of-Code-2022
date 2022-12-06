package ch.spoicy.day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader;
        Queue<Character> queue = new ArrayDeque<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter integer length of marker:");
        int input = scan.nextInt();
        try {
            reader = new BufferedReader(new FileReader("C:\\aoc2022inputs\\day06.txt"));
            String line = reader.readLine();
            // Load the first characters in
            for (int j = 0; j < input-1; j++) {
                queue.offer(line.charAt(j));
            }
            Set<Character> set = new HashSet<>();
            int i;
            for (i = input-1; i < line.length(); i++) {
                boolean hasDuplicate = false;
                queue.offer(line.charAt(i));
                for (final char c : queue) {
                    if (!set.add(c)) {
                        hasDuplicate = true;
                        break;
                    }
                }
                set.clear();
                if (!hasDuplicate) {
                    break;
                }
                queue.poll();
            }
            System.out.println(queue);
            System.out.println(i+1);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
