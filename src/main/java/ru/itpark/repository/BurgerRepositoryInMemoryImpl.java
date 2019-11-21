package ru.itpark.repository;

import lombok.RequiredArgsConstructor;
import ru.itpark.model.Burger;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BurgerRepositoryInMemoryImpl implements CrudRepository<Burger, Integer> {

  @Override
  public Burger create(Burger burger) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Burger updateById(Burger burger) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeById(Integer id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Burger> getAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Burger> getById(Integer id) {
    throw new UnsupportedOperationException();
  }
}
