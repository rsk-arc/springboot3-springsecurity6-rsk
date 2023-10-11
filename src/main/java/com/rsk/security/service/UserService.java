package com.rsk.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rsk.security.entities.User;
import com.rsk.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	
    private final UserRepository userRepository;
	
//	private final PasswordEncoder passwordEncoder;
//  private final GenericRepository<User> genericRepository;
	
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
    
    public List<User> getAllUsers(){
    	log.info("UserService ... getAllUsers");
//    	List<SearchCriteria> searchCriteriaList = new ArrayList<>();
//    	searchCriteriaList.add(new SearchCriteria("firstName", "rsk", SearchOperation.EQUAL));
//    	genericRepository.findBySearch(new User(), searchCriteriaList, genericRepository.getCurrentSession());
//    	return genericRepository.findAll(new User());    	
    	return userRepository.findAll();
    }
    
    public User saveUser(User user){
    	log.info("UserService ... getUser");

    	return userRepository.save(user);
    }
    
    public User getUser(Integer id){
    	log.info("UserService ... getUser");
    	return userRepository.findById(id)
    			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
    
    /**
     * 
     * @param userId
     * @param user
     * @return
     */
    public User updateUser(Integer userId, User user) {
    	log.info("UserService ... updateUser");
    	User dbUser = getUser(userId);
    	boolean temp = false;
    	if(dbUser != null) {
	    	if(user.getFirstName() != null && user.getFirstName().trim().length() > 0) {
	    		dbUser.setFirstName(user.getFirstName());
	    		temp = true;
	    	}
	    	if(user.getLastName() != null && user.getLastName().trim().length() > 0) {
	    		dbUser.setLastName(user.getLastName());
	    		temp = true;
	    	}
	    	if(user.getEmail() != null && user.getEmail().trim().length() > 0) {
	    		dbUser.setEmail(user.getEmail());
	    		temp = true;
	    	}
    	}
    	//Optional.ofNullable(user.getFirstName()).orElse(dbUser.getFirstName());
    	if(temp) {
    		log.info("UserService ... updateUser database call");
    		userRepository.save(dbUser);
    	}
    	return dbUser;
    }
    
    public boolean deleteUser(Integer id){
    	log.info("UserService ... deleteUser");
    	if (userRepository.existsById(id)) {
    		log.info(id.intValue()+ " user deleted succfully");
    		userRepository.deleteById(id);
    		return true;
    	}
    	throw new UsernameNotFoundException(id +" this id user not found, not able to delete.");
    }
    

    
}
