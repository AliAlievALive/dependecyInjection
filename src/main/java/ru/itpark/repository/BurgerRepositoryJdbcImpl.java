package ru.itpark.repository;

import lombok.RequiredArgsConstructor;
import ru.itpark.model.Burger;
import ru.itpark.util.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BurgerRepositoryJdbcImpl implements CrudRepository<Burger, Integer> {
  private final String url;
  private final JdbcTemplate jdbcTemplate;

  @Override
  public Burger create(Burger burger) {
    String sql = "INSERT INTO burgers (name, price) VALUES (?, ?);";
    int id = jdbcTemplate.executeUpdate(url, sql, stmt -> {
      stmt.setString(1, burger.getName());
      stmt.setInt(2, burger.getPrice());
      return stmt;
    });
    burger.setId(id);
    return burger;
  }

  @Override
  public Burger updateById(Burger burger) {
    String sql = "UPDATE burgers SET name = ?, price = ? WHERE id = ?;";
    jdbcTemplate.executeUpdate(url, sql, stmt -> {
      stmt.setString(1, burger.getName());
      stmt.setInt(2, burger.getPrice());
      stmt.setInt(3, burger.getId());
      return stmt;
    });
    return burger;
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
