package ru.itpark.util;

import java.sql.ResultSet;

@FunctionalInterface
public interface RowMapper<T> {
  T map(ResultSet resultSet);
}
