package com.alicesfavs.datamodel;

public enum BrandLevel
{
    LUXURY(1), PREMIUM(2), GENERAL(3);

    private final int code;

    private BrandLevel(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public static BrandLevel fromCode(int code)
    {
        for (BrandLevel brandLevel : BrandLevel.values())
        {
            if (brandLevel.getCode() == code)
            {
                return brandLevel;
            }
        }

        throw new IllegalArgumentException("Unknown brand level code " + code);
    }
}
