package ru.itpark.util;

import ru.itpark.exception.DataStoreException;
import ru.itpark.exception.NoGeneratedKeysException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplate {
  private <T> T executeInternal(String url, String sql, PreparedStatementExecutor<T> executor) {
    try (
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement statement = connection.prepareStatement(sql);
    ) {
      return executor.execute(statement);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataStoreException(e);
    }
  }

  public <T> List<T> executeQuery(
      String url,
      String sql,
      PreparedStatementSetter preparedStatementSetter,
      RowMapper<T> mapper
  ) {
    return executeInternal(url, sql, stmt -> {
      try (ResultSet resultSet = preparedStatementSetter
          .setValues(stmt)
          .executeQuery();) {
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
          result.add(mapper.map(resultSet));
        }
        return result;
      }
    });


  }

  public <T> Optional<T> executeQueryForObject(
      String url,
      String sql,
      PreparedStatementSetter preparedStatementSetter,
      RowMapper<T> mapper
  ) {
    return executeInternal(url, sql, stmt -> {
      try (ResultSet resultSet = preparedStatementSetter
          .setValues(stmt)
          .executeQuery();) {
        if (resultSet.next()) {
          return Optional.of(mapper.map(resultSet));
        }
        return Optional.empty();
      }
    });
  }

  public int executeUpdate(
      String url,
      String sql,
      PreparedStatementSetter preparedStatementSetter
  ) {
    return executeInternal(url, sql, stmt -> preparedStatementSetter
        .setValues(stmt)
        .executeUpdate());
  }

  // FIXME: multiple keys
  public int executeUpdateWithGeneratedKey(
      String url,
      String sql,
      PreparedStatementSetter preparedStatementSetter
  ) {
    try (
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    ) {

      preparedStatementSetter
          .setValues(statement)
          .executeUpdate();

      try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
        if (generatedKeys.next()) {
          return generatedKeys.getInt(1);
        }

        throw new NoGeneratedKeysException();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataStoreException(e);
    }
  }
}
