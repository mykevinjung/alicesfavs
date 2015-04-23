package com.alicesfavs.datamodel;

/**
 * Created by kjung on 4/22/15.
 */
public enum Country
{
    US(1), CANADA(2), UK(3);

    private final int code;

    private Country(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public static Country fromCode(int code)
    {
        for (Country country : Country.values())
        {
            if (country.getCode() == code)
            {
                return country;
            }

        }

        throw new IllegalArgumentException("Unknown country code " + code);
    }

}
