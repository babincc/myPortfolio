package crypt_keeper.objects;

import utils.toolbox.MiscTools;

/**
 * This class is used to hold the version number of Crypt Keeper that is used to
 * make a ciphertext.
 * 
 * @author Christian Babin
 * @version 3.3.0
 * @since 1.0.0
 */
public class Version {

	private int macro;	// The leftmost place in the version number - !!.##.##
	private int meso;	// The middle place in the version number - ##.!!.##
	private int micro;	// The rightmost place in the version number - ##.##.!!
	
	/**
	 * Default constructor makes current Crypt Keeper program version number as the
	 * version object.
	 * 
	 * Current version: 3.3.0
	 */
	public Version() {
		
		macro = 3;
		meso = 3;
		micro = 0;
		
	}
	
	/**
	 * Make the Version object out of a string that represents the version number.
	 * 
	 * @param versionNum - A string that represents the version number. It is
	 *                   formatted: ############. This is meant to be broken down
	 *                   into four groups of four like so: #### #### ####. Each
	 *                   group represents a place in the version number.
	 * @throws NumberFormatException
	 * @throws StringIndexOutOfBoundsException
	 */
	public Version(String versionNum) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		setMacro(versionNum.substring(0, 4));
		setMeso(versionNum.substring(4, 8));
		setMicro(versionNum.substring(8, 12));
		
	}

	public void setMacro(int macro) {
		
		this.macro = macro;
	
	}
	
	/**
	 * This sets the macro when provided with a string.
	 * 
	 * @param macro - The leftmost place in the version number - !!.##.##
	 * @throws NumberFormatException
	 */
	public void setMacro(String macro) throws NumberFormatException {
		
		setMacro(Integer.parseInt(macro));
	
	}

	public void setMeso(int meso) {
	
		this.meso = meso;
	
	}
	
	/**
	 * This sets the meso when provided with a string.
	 * 
	 * @param macro - The middle place in the version number - ##.!!.##
	 * @throws NumberFormatException
	 */
	public void setMeso(String meso) throws NumberFormatException {
		
		setMeso(Integer.parseInt(meso));
	
	}

	public void setMicro(int micro) {
	
		this.micro = micro;
	
	}
	
	/**
	 * This sets the micro when provided with a string.
	 * 
	 * @param macro - The rightmost place in the version number - ##.##.!!
	 * @throws NumberFormatException
	 */
	public void setMicro(String micro) throws NumberFormatException {
		
		setMicro(Integer.parseInt(micro));
	
	}
	
	public int getMacro() {
	
		return macro;
	
	}
	
	/**
	 * This gets the formated string version of macro.
	 * 
	 * @return The string version of the macro number. It is formatted: ####
	 */
	public String getMacroStr() {
		
		String macroStr = Integer.toString(macro);
		
		macroStr = MiscTools.padLeft(macroStr, 4);
		
		if(macroStr.length() > 4) {
			throw new IllegalStateException("Error Code: TLMB4");
		}
		
		return macroStr;
	
	}

	public int getMeso() {
	
		return meso;
	
	}
	
	/**
	 * This gets the formated string version of meso.
	 * 
	 * @return The string version of the meso number. It is formatted: ####
	 */
	public String getMesoStr() {
		
		String mesoStr = Integer.toString(meso);
		
		mesoStr = MiscTools.padLeft(mesoStr, 4);
		
		if(mesoStr.length() > 4) {
			throw new IllegalStateException("Error Code: TLMB4");
		}
		
		return mesoStr;
	
	}

	public int getMicro() {
	
		return micro;
	
	}
	
	/**
	 * This gets the formated string version of micro.
	 * 
	 * @return The string version of the micro number. It is formatted: ####
	 */
	public String getMicroStr() {
		
		String microStr = Integer.toString(micro);
		
		microStr = MiscTools.padLeft(microStr, 4);
		
		if(microStr.length() > 4) {
			throw new IllegalStateException("Error Code: TLMB4");
		}
		
		return microStr;
	
	}
	
	/**
	 * This method converts the version number into a string to be placed in the
	 * ciphertext. It is formatted: ############. This is four groups of four. The
	 * first four are for the macro or leftmost place in the version number, the
	 * next four are for the meso or middle place in the version number, and the
	 * last four are the micro or rightmost place in the version number. Each group
	 * is padded on the left with zeros if the number isn't big enough to fill the
	 * whole space.
	 */
	@Override
	public String toString() {
		
		String macroStr = getMacroStr();
		String mesoStr = getMesoStr();
		String microStr = getMicroStr();
		
		return macroStr + mesoStr + microStr;
		
	}
	
}
