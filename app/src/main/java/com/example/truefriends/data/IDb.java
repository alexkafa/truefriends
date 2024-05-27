package com.example.truefriends.data;

import java.util.List;

public interface IDb<T> {

     void add(T t);
     T get(int id);
     void delete(int id);
     List<T> getAll();
     void update(T t);
}
