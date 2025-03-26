package org.vaadin.projet10.backend;

import java.util.HashMap;
import java.util.Map;

public class VirtualDirectory {
    private final String name;
    private final Map<String, VirtualDirectory> subDirectories;
    private final Map<String, String> files;
    private String path;

    public VirtualDirectory(String name) {
        this.name = name;
        this.subDirectories = new HashMap<>();
        this.files = new HashMap<>();
        this.path = name.equals("/") ? "/" : "/" + name;
    }

    public String getName() {
        return name;
    }

    public Map<String, VirtualDirectory> getSubDirectories() {
        return subDirectories;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addSubDirectory(String name) {
        subDirectories.put(name, new VirtualDirectory(name));
    }

    public void addFile(String name, String content) {
        files.put(name, content);
    }

    public boolean hasSubDirectory(String name) {
        return subDirectories.containsKey(name);
    }

    public VirtualDirectory getSubDirectory(String name) {
        return subDirectories.get(name);
    }

    public String listContents() {
        StringBuilder result = new StringBuilder();
        subDirectories.keySet().forEach(dir -> result.append(dir).append("/\n"));
        files.keySet().forEach(file -> result.append(file).append("\n"));
        return result.toString();
    }

    public VirtualDirectory changeDirectory(String path) {
        if (path.equals("/")) {
            return this; // Retourne la racine
        } else if (this.hasSubDirectory(path)) {
            return this.getSubDirectory(path);
        }
        return null; // Répertoire non trouvé
    }
}