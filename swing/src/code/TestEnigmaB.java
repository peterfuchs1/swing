/**
 * 
 */
package code;

/**
 * @author Walter Rafeiner-Magor
 * @version 1.0
 */
public class TestEnigmaB {
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
			{"49","K"},
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
		String toConvert="9;1;32;61;1;77;76;4;23;47;1;54";
		String s=t.convert(toConvert,";");
		System.out.println("Konversion: "+s);
		System.out.println(s.equals("WEITERMACHEN"));
	}

}
