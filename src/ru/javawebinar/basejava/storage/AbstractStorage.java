package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        if (isExists(resume)) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveToStorage(resume);
    }

    @Override
    public void delete(String uuid) {
        if (!isExists(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        deleteFromStorage(uuid);
    }

    @Override
    public void update(Resume resume) {
        if (!isExists(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateInStorage(resume);
    }

    @Override
    public Resume get(String uuid) {
        if (!isExists(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getFromStorage(uuid);
    }


    protected abstract void saveToStorage(Resume resume);

    protected abstract void deleteFromStorage(String uuid);

    protected abstract void updateInStorage(Resume resume);

    protected abstract Resume getFromStorage(String uuid);

    protected abstract boolean isExists(Resume resume);

}
