package ru.itpark.service;

import lombok.RequiredArgsConstructor;
import ru.itpark.model.Burger;
import ru.itpark.repository.CrudRepository;

@RequiredArgsConstructor
public class BurgerService {
  private final CrudRepository<Burger, Integer> repository;
}
