package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    private Storage storage;

    private static final String UUID_1 = "A1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "A2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "A3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
    }

    // Save
    @Test
    public void save() {
        assertEquals(RESUME_1, storage.get(UUID_1));
        assertEquals(2, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveWithStorageOverflow() {
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - 2; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Storage overflow");
        }
        storage.save(new Resume());
    }

    // Delete
    @Test
    public void delete() {
        assertEquals(2, storage.size());
        storage.delete(UUID_1);
        assertEquals(1, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_1);
    }

    // Update
    @Test
    public void update() {
        storage.update(RESUME_1);
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_3);
    }

    // Clear
    @Test
    public void clear() {
        assertEquals(2, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }

    // Size
    @Test
    public void size() {
        storage.clear();
        storage.save(new Resume());
        assertEquals(1, storage.size());
        storage.save(new Resume());
        assertEquals(2, storage.size());
        storage.save(new Resume());
        assertEquals(3, storage.size());
    }

    // Get
    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_3);
    }

    // getAll
    @Test
    public void getAll() {
        String[] uuids = {UUID_1, UUID_2, UUID_3};
        storage.save(RESUME_3);
        assertEquals(3, storage.getAll().length);

        Resume[] resumes = storage.getAll();
        for (int i = 0; i < uuids.length; i++) {
            assertEquals(new Resume(uuids[i]), resumes[i]);
        }
    }

}