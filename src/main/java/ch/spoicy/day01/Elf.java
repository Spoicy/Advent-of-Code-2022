package ch.spoicy.day01;

import java.util.Objects;

public class Elf implements Comparable<Elf> {

    private int total;

    public Elf(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Elf e)) {
            return false;
        }
        return (total == total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total);
    }

    @Override
    public int compareTo(Elf o) {
        return Integer.compare(o.total, this.total);
    }

    @Override
    public String toString() {
        return "Elf{" +
                "total=" + total +
                '}';
    }
}
