package com.alicesfavs.datamodel;

public enum ExtractStatus
{
    EXTRACTED(1), NOT_FOUND(2), IN_PROCESS(3);

    private final int code;

    private ExtractStatus(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public static ExtractStatus fromCode(int code)
    {
        for (ExtractStatus extractStatus : ExtractStatus.values())
        {
            if (extractStatus.getCode() == code)
            {
                return extractStatus;
            }
        }

        throw new IllegalArgumentException("Unknown extract status code " + code);
    }
}
