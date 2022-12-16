package ch.spoicy.day08;

import java.util.ArrayList;
import java.util.List;

public final class ForestMap {

    private final List<List<Tree>> treeMap = new ArrayList<>();

    public List<List<Tree>> getTreeMap() {
        return treeMap;
    }

    public int getColumnAmount() {
        return treeMap.get(0).size();
    }

    public int getRowAmount() {
        return treeMap.size();
    }

    public void setRow(final int y, final List<Tree> row) {
        treeMap.add(row);
    }

    public Tree getTree(final int x, final int y) {
        return treeMap.get(y).get(x);
    }

    public List<Tree> getRow(final int y) {
        return treeMap.get(y);
    }

    public List<Tree> getPartialRow(final int y, final int fromX, final int toX) {
        List<Tree> list = new ArrayList<>();
        for (int i = fromX; i <= toX; i++) {
            list.add(treeMap.get(y).get(i));
        }
        return list;
    }

    public List<Tree> getColumn(final int x) {
        List<Tree> list = new ArrayList<>();
        for (int i = 0; i < getRowAmount(); i++) {
            list.add(treeMap.get(i).get(x));
        }
        return list;
    }

    public List<Tree> getPartialColumn(final int x, final int fromY, final int toY) {
        List<Tree> list = new ArrayList<>();
        for (int i = fromY; i <= toY; i++) {
            list.add(treeMap.get(i).get(x));
        }
        return list;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < treeMap.size(); i++) {
            for (int j = 0; j < treeMap.get(0).size(); j++) {
                output += treeMap.get(i).get(j);
            }
            output += "\n";
        }
        return output;
    }
}
