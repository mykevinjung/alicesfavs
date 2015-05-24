package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 4/26/15.
 */
public class UserFav extends ModelBase
{
    public long userId;
    public Product product;
    public String addOnName;
    public Double addOnPrice;

    public UserFav(long id, LocalDateTime createdDate)
    {
        super(id, createdDate);
    }

    public UserFav(ModelBase modelBase)
    {
        super(modelBase);
    }

}
