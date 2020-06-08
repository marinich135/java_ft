package ru.stqa.pft.mantis.model;

import java.util.Objects;

public class UserData {

  private int id;
  private String username;
  private String email;
  private String password;
  private int accessLevel;

  public int getId() {
    return id;
  }

  public UserData setId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserData setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserData setPassword(String password) {
    this.password = password;
    return this;
  }

  public int getAccessLevel() {
    return accessLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            accessLevel == userData.accessLevel &&
            Objects.equals(username, userData.username) &&
            Objects.equals(email, userData.email) &&
            Objects.equals(password, userData.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, accessLevel);
  }
}
