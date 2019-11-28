package com.example.demo.configuration.Securty;

import java.util.Optional;

import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	
	private UserRepository userRepository;
	
	@Autowired
	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user =  userRepository.findByUsername(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("not found : " + username));

		return user.map(MyUserDetails::new).get();
		
	}
	
//	public String getDepartmentByUserName(String username) {
//		
//	
//	
//	}
		
	


	
}
