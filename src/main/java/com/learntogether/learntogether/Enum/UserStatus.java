package com.learntogether.learntogether.Enum;

public enum UserStatus {
    ACTIVE, // user is given this status as soon as it is created
    INACTIVE, // if user doesn't post anything for a month then it will be banned for a week
    BLOCKED // if 3 posts of user gets banned then user will be banned permanently
}
