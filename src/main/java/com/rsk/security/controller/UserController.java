package com.rsk.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsk.security.constant.App;
import com.rsk.security.dao.ResponseBean;
import com.rsk.security.entities.User;
import com.rsk.security.exception.AppRuntimeException;
import com.rsk.security.exception.ApplicationException;
import com.rsk.security.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Users", description = "Users management APIs")
public class UserController {

	private final UserService userService;
	
	// http://localhost:8080/api/users
	// build create User REST API
	@Operation(summary = "This method is used to get the Create User.")
    @PostMapping
    public ResponseEntity<ResponseBean> createUser(@RequestBody User user){
    	log.info("UserController ... createUser");
        
    	Map<String, Object> ret = new HashMap<String, Object>();
 		ret.put("payload", userService.saveUser(user));
 		ResponseBean returnMap = ResponseBean.builder()
 				.withResponseMap(ret)
 				.withStatus(App.Return.SUCCESS)
 				.withMessage("success").build();
 		
 		return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
	@Operation(summary = "This method is used to get the user info.")
    @GetMapping("{id}")
    public ResponseEntity<ResponseBean> getUserById(@PathVariable("id") Integer userId){
    	log.info("UserController ... getUserById");

        Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("payload", userService.getUser(userId));
		ResponseBean returnMap = ResponseBean.builder()
				.withResponseMap(ret)
				.withStatus(App.Return.SUCCESS)
				.withMessage("success").build();
        
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @Operation(summary = "This method is used to get the Users.")
    @GetMapping
    public ResponseEntity<ResponseBean> getAllUsers() throws ApplicationException{
    	log.info("UserController ... getAllUsers");
    	try {
	        
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("list", userService.getAllUsers());
			ResponseBean returnMap = ResponseBean.builder()
					.withResponseMap(ret)
					.withStatus(App.Return.SUCCESS)
					.withMessage("success").build();
	        
	        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new AppRuntimeException(e.getMessage());
    	}
    }

    // Build Update User REST API
    // http://localhost:8080/api/users/1
    @Operation(summary = "This method is used to update the user info.")
    @PutMapping("{id}")
    public ResponseEntity<ResponseBean> updateUser(@PathVariable("id") Integer userId, @RequestBody User user){
    	log.info("UserController ... updateUser");
    	
        Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("payload", userService.updateUser(userId, user));
		ResponseBean returnMap = ResponseBean.builder()
				.withResponseMap(ret)
				.withStatus(App.Return.SUCCESS)
				.withMessage("success").build();
        
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    // Build Delete User REST API
    // http://localhost:8080/api/users/1
    @Operation(summary = "This method is used to delete the user data.")
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBean> deleteUser(@PathVariable("id") Integer userId){
    	log.info("UserController ... deleteUser");

        Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("payload", userService.deleteUser(userId));
		ResponseBean returnMap = ResponseBean.builder()
				.withResponseMap(ret)
				.withStatus(App.Return.SUCCESS)
				.withMessage("User successfully deleted!").build();
        
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }
}
