package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void saveToStorage(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public void deleteFromStorage(String uuid) {
        map.remove(uuid);
    }

    @Override
    public void updateInStorage(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Resume getFromStorage(String uuid) {
        return map.get(uuid);
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public boolean isExist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected int getIndex(String uuid) {
        return -1;
    }

}
