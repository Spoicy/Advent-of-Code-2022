package ch.spoicy.day07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int limit = 30000000;
    public static int totalDiskSpace = 70000000;
    static class EventListener implements NodeEventListener {

        private int sum = 0;
        public int closest = 0;
        public int necessaryRemoved = 0;
        @Override
        public void nodeEventUpdate(NodeEvent evt) {
            if (evt.getType() == null) {
                LOG.error("Type of null passed to listener: " + getClass());
            }
            if (evt.getType().equals(NodeEventType.SUM)) {
                sum += evt.getValue();
            } else if (evt.getType().equals(NodeEventType.CLOSEST)) {
                if (evt.getValue() < necessaryRemoved || closest == 0) {
                    return;
                }
                if (closest - necessaryRemoved > evt.getValue() - necessaryRemoved && evt.getValue() >= necessaryRemoved) {
                    closest = evt.getValue();
                }
            }
        }

        public int getSum() {
            return sum;
        }

        public int getClosest() {
            return closest;
        }
    }

    private static final Logger LOG =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BufferedReader reader;
        FileSystemNode rootNode = new FileSystemNode(null, new ArrayList<FileSystemNode>(), "/");
        FileSystemNode currentNode = rootNode;
        List<Integer> sumSizes = new ArrayList<>();
        EventListener listener = new EventListener();
        currentNode.addNodeEventListener(listener);
        try {
            reader = new BufferedReader(new FileReader("E:\\aoc2022inputs\\day07.txt"));
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                if (line.contains("$ cd")) {
                    String dir = line.substring(5);
                    if (dir.equals("..")){
                        currentNode = currentNode.getParentNode();
                    } else {
                        for (final FileSystemNode node : currentNode.getDirectoryFiles()) {
                            if (dir.equals(node.getName())) {
                                currentNode = node;
                                break;
                            }
                        }
                    }
                    line = reader.readLine();
                } else if (line.contains("$ ls")) {
                    line = reader.readLine();
                    while (line != null && !line.contains("$ cd")) {
                        String[] info = line.split(" ");
                        if (info[0].equals("dir")) {
                            FileSystemNode newNode = new FileSystemNode(currentNode, new ArrayList<FileSystemNode>(), info[1]);
                            newNode.addNodeEventListener(listener);
                            currentNode.addToList(newNode);
                        } else {
                            currentNode.addToList(new FileSystemNode(currentNode, Integer.parseInt(info[0]), info[1]));
                        }
                        //System.out.println(line);
                        line = reader.readLine();
                    }
                }
            }
            int currentSpace = rootNode.getSize(100000);
            System.out.println("Current occupied space: " + currentSpace);
            int necessaryRemoved = currentSpace - (totalDiskSpace - limit);
            listener.necessaryRemoved = necessaryRemoved;
            listener.closest = currentSpace;
            System.out.println("Sum of under " + 100000 + ": " + listener.getSum());
            rootNode.getSize(necessaryRemoved);
            System.out.println("Closest to " + listener.necessaryRemoved + ": " + listener.getClosest());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentNode = rootNode;
    }
}
