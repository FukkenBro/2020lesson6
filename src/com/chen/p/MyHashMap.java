package com.chen.p;

import java.util.Arrays;

public class MyHashMap implements MyMap {

    private static class HashMapEntry implements MyMap.Entry {
        private String key;
        private String value;
        private int hashCode;
        private HashMapEntry next;

        private HashMapEntry(String key, String value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + value;
        }
    }

    private int size = 0;
    private HashMapEntry[] table = new HashMapEntry[16];
    private final double LOADFACTOR = 0.75;
    private double threshold = LOADFACTOR * table.length;

    private void checkCapacity() {
        if (size >= threshold) {
            resize();
        }
    }

    @Override
    public void clear() {
        size = 0;
        table = new HashMapEntry[16];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String put(String key, String value) {
        String result = putInternal(key, value);
        if (result == null) {
            size++;
        }
        checkCapacity();
        return result;
    }

    private String putInternal(String key, String value) {
        HashMapEntry newEntry = new HashMapEntry(key, value, key.hashCode());
        int position = getIndex(key);
        // проверить что в цепочке такого еще нет
        if (table[position] != null) {
            HashMapEntry tmp = table[position];
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    // если нашли такой заменяем ему value и возвращаем старое
                    String oldValue = tmp.value;
                    tmp.value = value;
                    return oldValue;
                }
                tmp = tmp.next;
            }
            // return не сработал, значит такого еще нет
            newEntry.next = table[position];
        }
        table[position] = newEntry;
        return null;
    }

    private void resize() {
        Entry[] arr = toArray();
        table = new HashMapEntry[table.length * 2];
        threshold = LOADFACTOR * table.length;
        for (Entry entry : arr) {
            putInternal(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean containsKey(String key) {
        HashMapEntry tmp = table[getIndex(key)];
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    @Override
    public String get(String key) {
        HashMapEntry tmp = table[getIndex(key)];
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                return tmp.toString();
            }
            tmp = tmp.next;
        }
        return null;
    }

    @Override
    public String remove(String key) {
        HashMapEntry tmp = table[getIndex(key)];
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                // если нашли такой заменяем ему value и возвращаем старое
                String oldValue = tmp.value;
                tmp.value = null;
                tmp.key = null;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public Entry[] toArray() {
        HashMapEntry[] result = new HashMapEntry[size];
        int index = 0;
        for (HashMapEntry tmp : table) {
            while (tmp != null) {
                result[index] = tmp;
                tmp = tmp.next;
                index++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private int getIndex(String key) {
        int hash = key.hashCode();
        return Math.abs(hash % table.length);
    }
}
