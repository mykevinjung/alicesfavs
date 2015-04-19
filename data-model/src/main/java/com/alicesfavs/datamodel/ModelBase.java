package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

public class ModelBase
{

    public final long id;
    public final LocalDateTime createdDate;
    public LocalDateTime updatedDate;

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
        this.id = other.id;
        this.createdDate = other.createdDate;
        this.updatedDate = other.updatedDate;
    }

    public String toString()
    {
        return "id=" + id + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate;
    }

}
