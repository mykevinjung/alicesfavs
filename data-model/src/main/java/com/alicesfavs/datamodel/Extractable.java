package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

public class Extractable extends ModelBase
{

    public ExtractStatus extractStatus;

    /**
     * The job id by which this model was extracted last
     */
    public Long extractJobId;

    /**
     * The date when this model was extracted last
     */
    public LocalDateTime extractedDate;

    public Extractable(long id, LocalDateTime createdDate)
    {
        super(id, createdDate);
    }

    public Extractable(long id, LocalDateTime createdDate, LocalDateTime updatedDate)
    {
        super(id, createdDate, updatedDate);
    }

    public Extractable(long id, LocalDateTime createdDate, LocalDateTime updatedDate, ExtractStatus extractStatus,
            Long extractJobId, LocalDateTime extractedDate)
    {
        super(id, createdDate, updatedDate);
        this.extractStatus = extractStatus;
        this.extractJobId = extractJobId;
        this.extractedDate = extractedDate;
    }

    public Extractable(ModelBase modelBase)
    {
        super(modelBase);
    }

    public Extractable(ModelBase modelBase, ExtractStatus extractStatus, Long extractJobId, LocalDateTime extractedDate)
    {
        super(modelBase);
        this.extractStatus = extractStatus;
        this.extractJobId = extractJobId;
        this.extractedDate = extractedDate;
    }

    public Extractable(Extractable other)
    {
        super(other);
        this.extractStatus = other.extractStatus;
        this.extractJobId = other.extractJobId;
        this.extractedDate = other.extractedDate;
    }

    public String toString()
    {
        return super.toString() + ", extractStatus=" + extractStatus + ", extractJobId=" + extractJobId
                + ", extractedDate=" + extractedDate;
    }

}
