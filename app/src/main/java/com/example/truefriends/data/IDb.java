package com.example.truefriends.data;

import java.util.List;

/**
 * A generic interface for basic database operations.
 * This interface defines the standard CRUD operations (Create, Read, Update, Delete)
 * and also allows searching by a specific field.
 *
 * @param <T> The type of object that this interface will manage.
 */
public interface IDb<T> {

     /**
      * Adds a new object to the database.
      *
      * @param t The object to be added.
      */
     void add(T t);

     /**
      * Retrieves an object from the database based on its unique identifier.
      *
      * @param id The unique identifier of the object to retrieve.
      * @return The object corresponding to the specified ID, or null if not found.
      */
     T get(int id);

     /**
      * Deletes an object from the database based on its unique identifier.
      *
      * @param id The unique identifier of the object to delete.
      */
     void delete(int id);

     /**
      * Retrieves all objects of type T from the database.
      *
      * @return A list of all objects in the database.
      */
     List<T> getAll();

     /**
      * Updates an existing object in the database.
      *
      * @param t The object with updated values to be stored.
      */
     void update(T t);

     /**
      * Retrieves a list of objects from the database that match a specific field value.
      *
      * @param fieldName The name of the field to filter by.
      * @param value The value that the specified field should match.
      * @return A list of objects that match the specified field value.
      */
     List<T> getByField(String fieldName, String value);
}
