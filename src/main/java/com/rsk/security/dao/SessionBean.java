package com.rsk.security.dao;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2952078410479614610L;
	
	@JsonProperty("session_token")
	private String sessionToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
	
	private long clientId;
	private String clientName;
	private long clientType;

	private long employeeId;
	private String employeeName;
	private String employeeEmail;
	private List<String> roles;

}