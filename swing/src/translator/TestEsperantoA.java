/**
 * 
 */
package translator;

/**
 * @author Walter Rafeiner-Magor
 * @version 1.0
 */
public class TestEsperantoA {
	public static String[][] values = { 
		{"en","in"},
		{"multaj","vielen"},
		{"lokoj","Orten"},
		{"de","von/vom"},
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
		String toConvert = "En;multaj;lokoj;de;Ĉinio;estis;temploj;de;la;drako-reĝo";
		String s = t.convert(toConvert, ";");
		System.out.println("Konversion: " + s);
		System.out.println(s.equals("in vielen Orten von/vom China waren Tempel von/vom für Drachenkönig"));
	}

}