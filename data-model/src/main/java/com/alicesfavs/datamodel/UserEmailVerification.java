package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 4/26/15.
 */
public class UserEmailVerification extends ModelBase
{
    public long userId;
    public String emailAddressHash;
    public String verificationCode;
    public boolean verified;
    public LocalDateTime expirationDate;

    public UserEmailVerification(long id, LocalDateTime createdDate, long userId)
    {
        super(id, createdDate);
        this.userId = userId;
    }

    public UserEmailVerification(ModelBase modelBase, long userId)
    {
        super(modelBase);
        this.userId = userId;
    }

}
