package com.example.mybootapp.dto;

import com.example.mybootapp.domain.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UsersSignupDto {
	
	private String username;
	private String password;
	private String email;
	
	@Builder
	private UsersSignupDto(String username, String password, String email) 
	{
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	//	UsersService에서 UsersRepository를 이용하기 위해서 Entity로 변환할 수 있는 메서드
	public Users toEntity() 
	{
		return Users.builder()
				.username(this.username)
				.password(this.password)
				.email(this.email)
				.build();
	}
}

