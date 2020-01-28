package com.urise.webapp.storage;

import java.util.Arrays;

public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        }
        else
            System.out.println("Резюме '" + uuid + "' отсутсвует");
    }

    void clear() {
        Arrays.fill(storage, null);
        storageSize = 0;
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1)
            return null;
        else
            return storage[index];
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    int size() {
        return storageSize;
    }

    private int getIndex(String uuid){
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }
}
