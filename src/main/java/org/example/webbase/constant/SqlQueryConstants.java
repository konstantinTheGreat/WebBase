package org.example.webbase.constant;

import org.intellij.lang.annotations.Language;

public class SqlQueryConstants {
    @Language("SQL")
    public static final String CHECK_USER = "SELECT username, password FROM Users WHERE username = ?";
    @Language("SQL")
    public static final String DELETE_USER = "DELETE FROM users WHERE username = ?";
    @Language("SQL")
    public static final String UPLOAD_FILE_SQL = "UPDATE users SET profile_pic = ? WHERE username = ?";
    @Language("SQL")
    public static final String GET_EMAIL_SQL = "SELECT email FROM Users WHERE username = ?";
    @Language("SQL")
    public static final String SELECT_PASSWORD = "SELECT password, salt FROM Users WHERE username = ?";
    @Language("SQL")
    public static final String ADD_USER = "INSERT INTO users (username, password, email, verification_code, salt, status) VALUES (?, ?, ?, ?, ?, ?)";
    @Language("SQL")
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = ?, salt = ? WHERE username = ?\n";
    @Language("SQL")
    public static final String GET_USER_INFO = "SELECT userid, username, password, email, verification_code, salt, profile_pic, status FROM users WHERE username = ?";

}
