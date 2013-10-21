package org.bb.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.zip.CRC32;

public class CHAP{

	public static final int TIMEOUT = 30;
	private HashMap<String, Long> challenges         = new HashMap<String, Long>();
	private HashMap<Long, Date>   challenge_validity = new HashMap<Long, Date>();
  
	public long createChallenge(String nickname){
		long challenge = new Random().nextLong();
    
		challenges.put(nickname, challenge);
		challenge_validity.put(challenge, new Date());
    
		return challenge;
	}
  
	public long getChallenge(String nickname){
		return this.challenges.get(nickname);
	}
  
	public static long createChecksum(long random, String password){
		if(password == null)
			return -1;
		CRC32 crc = new CRC32();
		crc.update(password.getBytes());
		return crc.getValue() * random;
	}
  
	public boolean isValid(String nickname){
		Long challenge = this.challenges.get(nickname);
		if(challenge == null)
			return false;
    
		Date time = challenge_validity.get(challenge);
		if(time == null)
			return false;
		if(time.getTime() < new Date().getTime() - 1000 * TIMEOUT)
			return false;
    
		return true;
	}
}
