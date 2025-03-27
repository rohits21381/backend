package com.skyrics.app;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.skyrics.app.dao.RoleRepository;
import com.skyrics.app.entities.Role;

@SpringBootApplication
@EnableTransactionManagement
public class SkyricsApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SkyricsApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRoleId(501);
		role.setRole("ROLE_ADMIN");
		
		Role role2 = new Role();
		role2.setRoleId(502);
		role2.setRole("ROLE_USER");
		
		
		ArrayList<Role> roleList = new ArrayList<>();
		roleList.add(role);
		roleList.add(role2);
		
		List<Role> result = this.roleRepository.saveAll(roleList);
		result.forEach(r-> System.out.println(r.getRole()));
	}
}
