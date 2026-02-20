package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileCabinetTest {


    private Folder folder1;
    private Folder folder2;
    private Folder folder3;
    private Folder folder4;
    private FileCabinet cabinet;

    @BeforeEach
    void setUp() {
        folder1 = new FileCabinet.Builder().folderName("Dokumenty").size("SMALL").build();
        folder2 = new FileCabinet.Builder().folderName("Zdjecia").size("LARGE").build();
        folder3 = new FileCabinet.Builder().folderName("Filmy").size("MEDIUM").build();
        folder4 = new FileCabinet.Builder().folderName("Muzyka").size("MEDIUM").build();

        cabinet = new FileCabinet.Builder().folders(List.of(folder1, folder2, folder3, folder4)).build();
    }

    @Test
    void shouldFindFolderByName() {

        //when
        Optional<Folder> result = cabinet.findFolderByName("Dokumenty");

        //then
        assertTrue(result.isPresent());
        assertEquals(folder1, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenFolderNotFound() {
        //when
        Optional<Folder> result = cabinet.findFolderByName("NonExisting");

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindFoldersBySize() {
        //when
        List<Folder> result = cabinet.findFoldersBySize("MEDIUM");

        //then
        assertEquals(2, result.size());
        assertEquals(result, List.of(folder3, folder4));
    }

    @Test
    void shouldReturnEmptyListWhenNoFoldersWithGivenSize() {
        //when
        List<Folder> result = cabinet.findFoldersBySize("XS");

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnCorrectCount() {

        //when
        int folderSize = cabinet.count();

        //then
        assertEquals(4, folderSize);
    }

}

