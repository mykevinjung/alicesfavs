package com.alicesfavs.datamodel.html;

/**
 * Created by kjung on 9/10/16.
 */
public enum Alignment
{

    LEFT("left"), CENTER("center"), RIGHT("right");

    private final String value;

    private Alignment(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return value;
    }

}
