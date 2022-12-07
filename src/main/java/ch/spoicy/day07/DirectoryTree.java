package ch.spoicy.day07;

import java.util.*;

public class DirectoryTree {

    private Map<Character, Character> directoryList = new HashMap<>();
    private Map<Character, Character> fileList = new HashMap<>();
    private Map<Character, Integer> fileInfos = new HashMap<>();
    private Map<Character, Integer> directorySizes = new HashMap<>();

    public DirectoryTree() {
        directoryList.put('/', '/');
    }

    public void addDirectory(char dir, char parent) {
        directoryList.put(dir, parent);
    }

    public void addFile(char file, int size, char parent) {
        fileList.put(file, parent);
        fileInfos.put(file, size);
    }

    public int getDirectorySize(char dir) {
        if (directorySizes.containsKey(dir)) {
            return directorySizes.get(dir);
        }
        throw new RuntimeException("Directory size not calculated");
    }

    public void setDirectorySize(char dir) {
        
    }

    public void calculateDirectorySizes() {
        Set<Character> directories = directoryList.keySet();
        Collection<Character> parents = directoryList.values();
        Set<Character> childlessDirs = new HashSet<>(directories);
        childlessDirs.retainAll(parents);
        for (final char dir : childlessDirs) {

        }
    }
}
