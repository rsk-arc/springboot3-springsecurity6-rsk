package com.rsk.security.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6746221581561359793L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "token_seq")
	public Integer id;

	@Column(unique = true)
	public String token;

	@Enumerated(EnumType.STRING)
	public TokenType tokenType = TokenType.BEARER;

	public boolean revoked;

	public boolean expired;
	
	@ManyToOne() // fetch = FetchType.LAZY
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(foreignKeyDefinition =
        "FOREIGN KEY(user_id) REFERENCES employee(id) ON DELETE CASCADE",
        value = ConstraintMode.CONSTRAINT))
    public User user;
    
}
