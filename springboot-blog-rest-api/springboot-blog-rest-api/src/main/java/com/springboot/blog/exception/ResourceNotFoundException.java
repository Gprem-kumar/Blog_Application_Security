package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
  private String resourceName;
  private String fieldName;
  private long fieldValue;

    public ResourceNotFoundException(String fieldName,String resourceName,long fieldValue)
    {
        super(String.format("%s not found with %s",resourceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
    public String getFieldName()
    {
        return fieldName;
    }
    public String getResourceName()
    {
        return resourceName;
    }
    public long getFieldValue()
    {
        return fieldValue;
    }
}
