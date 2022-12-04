package ch.spoicy.day04;

public class Elf {

    private final int beginSection;
    private final int endSection;

    public Elf(final int beginSection, final int endSection) {
        this.beginSection = beginSection;
        this.endSection = endSection;
    }

    public boolean containsElf(Elf elf) {
        return this.beginSection <= elf.beginSection && this.endSection >= elf.endSection;
    }

    public boolean overlapsElf(Elf elf) {
        return (this.beginSection <= elf.beginSection && this.endSection >= elf.beginSection) ||
                (this.beginSection <= elf.endSection && this.endSection >= elf.endSection);
    }
}
