package com.rgtproject.global.util.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public final class PasswordUtils {

	public  static String hashPassword(String plainPassword){
		return BCrypt.hashpw(plainPassword,BCrypt.gensalt());
	}
	public  static boolean checkPw(String plainPw,String hashpw){
		return BCrypt.checkpw(plainPw,hashpw);
	}
}
