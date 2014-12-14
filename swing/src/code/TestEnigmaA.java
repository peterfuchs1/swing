/**
 * 
 */
package code;

/**
 * @author Walter Rafeiner-Magor
 * @version 1.0
 */
public class TestEnigmaA {
	public static String[][] values={
			{"4","A"},
			{"13","B"},
			{"23","C"},
			{"12","D"},
			{"1","E"},
			{"2","F"},
			{"5","G"},
			{"47","H"},
			{"32","I"},
			{"22","J"},
			{"29","K"},
			{"8","L"},
			{"76","M"},
			{"54","N"},
			{"31","O"},
			{"17","P"},
			{"18","Q"},
			{"77","R"},
			{"64","S"},
			{"61","T"},
			{"44","U"},
			{"34","V"},
			{"9","W"},
			{"10","X"},
			{"51","Y"},
			{"66","Z"}
			};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Enigma t=new Enigma();
		// Insert all pairs with key and value
		for(String[] pair:values){
			t.add(pair[0], pair[1].charAt(0));
		}
		// test a special string to convert
		String toConvert="76:1:32:54:1:61:9:1:5:1:54";
		String s=t.convert(toConvert,":");
		System.out.println("Konversion: "+s);
		System.out.println(s.equals("MEINETWEGEN"));
	}

}
