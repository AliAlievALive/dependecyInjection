package ru.itpark.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
  T create(T object);
  T updateById(T object);
  boolean removeById(ID id);
  List<T> getAll();
  Optional<T> getById(ID id);
}
