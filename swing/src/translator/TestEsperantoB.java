/**
 * 
 */
package translator;

/**
 * @author Walter Rafeiner-Magor
 * @version 1.0
 */
public class TestEsperantoB {
	public static String[][] values = { 
		{"en","in"},
		{"multaj","vielen"},
		{"lokoj","Orten"},
		{"de","von/vom"},
		{"Ĉinio","China"},
		{"ĉinio","China"},
		{"estis","waren"},
		{"dum","während"},
		{"trosekeco","Dürre"},
		{"oni","im"},
		{"preĝis","betete"},
		{"temploj","Tempel"},
		{"ke","dass"},
		{"drako-reĝo","Drachenkönig"},
		{"donu","geben"},
		{"pluvon","Regen"},
		{"al","der/die/das"},
		{"la","für"},
		{"homa","Menschen"},
		{"mondo","Welt"}
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Translator t = new Translator();
		// Insert all pairs with key and value
		for (String[] pair : values) {
			t.add(pair[0], pair[1]);
		}
		// test a special string to convert
		String toConvert = "Dum,trosekeco,preĝis,homa,oni,temploj,ke,drako-reĝo,donu,pluvon,la,al,mondo";
		String s = t.convert(toConvert, ",");
		System.out.println("Konversion: " + s);
		System.out.println(s.equals("während Dürre betete Menschen im Tempel dass Drachenkönig "
				+ "geben Regen für der/die/das Welt"));
	}

}
