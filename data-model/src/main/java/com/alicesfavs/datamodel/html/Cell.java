package com.alicesfavs.datamodel.html;

/**
 * Created by kjung on 9/10/16.
 */
public class Cell
{

    public final String value;
    public final Alignment alignment;

    public Cell(String value)
    {
        this.value = value;
        this.alignment = Alignment.LEFT;
    }

    public Cell(Object value)
    {
        this(value, Alignment.LEFT);
    }

    public Cell(Object value, Alignment alignment)
    {
        this.value = value == null ? "" : value.toString();
        this.alignment = alignment;
    }

}
