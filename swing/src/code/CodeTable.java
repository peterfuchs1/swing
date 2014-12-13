package code;

import java.util.HashMap;

public class CodeTable implements Enigma{
	private HashMap<String, Character> table;
	public static String DELIMITER=":";
	public CodeTable(){
		table=new HashMap<String, Character>();
	}
	@Override
	public void add(String key, char value){
		table.put(key, value);
	}
	@Override
	public char get(String key) throws NotFoundException{
		Character c=table.get(key);
		if(c==null)
			throw new NotFoundException("key: "+key+" couldn't be found");
		return c;
	}
	@Override
	public String convert(String s){
		return convert(s,DELIMITER);
	}
	@Override
	public String convert(String s, String d){
		String[] keys=s.split(d);
		StringBuilder sb=new StringBuilder();
		for(String k:keys){
			sb.append(table.get(k));
		}
		return sb.toString();
	}
	
}
