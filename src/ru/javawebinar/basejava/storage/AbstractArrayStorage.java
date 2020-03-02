package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void saveToStorage(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExists(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            saveToStorage(resume, index);
            size++;
        }
    }

    @Override
    public void deleteFromStorage(String uuid) {
        int index = getIndex(uuid);
        if (!isExists(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void updateInStorage(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (!isExists(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
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
        int index = getIndex(uuid);
        if (!isExists(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected boolean isExists(Resume resume) {
        return getIndex(resume.getUuid()) >= 0 ? true : false;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveToStorage(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}
