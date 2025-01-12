package com.design.lld;

import java.util.Objects;

public class ImplHashMap<K, V> {

    private static final int INITIAL_SIZE = 1 << 4; // 16
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private Entry<K, V>[] hashTable;
    private int size = 0;
    private float loadFactor = 0.75f; // Default load factor
    private int threshold;

    ImplHashMap() {
        hashTable = new Entry[INITIAL_SIZE];
        threshold = (int) (INITIAL_SIZE * loadFactor);
    }

    ImplHashMap(int capacity) {
        int tableSize = tableSizeFor(capacity);
        hashTable = new Entry[tableSize];
        threshold = (int) (tableSize * loadFactor);
    }

    final int tableSizeFor(int cap) {
        return (cap <= 0) ? 1 : (cap >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : Integer.highestOneBit((cap - 1) << 1);
    }
    

    class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    public void put(K key, V value) {
        if (size >= threshold) {
            resize();
        }

        int hashCode = (key == null) ? 0 : key.hashCode() & (hashTable.length - 1);
        Entry<K, V> node = hashTable[hashCode];

        if (node == null) {
            hashTable[hashCode] = new Entry<>(key, value);
            size++;
        } else {
            Entry<K, V> previousNode = node;
            while (node != null) {
                if (Objects.equals(node.key, key)) {
                    node.value = value; // Update value if key exists
                    return;
                }
                previousNode = node;
                node = node.next;
            }
            previousNode.next = new Entry<>(key, value);
            size++;
        }
    }

    public V get(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode() & (hashTable.length - 1);
        Entry<K, V> node = hashTable[hashCode];

        while (node != null) {
            if (Objects.equals(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    private void resize() {
        int newCapacity = hashTable.length << 1; // Double the capacity
        if (newCapacity > MAXIMUM_CAPACITY) {
            return; // Do not resize if at max capacity
        }

        Entry<K, V>[] newTable = new Entry[newCapacity];
        for (Entry<K, V> headNode : hashTable) {
            while (headNode != null) {
                Entry<K, V> nextNode = headNode.next;

                int hashCode = (headNode.key == null) ? 0 : headNode.key.hashCode() & (newCapacity - 1);
                headNode.next = newTable[hashCode];
                newTable[hashCode] = headNode;

                headNode = nextNode;
            }
        }
        hashTable = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    public static void main(String[] args) {
        ImplHashMap<Integer, String> map = new ImplHashMap<>(7);
        map.put(1, "hi");
        map.put(2, "my");
        map.put(3, "name");
        map.put(4, "is");
        map.put(5, "Sandeep");
        map.put(6, "how");
        map.put(7, "are");
        map.put(8, "you");
        map.put(9, "friends");
        map.put(10, "?");

        String value = map.get(8);
        System.out.println(value); // Output: you
    }
}
