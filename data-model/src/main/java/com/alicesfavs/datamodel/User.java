package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 4/26/15.
 */
public class User extends ModelBase
{
    public String emailAddress;
    public String password;
    public String name;
    public Integer favLimit;
    public Status status;
    public Country registrationCountry;

    public User(long id, LocalDateTime createdDate)
    {
        super(id, createdDate);
    }

    public User(ModelBase modelBase)
    {
        super(modelBase);
    }

    public static enum Status
    {
        REGISTERED(1), ACTIVATED(2), DELETED(3), INVALID_EMAIL(4);

        private final int code;

        private Status(int code)
        {
            this.code = code;
        }

        public int getCode()
        {
            return code;
        }

        public static Status fromCode(int code)
        {
            for (Status status : Status.values())
            {
                if (status.getCode() == code)
                {
                    return status;
                }
            }

            throw new IllegalArgumentException("Unknown user status code " + code);
        }
    }

}
