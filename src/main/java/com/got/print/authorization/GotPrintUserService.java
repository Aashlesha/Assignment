package com.got.print.authorization;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.got.print.home.NoteUserHome;
import com.got.print.persistance.Note_User;

@Service
public class GotPrintUserService implements UserDetailsService{
	
	@Autowired
	private NoteUserHome noteUserHome;

	
	@Override
	public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException {
		
		Note_User activeUserInfo = noteUserHome.getCurrentNoteUser(userName);
		
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		
		UserDetails userDetails = (UserDetails)new User(String.valueOf(activeUserInfo.getId()),activeUserInfo.getPassword(),Arrays.asList(authority));
		
		return userDetails;
	}
}
