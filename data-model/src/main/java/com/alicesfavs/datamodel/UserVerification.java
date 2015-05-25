package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 4/26/15.
 */
public class UserVerification extends ModelBase
{
    public long userId;
    public String emailAddressHash;
    public String verificationCode;
    public boolean verified;
    public LocalDateTime expirationDate;

    public UserVerification(long id, LocalDateTime createdDate, long userId)
    {
        super(id, createdDate);
        this.userId = userId;
    }

    public UserVerification(ModelBase modelBase)
    {
        super(modelBase);
    }

}
