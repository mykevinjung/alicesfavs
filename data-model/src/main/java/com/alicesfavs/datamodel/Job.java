package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

public class Job extends ModelBase
{
    public final long siteId;
    public final Mode mode;
    public Status status;
    public int stepNumber;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public Integer foundCategoryNo;
    public Integer foundProductNo;

    public Job(long id, LocalDateTime createdDate, long siteId, Mode mode)
    {
        super(id, createdDate);
        this.siteId = siteId;
        this.mode = mode;
    }

    public Job(ModelBase modelBase, long siteId, Mode mode)
    {
        super(modelBase);
        this.siteId = siteId;
        this.mode = mode;
    }

    public static enum Mode
    {
        FULL_EXTRACT(1), CATEGORY_EXTRACT(2), PRODUCT_EXTRACT(3);

        private final int code;

        private Mode(int code)
        {
            this.code = code;
        }

        public int getCode()
        {
            return code;
        }

        public static Mode fromCode(int code)
        {
            for (Mode mode : Mode.values())
            {
                if (mode.getCode() == code)
                {
                    return mode;
                }
            }

            throw new IllegalArgumentException("Unknown job mode code " + code);
        }
    }

    public static enum Status
    {
        CREATED(1), PROCESSING(2), COMPLETED(3), ERRORED(4);

        private final int code;

        private Status(int code)
        {
            this.code = code;
        }

        public int getCode()
        {
            return code;
        }

        public static Status fromCode(int code)
        {
            for (Status status : Status.values())
            {
                if (status.getCode() == code)
                {
                    return status;
                }
            }

            throw new IllegalArgumentException("Unknown job status code " + code);
        }
    }

}