package com.rsk.security.dao;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import java.util.Collections;

@Data
public class ResponseBean {

	@JsonProperty("status")
	private int status;
	@JsonProperty("message")
	private String message;
	@JsonProperty("data")
	private Map<String, Object> responseMap;
	@JsonProperty("responseTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date responseTime;

	private ResponseBean(Builder builder) {
		this.status = builder.status;
		this.message = builder.message;
		this.responseMap = builder.responseMap;
		this.responseTime = new Date();
	}
	
	public ResponseBean() {
		responseTime = new Date();
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int status;
		private String message;
		private Map<String, Object> responseMap = Collections.emptyMap();

		private Builder() {
		}

		public Builder withStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder withResponseMap(Map<String, Object> responseMap) {
			this.responseMap = responseMap;
			return this;
		}

		public ResponseBean build() {
			return new ResponseBean(this);
		}
	}
	
}
