package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class FileCabinet implements Cabinet, MultiFolder {
    private final List<Folder> folders;
    private final String folderName;
    private final String size;

    private FileCabinet(Builder builder) {
        this.folderName = builder.folderName;
        this.folders = builder.folders;
        this.size = builder.size;
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return getFolders().stream().filter(folder -> folder.getName().equals(name)).findAny();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return getFolders().stream().filter(folder -> folder.getSize().equals(size)).toList();
    }

    @Override
    public int count() {
        return getFolders().size();
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }

    @Override
    public String getName() {
        return folderName;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(folderName, folders, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileCabinet that = (FileCabinet) o;
        return Objects.equals(folderName, that.folderName) && Objects.equals(folders, that.folders) && Objects.equals(size, that.size);
    }

    public static class Builder {
        private String folderName;
        private List<Folder> folders = new ArrayList<>();
        private String size;

        public Builder folderName(String folderName) {
            this.folderName = folderName;
            return this;
        }

        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder folders(List<Folder> folders) {
            this.folders = folders;
            return this;
        }


        public FileCabinet build() {
            return new FileCabinet(this);
        }
    }

}
