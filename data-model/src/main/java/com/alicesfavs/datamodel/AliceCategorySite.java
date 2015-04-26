package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

public class AliceCategorySite extends ModelBase
{
    public final long aliceCategoryId;
    public final long siteId;

    public AliceCategorySite(long id, LocalDateTime createdDate, long aliceCategoryId, long siteId)
    {
        super(id, createdDate);
        this.aliceCategoryId = aliceCategoryId;
        this.siteId = siteId;
    }

    public AliceCategorySite(ModelBase modelBase, long aliceCategoryId, long siteId)
    {
        super(modelBase);
        this.aliceCategoryId = aliceCategoryId;
        this.siteId = siteId;
    }

}
