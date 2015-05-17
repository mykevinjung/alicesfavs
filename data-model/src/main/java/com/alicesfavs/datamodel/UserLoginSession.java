package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 5/16/15.
 */
public class UserLoginSession extends ModelBase
{
    public long userId;
    public String guid;
    public String token;
    public String ipAddress;
    public String userAgent;
    public LocalDateTime lastLoginDate;
    public LocalDateTime expirationDate;

    public UserLoginSession(long id, LocalDateTime createdDate)
    {
        super(id, createdDate);
    }

}
