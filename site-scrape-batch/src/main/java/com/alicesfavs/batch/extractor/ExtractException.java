package com.alicesfavs.batch.extractor;

public class ExtractException extends Exception
{

    private static final long serialVersionUID = 1L;

    public ExtractException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ExtractException(String message)
    {
        super(message);
    }

    public ExtractException(Throwable cause)
    {
        super(cause);
    }

}
