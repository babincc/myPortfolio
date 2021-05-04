package crypt_keeper.objects;

import utils.toolbox.MiscTools;
import utils.toolbox.math.MathClass;

/**
 * This class is the ciphertext object. It is used to hold the different pieces
 * of the ciphertext so it doesn't have to get broken up every time a piece is
 * needed.
 * 
 * @author Christian Babin
 * @version 3.1.1
 * @since 1.0.0
 */
public class Ciphertext {

	private Version vNum;	// The version of Crypt Keeper that made this ciphertext
	private int offset;		// The ASCII offset for this ciphertext
	private String OTP;		// The one time pad for this ciphertext
	private String encStr;	// The encrypted string in this ciphertext
	private int endCap;		// The length of the ciphertext placed on the end to prevent attacks
	
	public Ciphertext() {
		
		vNum = new Version();
		offset = MathClass.genRand(0, 93);
		setOTP();
		
	}
	
	public Ciphertext(String ciphertext) {
		
		String vNumStr = ciphertext.substring(0, 12);
		String offsetStr = ciphertext.substring(12, 14);
		String OTPLenStr = ciphertext.substring(14, 16);
		
		Version vNum = null;
		int offset = -1;
		int OTPLen = -1;
		try {
			vNum = new Version(vNumStr);
			offset = Integer.parseInt(offsetStr);
			OTPLen = Integer.parseInt(OTPLenStr);
		} catch(NumberFormatException e) {
			System.out.println("ERROR: Either the wrong password was entered or the encrypted text has had characters added/removed/changed!");
			throw new IllegalArgumentException("Error Code: WP8");
		}
		
		String OTP = ciphertext.substring(16, 16+OTPLen);
		String encStr = ciphertext.substring(16+OTPLen, ciphertext.length()-7);
		
		String endCapStr = ciphertext.substring(ciphertext.length()-7);
		
		// if the user has messed with the length of the message, they will get this error code
		int endCap = -1;
		try{
			endCap = Integer.parseInt(endCapStr);
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException("Error Code: TL9");
		}
		
		setvNum(vNum);
		setOffset(offset);
		setOTP(OTP);
		setEncStr(encStr);
		setEndCap(endCap);
		
	}

	public void setvNum(Version vNum) {
		
		this.vNum = vNum;
	
	}

	public void setOffset(int offset) {
	
		this.offset = offset;
	
	}

	public void setOTP(String oTP) {
	
		OTP = oTP;
	
	}
	
	public void setOTP() {
		
		OTP = genOTP();
	
	}

	public void setEncStr(String encStr) {
	
		this.encStr = encStr;
	
	}
	
	public void setEndCap(int endCap) {
		
		this.endCap = endCap;
		
	}
	
	public Version getvNum() {
	
		return vNum;
	
	}

	public int getOffset() {
	
		return offset;
	
	}

	public String getOTP() {
	
		return OTP;
	
	}

	public String getEncStr() {
	
		return encStr;
	
	}
	
	public int getEndCap() {
		
		return endCap;
		
	}
	
	/**
	 * This method generates a random one time pad.
	 * 
	 * @return A random one time pad 5 to 25 chars long.
	 */
	private String genOTP() {
		
		String OTP = "";
		
		for(int i = 0; i < MathClass.genRand(5, 25); i++) {
			OTP += Character.toString(MathClass.genRand(33, 126));
		}

		return OTP;
		
	}
	
	/**
	 * This method checks to see if the ciphertext provided is an official
	 * ciphertext encrypted by Crypt Keeper. It looks at the endCap to make sure the
	 * length is correct.
	 * 
	 * @return True if the length is correct.
	 */
	public boolean isVerifiedCKCiphertext() {
		
		String ciphertext = this.toString();
		
		if(ciphertext.length() != endCap) {
			return false;
		} else {
			return true;
		}
		
	}
	
	/**
	 * This method is used with passwords to finalize the ciphertext. It sweeps away
	 * all of the data except for the encrypted string. This can be decrypted with
	 * only a password; so, the other data is not needed and only poses a security
	 * threat.
	 */
	public void finalSweep() {
		
		vNum = null;
		offset = -1;
		OTP = null;
		
	}
	
	/**
	 * This method converts the ciphertext object into a single string.
	 */
	@Override
	public String toString() {
		
		// this is for ciphertext objects that have been swept
		if(vNum == null && offset == -1 && OTP == null) {
			return encStr;
		}
		
		String vNumStr = vNum.toString();
		String offsetStr = MiscTools.padLeft(Integer.toString(offset), 2);
		String OTPLenStr = MiscTools.padLeft(Integer.toString(OTP.length()), 2);
		
		String toReturn = vNumStr + offsetStr + OTPLenStr + OTP + encStr;
		
		int endCap = toReturn.length() + 7;
		
		if(this.endCap <= 0) {
			setEndCap(endCap);
		}
		
		return toReturn + MiscTools.padLeft(Integer.toString(endCap), 7);
		
	}
	
}
