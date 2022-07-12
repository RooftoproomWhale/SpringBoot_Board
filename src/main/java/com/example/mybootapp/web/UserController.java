package com.example.mybootapp.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mybootapp.dto.UsersSignupDto;
import com.example.mybootapp.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	//	생성자를 통한 의존성 주입(DI: dependancy injection)
	//	SecurityConfig 클래스의 pwdEncoder() 메서드가 리턴하는 Bean이 자동으로 주입됨.
	private final PasswordEncoder encoder;
	private final UsersService usersService;
	
	@GetMapping("/signup")
	public String signup()
	{
		log.info("signup() GET");
		
		return "user_signup";	//	view(HTML) name
	}
	
	@PostMapping("/signup")
	public String processSignup(
			@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "email") String email)
	{
		log.info("processSignup(username={}, password={}, email={})", username, password, email);

		//	요청 파라미터들을 UsersSignupDto 객체로 변환
		UsersSignupDto dto = UsersSignupDto.builder()
				.username(username)
				.password(encoder.encode(password))
				.email(email)
				.build();
		
		// 	TODO: usersService의 메서드를 사용해서 회원 가입을 진행.
		Long id = usersService.registerUser(dto);
		log.info("registered (id={}) 호출", id);
		
		//	TODO: 회원 가입 성공 후 이동할 페이지 -> 로그인 페이지로 이동.
		return "redirect:/login";	//	새로운 요청을 생성.
	}
}
