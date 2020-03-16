package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        getExistedIndex(resume);
        saveToStorage(resume);
    }

    @Override
    public void delete(String uuid) {
        getNotExistedIndex(new Resume(uuid)); // Как избежать создания нового резюме?
        deleteFromStorage(uuid);
    }

    @Override
    public void update(Resume resume) {
        getNotExistedIndex(resume);
        updateInStorage(resume);
    }

    @Override
    public Resume get(String uuid) {
        getNotExistedIndex(new Resume(uuid));
        return getFromStorage(uuid);
    }

    private void getExistedIndex(Resume resume) {
        if (isExist(resume)) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    private void getNotExistedIndex(Resume resume) {
        if (!isExist(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void saveToStorage(Resume resume);

    protected abstract void deleteFromStorage(String uuid);

    protected abstract void updateInStorage(Resume resume);

    protected abstract Resume getFromStorage(String uuid);

    protected abstract boolean isExist(Resume resume);

    protected abstract int getIndex(String uuid);

}
