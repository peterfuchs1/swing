package max;
/**
 * 
 * @author Walter Rafeiner-Magor
 *
 */
public final class Util {
	/**
	 * 
	 * @param <T> Klasse implementiert Comparable
	 * @param a
	 * @param b
	 * @return Maxwert (a,b)
	 */
	public final static <T extends Comparable<T>> T max( T a, T b) {
		return (a.compareTo(b)>0)?a:b;
	}
	/**
	 * 
	 * @param <T> Klasse implementiert Comparable
	 * @param m
	 * @return Maxwert (a,b)
	 */
	public final static <T extends Comparable<T>> T max( T[] m) {
		T maxwert=m[0];
		for(int i=1;i<m.length;i++) 
			maxwert=Util.max(maxwert,m[i]);
		return maxwert;
	}
	/**
	 * 
	 * @param <T> Klasse implementiert Comparable
	 * @param m
	 * @return Maxwert (a,b)
	 */
	public final static <T extends Comparable<T>> T min( T[] m) {
		T minwert=m[0];
		for(int i=1;i<m.length;i++) 
			minwert=Util.min(minwert,m[i]);
		return minwert;
	}
	/**
	 * 
	 * @param <T> Klasse implementiert Comparable
	 * @param a 
	 * @param b
	 * @return MinWert(a,b)
	 */
	public final static <T extends Comparable<T>> T min( T a, T b) {
		return (a.compareTo(b)<0)?a:b;
	}
	/**
	 * 
	 * @param <T> Klasse implementiert Comparable
	 * @param m
	 * @param n
	 * @return Zufallswert (a,b)
	 */
	public final static  <T> T random( T m, T n ) 
	{ 
	  return Math.random() > 0.5 ? m : n; 
	}
	/**
	 * 
	 * @param <T> beliebiger Objekttyp
	 * @param m Array von T
	 * @return Zufallswert 
	 */
	public final static  <T> T random( T[] m ) 
	{ 
		int i=(int)(Math.random()*m.length);
	  return m[i]; 
	}
	/**
	 * 
	 * @param <T> Klasse implementiert Comparable
	 * @param a
	 * @param b
	 * @return bei Gleichheit true sonst false
	 */
	public final static <T extends Comparable<T>> boolean equalTo( T a, T b) {
		return (a.compareTo(b)==0);
	}
	public final static int hexToInteger(String s){
		return hexToInteger(s,0,s.length()-1);
	}
	public final static int hexToInteger(String s,int start){
		start=(start<0)?0:start;
		start=(start>=s.length())?s.length()-1:start;
		return hexToInteger(s,start,s.length()-1);
	}
	/**
	 * Umwandlung eines Hexstrings in ein int-Value
	 * @param s String
	 * @param start 
	 * @param end
	 * @return intValue
	 */
	public final static int hexToInteger(String s,int start, int end) {
		String hex="0123456789abcdef";
		s=s.toLowerCase();
		int intValue=0;
		for(int i=start;i<=end;i++) 
			intValue=intValue*16+hex.indexOf(s.charAt(i));
		return intValue;
		
	}

}
