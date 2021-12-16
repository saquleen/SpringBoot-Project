package SpringBoot.Kickstart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class HomeResources {
	
	@Autowired
	UserDetailsService UserDetailsService;

	private static final String Admin = null;
	private static final String User = null;
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(UserDetailsService);
	}
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> user = UserRepository.findByUserName(userName);

		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return user.map(UserDetailsImpl::new).get();
	}
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole(Admin)
			.antMatchers("/user").hasAnyRole(Admin,User)
			.antMatchers("/all").permitAll()
			.and().formLogin();
	}
}
