package org.example.webbase.entity;

import java.util.Objects;

public class User extends AbstractEntity{
    private String username;
    private String password;
    private String email;
    private Integer verification_code;
    private String salt;

    public User(){}

    public User(String username, String password, String email, Integer verification_code, String salt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.verification_code = verification_code;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getVerification_code() {
        return verification_code;
    }

    public String getSalt() {
        return salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVerification_code(Integer verification_code) {
        this.verification_code = verification_code;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(verification_code, user.verification_code) && Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, verification_code, salt);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", verification_code=" + verification_code +
                ", salt='" + salt + '\'' +
                '}';
    }
}
