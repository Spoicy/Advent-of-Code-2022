package ch.spoicy.day03;

import java.util.ArrayList;
import java.util.List;

public class ElfGroup {
    private final Rucksack elfRucksack1;
    private final Rucksack elfRucksack2;
    private final Rucksack elfRucksack3;

    public ElfGroup(Rucksack elfRucksack1, Rucksack elfRucksack2, Rucksack elfRucksack3) {
        this.elfRucksack1 = elfRucksack1;
        this.elfRucksack2 = elfRucksack2;
        this.elfRucksack3 = elfRucksack3;
    }

    public ElfGroup(List<Rucksack> list) {
        this.elfRucksack1 = list.get(0);
        this.elfRucksack2 = list.get(1);
        this.elfRucksack3 = list.get(2);
    }

    public char getCommonCharacter() {
        List<Character> list = new ArrayList<>(elfRucksack1.getContents());
        list.retainAll(elfRucksack2.getContents());
        list.retainAll(elfRucksack3.getContents());
        return list.get(0);
    }
}
