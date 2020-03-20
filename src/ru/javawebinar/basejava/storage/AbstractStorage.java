package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        getNotExistedIndex(resume.getUuid());
        saveToStorage(resume);
    }

    @Override
    public void delete(String uuid) {
        getExistedIndex(uuid);
        deleteFromStorage(uuid);
    }

    @Override
    public void update(Resume resume) {
        getExistedIndex(resume.getUuid());
        updateInStorage(resume);
    }

    @Override
    public Resume get(String uuid) {
        getExistedIndex(uuid);
        return getFromStorage(uuid);
    }

    private void getNotExistedIndex(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
    }

    private void getExistedIndex(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }


    protected abstract void saveToStorage(Resume resume);

    protected abstract void deleteFromStorage(String uuid);

    protected abstract void updateInStorage(Resume resume);

    protected abstract Resume getFromStorage(String uuid);

    protected abstract boolean isExist(String uuid);

    protected abstract int getKey(String uuid);

}
