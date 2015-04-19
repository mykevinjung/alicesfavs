package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

public class Category extends Extractable
{

    public final long siteId;
    public final CategoryExtract categoryExtract1;
    public final CategoryExtract categoryExtract2;
    public final CategoryExtract categoryExtract3;

    /**
     * Display order on the site. Left-top is the first, and right-bottom is the last.<br>
     * Lower number first, higher number later
     */
    public Integer displayOrder;

    public Category(long id, LocalDateTime createdDate, long siteId, CategoryExtract categoryExtract1,
            CategoryExtract categoryExtract2, CategoryExtract categoryExtract3)
    {
        super(id, createdDate);
        Assert.notNull(categoryExtract1);
        this.siteId = siteId;
        this.categoryExtract1 = categoryExtract1;
        this.categoryExtract2 = categoryExtract2;
        this.categoryExtract3 = categoryExtract3;
    }

    public Category(long id, LocalDateTime createdDate, long siteId, CategoryExtract categoryExtract1,
            CategoryExtract categoryExtract2)
    {
        this(id, createdDate, siteId, categoryExtract1, categoryExtract2, null);
    }

    public Category(long id, LocalDateTime createdDate, long siteId, CategoryExtract categoryExtract1)
    {
        this(id, createdDate, siteId, categoryExtract1, null, null);
    }

    public Category(ModelBase modelBase, long siteId, CategoryExtract categoryExtract1,
            CategoryExtract categoryExtract2, CategoryExtract categoryExtract3)
    {
        super(modelBase);
        this.siteId = siteId;
        this.categoryExtract1 = categoryExtract1;
        this.categoryExtract2 = categoryExtract2;
        this.categoryExtract3 = categoryExtract3;
    }

    public Category(ModelBase modelBase, long siteId, CategoryExtract categoryExtract1, CategoryExtract categoryExtract2)
    {
        this(modelBase, siteId, categoryExtract1, categoryExtract2, null);
    }

    public Category(ModelBase modelBase, long siteId, CategoryExtract categoryExtract1)
    {
        this(modelBase, siteId, categoryExtract1, null, null);
    }

    public Category(Extractable extractable, long siteId, CategoryExtract categoryExtract1,
            CategoryExtract categoryExtract2, CategoryExtract categoryExtract3)
    {
        super(extractable);
        this.siteId = siteId;
        this.categoryExtract1 = categoryExtract1;
        this.categoryExtract2 = categoryExtract2;
        this.categoryExtract3 = categoryExtract3;
    }

    public Category(Extractable extractable, long siteId, CategoryExtract categoryExtract1,
            CategoryExtract categoryExtract2)
    {
        this(extractable, siteId, categoryExtract1, categoryExtract2, null);
    }

    public Category(Extractable extractable, long siteId, CategoryExtract categoryExtract1)
    {
        this(extractable, siteId, categoryExtract1, null, null);
    }
    
    public CategoryExtract getLeafExtract()
    {
        if (categoryExtract3 != null)
        {
            return categoryExtract3;
        }
        if (categoryExtract2 != null)
        {
            return categoryExtract2;
        }
        return categoryExtract1;
    }

    public String toString()
    {
        return "Category[" + super.toString() + ", siteId=" + siteId + ", categoryExtract1=" + categoryExtract1
                + ", categoryExtract2=" + categoryExtract2 + ", categoryExtract3=" + categoryExtract3
                + ", displayOrder=" + displayOrder + "]";
    }

}
