package evgenyt.tacos;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;

@Data
public class RegistrationForm {
	
	private String username;
	private String password;
	private String fullname;
	private String address;
	private String phone;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User( username, passwordEncoder.encode(password),
				fullname, address, phone);
	}
}
