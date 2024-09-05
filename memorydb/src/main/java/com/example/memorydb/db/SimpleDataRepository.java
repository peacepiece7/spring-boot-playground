package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {

    private final List<T> dataList = new ArrayList<T>();

    private static long index = 0;

    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    Comparator<T> sortRamda = (o1, o2) -> Long.compare(o1.getId(), o2.getId());
    Comparator<T> sortMethodRef = Comparator.comparingLong(Entity::getId);

    // create
    @Override
    public T save(T data) {

        if(Objects.isNull(data)) {
            throw new RuntimeException("Data is null");
        }

        var prevData = dataList.stream()
                .filter(it -> it.getId().equals(data.getId()))
                .findFirst();

        prevData.ifPresent(dataList::remove); //  prevData.ifPresent(it -> {dataList.remove(it)});
        if(prevData.isEmpty()) data.setId(index++);
        dataList.add(data);
        return data;
    };

    // read
    @Override
    public Optional<T> findById(ID id) {

        return dataList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    };

    // write

    @Override
    public List<T> findAll() {
        return dataList.stream()
                .sorted(sortRamda) // Stream<T>
                .collect(Collectors.toList()); // List<T>
    }

    // delete
    @Override
    public void delete(ID id) {
        var deleteEntity = dataList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();

        deleteEntity.ifPresent(dataList::remove);
    }
}
