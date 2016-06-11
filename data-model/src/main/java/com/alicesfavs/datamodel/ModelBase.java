package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

public class ModelBase
{

    public long id;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;

    public ModelBase()
    {
        // default constructor
    }

    public ModelBase(long id, LocalDateTime createdDate)
    {
        this(id, createdDate, null);
    }

    public ModelBase(long id, LocalDateTime createdDate, LocalDateTime updatedDate)
    {
        Assert.notNull(createdDate);
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public ModelBase(ModelBase other)
    {
        setModelBase(other);
    }

    public void setModelBase(ModelBase other)
    {
        this.id = other.id;
        this.createdDate = other.createdDate;
        this.updatedDate = other.updatedDate;
    }

    public String toString()
    {
        return "id=" + id + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ModelBase modelBase = (ModelBase) o;

        return id == modelBase.id;

    }

    @Override
    public int hashCode()
    {
        return (int) (id ^ (id >>> 32));
    }

}
