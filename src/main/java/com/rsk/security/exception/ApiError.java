package com.rsk.security.exception;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.rsk.security.dao.ResponseBean;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "message", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
class ApiError extends ResponseBean {

    private String errorCode;

    public ApiError() {
		super();
	}
    
	public ApiError(String message) {
		super();
		this.setMessage(message);
	}
	
	public ApiError(Throwable error) {
		super();
		this.errorCode = error.getLocalizedMessage();
	}
	
	public ApiError(Throwable error, String errorCode) {
		super();
		this.errorCode = errorCode;
		this.setMessage(error.getLocalizedMessage());
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}

class LowerCaseClassNameResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}