package com.alicesfavs.datamodel;

public class CategoryProduct extends Extractable
{
    public final long categoryId;
    public final long productId;
    public Integer displayOrder;

    public CategoryProduct(ModelBase modelBase, long categoryId, long productId)
    {
        super(modelBase);
        this.categoryId = categoryId;
        this.productId = productId;
    }

    public CategoryProduct(Extractable extractable, long categoryId, long productId)
    {
        super(extractable);
        this.categoryId = categoryId;
        this.productId = productId;
    }

    public CategoryProduct(Extractable extractable, long categoryId, long productId, Integer displayOrder)
    {
        super(extractable);
        this.categoryId = categoryId;
        this.productId = productId;
        this.displayOrder = displayOrder;
    }

}
