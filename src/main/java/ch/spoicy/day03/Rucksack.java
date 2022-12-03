package ch.spoicy.day03;

import java.util.ArrayList;

public final class Rucksack {

    public static final int OFFSET_LOWERCASE = 96;
    public static final int OFFSET_UPPERCASE = 64;
    private final ArrayList<Character> compartment1 = new ArrayList<>();
    private final ArrayList<Character> compartment2 = new ArrayList<>();

    public Rucksack(final String input) {
        int length = input.length();
        String comp1 = input.substring(0, length / 2);
        String comp2 = input.substring(length / 2, length);
        for (int i = 0; i < length / 2; i++) {
            compartment1.add(comp1.charAt(i));
            compartment2.add(comp2.charAt(i));
        }
    }

    public ArrayList<Character> getContents() {
        ArrayList<Character> list = new ArrayList<>(compartment1);
        list.addAll(compartment2);
        return list;
    }

    public char getCommonCharacter() {
        ArrayList<Character> list = new ArrayList<>(compartment1);
        list.retainAll(compartment2);
        return list.get(0);
    }

    public static int getPriorityValue(char character) {
        int c = character;
        if (c <= OFFSET_LOWERCASE) {
            c -= OFFSET_UPPERCASE - 26;
        } else if (c > OFFSET_LOWERCASE) {
            c -= OFFSET_LOWERCASE;
        } else {
            throw new RuntimeException("Unexpected character in input");
        }
        return c;
    }
}
