package ch.spoicy.day08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    private static final Logger LOG =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BufferedReader reader;
        ForestMap forestMap = new ForestMap();
        try {
            reader = new BufferedReader(new FileReader("E:\\aoc2022inputs\\day08.txt"));
            String line = reader.readLine();
            int rowNum = 0;
            while (line != null) {
                List<Tree> treeList = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    treeList.add(new Tree(Character.getNumericValue(line.charAt(i))));
                }
                forestMap.setRow(rowNum, treeList);
                rowNum++;
                // read next line
                line = reader.readLine();
            }
            System.out.println(forestMap);
            List<Tree> outsideGrid = new ArrayList<>();
            outsideGrid.addAll(forestMap.getColumn(0));
            outsideGrid.addAll(forestMap.getColumn(forestMap.getColumnAmount() - 1));
            outsideGrid.addAll(forestMap.getPartialRow(0, 1, forestMap.getColumnAmount() - 1));
            outsideGrid.addAll(forestMap.getPartialRow(forestMap.getRowAmount() - 1, 1, forestMap.getColumnAmount() - 1));
            outsideGrid.forEach(Tree::makeVisible);
            // Rows, y-axis
            for (int row = 1; row < forestMap.getColumnAmount() - 1; row++) {
                // Columns, x-axis
                for (int col = 1; col < forestMap.getRowAmount() - 1; col++) {
                    Tree t = forestMap.getTree(col, row);
                    /**
                    if (t.isVisible()) {
                        continue;
                    } */
                    // Row checks
                    boolean rowLeft = true, rowRight = true;
                    for (int k = 0; k < forestMap.getColumnAmount(); k++) {
                        Tree t2 = forestMap.getTree(col, k);
                        if (k < row && t2.getHeight() >= t.getHeight()) {
                            rowLeft = false;
                        } else if (k > row && t2.getHeight() >= t.getHeight()) {
                            rowRight = false;
                        }
                    }
                    if (rowLeft || rowRight) {
                        t.makeVisible();
                        continue;
                    }
                    // Column checks
                    boolean columnTop = true, columnBottom = true;
                    for (int k = 0; k < forestMap.getRowAmount(); k++) {
                        Tree t2 = forestMap.getTree(k, row);
                        if (k < col && t2.getHeight() >= t.getHeight()) {
                            columnTop = false;
                        } else if (k > col && t2.getHeight() >= t.getHeight()) {
                            columnBottom = false;
                        }
                    }
                    if (columnTop || columnBottom) {
                        t.makeVisible();
                    }
                }
            }
            int sum = 0;
            for (int row = 0; row < forestMap.getColumnAmount(); row++) {
                String rowString = "";
                for (int col = 0; col < forestMap.getRowAmount(); col++) {
                    if (forestMap.getTree(col, row).isVisible()) {
                        rowString += "1";
                        sum++;
                    } else {
                        rowString += "0";
                    }
                }
                System.out.println(rowString);
            }
            System.out.println(sum);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
