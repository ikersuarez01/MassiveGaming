package com.IkerLucia.MassiveGaming.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.IkerLucia.MassiveGaming.model.Usuario;
import com.IkerLucia.MassiveGaming.repository.UsuarioRepository;

@Service
public class RepositoryUserDetailsService implements UserDetailsService {
 
 @Autowired
 private UsuarioRepository userRepository;
 
@Override
 public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
//	 Usuario user = userRepository.findByCorreo1(correo).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	List<Usuario> user = userRepository.findByCorreo(correo);
	List<GrantedAuthority> roles = new ArrayList<>();
	roles.add(new SimpleGrantedAuthority("ROLE_USER"));
	if(user.isEmpty()) 
		new UsernameNotFoundException("User not found");
		
	return new org.springframework.security.core.userdetails.User(user.get(0).getCorreo(), user.get(0).getPassword(), roles);

 }
}