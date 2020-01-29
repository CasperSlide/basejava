package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {

    Resume[] storage = new Resume[1000];
    private int storageSize = 0;

    public void save(Resume r) {
        if (storageSize == storage.length) {
            System.out.println("[WARN] Добавить резюме невозможно, хранилище заполнено!");
        } else {
            if (getIndex(r.getUuid()) == -1) {
                storage[storageSize] = r;
                storageSize++;
            } else {
                System.out.println("[INFO] Резюме '" + r.getUuid() + "' было создано ранее");
            }
        }
    }

    public void update(Resume r, String newUuid){
        int index = getIndex(r.getUuid());
        if (index != -1) {
            if (newUuid == null){
                newUuid = "new_" + r.getUuid();
            }
            Resume newResumeUuid = new Resume();
            newResumeUuid.setUuid(newUuid);
            storage[index] = newResumeUuid;
        }
        else {
            System.out.println("[INFO] Резюме '" + r.getUuid() + "' отсутсвует");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        }
        else {
            System.out.println("[INFO] Резюме '" + uuid + "' отсутсвует");
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        storageSize = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        else {
            System.out.println("[INFO] Резюме '" + uuid + "' отсутсвует");
            return null;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }

    private int getIndex(String uuid){
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
