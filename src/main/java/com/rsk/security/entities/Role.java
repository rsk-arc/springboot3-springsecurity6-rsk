package com.rsk.security.entities;

public enum Role {
    USER("USER"), ADMIN("ADMIN");
	
    private String role;
    
    Role(String role){
    	this.role = role;
    }
    
    public String getRole() {
    	return this.role;
    }
    
    public static Role findByRole(String roleName){
        for(Role role : values()){
            if( role.name().equals(roleName)){
                return role;
            }
        }
        return null;
    }
}
