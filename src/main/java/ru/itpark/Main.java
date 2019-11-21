package ru.itpark;

import ru.itpark.annotation.Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Main {
  public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
    // Class ->
    final Class<?> cls = Class.forName("ru.itpark.reflection.NoDeps");
    System.out.println(cls.isAnnotationPresent(Controller.class));
    final Constructor<?>[] constructors = cls.getConstructors();
    for (Constructor<?> constructor : constructors) {
      System.out.println(constructor.getName());
      final Object o = constructor.newInstance();

      final Field[] fields = cls.getDeclaredFields();
      for (Field field : fields) {
        System.out.println(field.getName());
        field.setAccessible(true);
        System.out.println(Modifier.isPrivate(field.getModifiers()));
        field.set(o, "reflection");
        System.out.println(field.get(o));
      }

      System.out.println(o);
    }

  }
}
