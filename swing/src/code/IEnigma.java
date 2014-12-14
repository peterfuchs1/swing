package code;

public interface IEnigma {
	/**
	 * Add a new key/value pair
	 * @param key
	 * @param value
	 */
	public void add(String key, char value);
	/**
	 * get a conversion for the given key
	 * @param key
	 * @return converted character
	 * @throws NotFoundException
	 */
	public char get(String key) throws NotFoundException;
	/**
	 * Convert a string of keys delimited with a given delimiter 
	 * @param s
	 * @param delimiter 
	 * @return converted String
	 */
	public String convert(String s, String delimiter);
	/**
	 * Convert a string of keys delimited with a standard delimiter 
	 * @param s 
	 * @return converted String
	 */
	public String convert(String s);

}
