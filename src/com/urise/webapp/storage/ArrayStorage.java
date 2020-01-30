package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    Resume[] storage = new Resume[1000];
    private int storageSize = 0;

    public void save(Resume resume) {
        if (storageSize == storage.length) {
            System.out.println("[WARN] Добавить резюме невозможно, хранилище заполнено!");
        } else {
            if (getIndex(resume.getUuid()) == -1) {
                storage[storageSize] = resume;
                storageSize++;
            } else {
                System.out.println("[INFO] Резюме '" + resume.getUuid() + "' было создано ранее");
            }
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            Resume newResumeUuid = new Resume();
            newResumeUuid.setUuid(resume.getUuid());
            storage[index] = newResumeUuid;
        } else {
            System.out.println("[INFO] Резюме '" + resume.getUuid() + "' отсутсвует");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            System.out.println("[INFO] Резюме '" + uuid + "' отсутсвует");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("[INFO] Резюме '" + uuid + "' отсутсвует");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
