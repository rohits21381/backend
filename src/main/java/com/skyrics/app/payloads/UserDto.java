package com.skyrics.app.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private Integer userId;
    
    @NotEmpty(message = "not empty name")
	private String fname;
    
    @NotEmpty(message = "not empty name")
	private String lname;
	
    @NotEmpty(message = "not empty address")
	private String address;
	
    @NotEmpty(message = "not empty city")
	private String city;
	
    @NotEmpty(message = "not empty state")
	private String state;
	
    @NotEmpty(message = "not empty about")
	private String zipcode;
	
    @NotEmpty(message = "not empty number")
	private String number;
	
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "not valid email formate")
	private String email;
	
	@NotEmpty
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})",message = "not valid pasword formate")
	private String pass;
	
	@NotEmpty
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})",message = "not valid pasword formate")
	private String cpass;
	
	private Set<RoleDto> roles = new HashSet<>();

}
