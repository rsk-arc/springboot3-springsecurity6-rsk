package com.rsk.security.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5219709698814307503L;

	
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "languages_seq")
    @Id
    @Column
    private Integer id;
    
    @Column
    private String locale;
    
    @Column(name = "messagekey")
    private String key;
    
    @Column(name = "messagecontent")
    private String content;
    
	public Language() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
    
  
}