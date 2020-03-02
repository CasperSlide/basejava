package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;

public class ListStorage extends AbstractStorage {

    private LinkedList<Resume> list = new LinkedList<>();

    @Override
    public void saveToStorage(Resume resume) {
        list.add(resume);
    }

    @Override
    public void deleteFromStorage(String uuid) {
        list.remove(new Resume(uuid));
    }

    @Override
    public void updateInStorage(Resume resume) {
        list.set(list.indexOf(resume), resume);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Resume getFromStorage(String uuid) {
        return list.get(list.indexOf(new Resume(uuid)));
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public boolean isExists(Resume resume) {
        return list.contains(resume);
    }

}
