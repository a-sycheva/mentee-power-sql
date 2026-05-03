package ru.mentee.power.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Простой класс для проверки подключения к PostgreSQL.
 * Демонстрирует базовое использование JDBC без дополнительных библиотек.
 */
public class SimpleConnection {

  // Пока хардкодим настройки (в следующем уроке исправим!)
  private static final String URL = "jdbc:postgresql://localhost:5432/mentee_db";
  private static final String USERNAME = "mentee";
  private static final String PASSWORD = "password123";

  public static void main(String[] args) {
    System.out.println("Testing connection to PostgreSQL...");
    System.out.println("Connecting to: " + URL);
    System.out.println("Тестовый вывод на русском");

    try {
      // Подключаемся к БД
      Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      System.out.println("Connection established!");

      // Выполняем простой запрос
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as user_count FROM users");

      if (resultSet.next()) {
        int userCount = resultSet.getInt("user_count");
        System.out.println("Users in database: " + userCount);
      }

      // Получаем версию PostgreSQL
      ResultSet versionResult = statement.executeQuery("SELECT version()");
      if (versionResult.next()) {
        String version = versionResult.getString(1);
        String shortVersion = version.split(" ")[1]; // Только номер версии
        System.out.println("Version of PostgreSQL: " + shortVersion);
      }

      // Закрываем соединения
      resultSet.close();
      versionResult.close();
      statement.close();
      connection.close();

      System.out.println("All works! Ready to learning SQL.");

    } catch (Exception e) {
      System.err.println("Connection error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}