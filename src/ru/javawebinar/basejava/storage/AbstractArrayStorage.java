package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void saveToStorage(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, getKey(resume.getUuid()));
            size++;
        }
    }

    @Override
    public void deleteFromStorage(String uuid) {
        fillDeletedElement(getKey(uuid));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void updateInStorage(Resume resume) {
        storage[getKey(resume.getUuid())] = resume;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume getFromStorage(String uuid) {
        return storage[getKey(uuid)];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected boolean isExist(String uuid) {
        return getKey(uuid) >= 0;
    }

    protected abstract int getKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}
