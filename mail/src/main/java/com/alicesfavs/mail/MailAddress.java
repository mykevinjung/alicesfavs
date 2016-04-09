package com.alicesfavs.mail;

/**
 * Created by kjung on 3/26/16.
 */
public class MailAddress
{

    private final String address;
    private final String personal;

    public MailAddress(String address, String personal)
    {
        this.address = address;
        this.personal = personal;
    }

    public MailAddress(String address)
    {
        this(address, null);
    }

    public String getAddress()
    {
        return address;
    }

    public String getPersonal()
    {
        return personal;
    }

}
