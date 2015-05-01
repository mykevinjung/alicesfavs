package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 4/25/15.
 */
public class AliceCategory extends ModelBase
{
    public final String name;

    public AliceCategory(long id, LocalDateTime createdDate, String name)
    {
        super(id, createdDate);
        this.name = name;
    }

    public AliceCategory(ModelBase modelBase, String name)
    {
        super(modelBase);
        this.name = name;
    }

}
