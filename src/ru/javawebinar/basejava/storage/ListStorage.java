package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new LinkedList<>();

    @Override
    public void saveToStorage(Resume resume) {
        list.add(resume);
    }

    @Override
    public void deleteFromStorage(String uuid) {
        list.remove(getIndex(uuid));
    }

    @Override
    public void updateInStorage(Resume resume) {
        list.set(getIndex(resume.getUuid()), resume);
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
        return list.get(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public boolean isExist(Resume resume) {
        return list.contains(resume);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getUuid()).equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
