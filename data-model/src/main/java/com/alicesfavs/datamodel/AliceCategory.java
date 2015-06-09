package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 4/25/15.
 */
public class AliceCategory extends ModelBase
{
    public String name;
    public boolean display;

    public AliceCategory(long id, LocalDateTime createdDate)
    {
        super(id, createdDate);
    }

    public AliceCategory(ModelBase modelBase)
    {
        super(modelBase);
    }

}
