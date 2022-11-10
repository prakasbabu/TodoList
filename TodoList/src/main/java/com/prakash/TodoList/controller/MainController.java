package com.prakash.TodoList.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.TodoList.Dto.TodoListDto;
import com.prakash.TodoList.entity.LoginRequest;
import com.prakash.TodoList.entity.User;
import com.prakash.TodoList.service.TodoListService;
import com.prakash.TodoList.utils.JwtTokenUtil;

	@RestController
	@RequestMapping("/api")
	public class MainController {

	 @Autowired
	  private JwtTokenUtil jwtTokenUtil;
		
		@Autowired
		private TodoListService todoListService;

		@PostMapping("/newTask")
		public ResponseEntity<TodoListDto> createList(@RequestBody TodoListDto todoListDto)   {
			TodoListDto createdList = this.todoListService.create(todoListDto);
			return new ResponseEntity<>(createdList, HttpStatus.CREATED);
		}
		
		@GetMapping("/lists")
		public ResponseEntity<List<TodoListDto>> getAllList(){
			//System.out.println(jwtTokenUtil.getUsernameFromToken());
        return ResponseEntity.ok(this.todoListService.findAll());
		}

		
		@PostMapping(value = "/customer-login")
	    public ResponseEntity doCustomerLogin(@RequestBody LoginRequest loginRequest) {
	        User user = new User();
	        user.setPassword(loginRequest.getPassword());
	        user.setEmailId(loginRequest.getEmailId());
	        
	      	org.springframework.security.core.userdetails.User userDetails
	                = new org.springframework.security.core.userdetails.User(loginRequest.getEmailId(), loginRequest.getPassword(), new ArrayList<>());

	        user.setAccessToken(jwtTokenUtil.generateToken(userDetails, "END_USER"));
	        System.out.println("customer is logged in now ");
	        return new ResponseEntity(user, HttpStatus.OK);
	    }
		
		 @PostMapping(value = "/admin-login")
		    public ResponseEntity doAdminLogin(@RequestBody LoginRequest loginRequest) {
		        User user = new User();
		        user.setPassword(loginRequest.getPassword());
		        user.setEmailId(loginRequest.getEmailId());

		        org.springframework.security.core.userdetails.User userDetails
		                = new org.springframework.security.core.userdetails.User(loginRequest.getEmailId(), loginRequest.getPassword(), new ArrayList<>());

		        user.setAccessToken(jwtTokenUtil.generateToken(userDetails, "ADMIN"));
		        System.out.println("admin is logged in now ");
		        return new ResponseEntity(user, HttpStatus.OK);
		    }
		 
		 @GetMapping(value = " /test/validate/token")
		 public ResponseEntity validateToken() {
			 
			 return new ResponseEntity("test pass",HttpStatus.OK);
		 }
		 


}
