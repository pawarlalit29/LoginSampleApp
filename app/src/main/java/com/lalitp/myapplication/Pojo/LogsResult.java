package com.lalitp.myapplication.Pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by atulsia on 7/11/16.
 */
@DatabaseTable(tableName = "notification_details")
public class LogsResult {

    public static final String COL_NOTIFICATION_ID = "_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_MSG = "message";
    public static final String COL_TIMESTAMP = "timestamp";

    @DatabaseField(generatedId = true, columnName = COL_NOTIFICATION_ID)
    public long notificationId;
    @DatabaseField(columnName = COL_USERNAME)
    public String username;
    @DatabaseField(columnName = COL_PASSWORD)
    public String password;
    @DatabaseField(columnName = COL_MSG)
    public String message;
    @DatabaseField(columnName = COL_TIMESTAMP)
    public long timestamp;

    public LogsResult() {
    }

    public LogsResult(String username, String type, String message) {
        this.username = username;
        this.password = type;
        this.message = message;
    }

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
