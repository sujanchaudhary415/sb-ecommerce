package com.ecommerce.sb_ecom.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    String field;
    Long fieldId;

    public ResourceNotFoundException()
    {

    }

    public ResourceNotFoundException(String resourceName,String field,String fieldName)
    {
        super(String.format("%s not found with %s: %s",resourceName,field,fieldName));
        this.resourceName=resourceName;
        this.field=field;
        this.fieldName=fieldName;

    }

    public ResourceNotFoundException(String resourceName,String field,long fieldId)
    {
        super(String.format("%s not found with %s: %d",resourceName,field,fieldId));
        this.resourceName=resourceName;
        this.field=field;
        this.fieldId=fieldId;

    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
}
