package com.alicesfavs.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kjung on 11/2/15.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

}
