package com.abdelrahman.blogplatorm.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.repositories.UserRepo;
import com.abdelrahman.blogplatorm.security.dtos.MyUserPricipale;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not Found"));
		return new MyUserPricipale(user);
	}

}
