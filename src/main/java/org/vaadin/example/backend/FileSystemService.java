package org.vaadin.example.backend;

public class FileSystemService {
    private final VirtualDirectory root;
    private VirtualDirectory currentDirectory;

    public FileSystemService() {
        root = new VirtualDirectory("/");
        currentDirectory = root;

        // Définition dynamique des sections du site
        root.addSubDirectory("api");
        root.getSubDirectory("api").addSubDirectory("users");
        root.addSubDirectory("data");

        root.getSubDirectory("data").addFile("file1.txt", "Contenu du fichier 1");
    }

    public VirtualDirectory getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(VirtualDirectory directory) {
        this.currentDirectory = directory;
    }

    public String listContents() {
        return currentDirectory.listContents();
    }

    public String changeDirectory(String path) {
        VirtualDirectory newDirectory = path.equals("/") ? root : currentDirectory.changeDirectory(path);
        if (newDirectory != null) {
            currentDirectory = newDirectory;
            return "Moved to " + path;
        }
        return "Directory not found: " + path;
    }
}