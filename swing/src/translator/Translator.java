package translator;

import java.util.HashMap;

public class Translator implements ITranslator{
	private HashMap<String, String> table;
	public static String DELIMITER=",";
	public Translator(){
		table=new HashMap<String, String>();
	}
	@Override
	public void add(String key, String value){
		table.put(key, value);
	}
	@Override
	public String get(String key) throws NotFoundException{
		String c=table.get(key.toLowerCase());
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
		for(int i=0;i<keys.length;i++){
			if(i!=0) sb.append(' ');
			sb.append(table.get(keys[i].toLowerCase()));
		}
		return sb.toString();
	}
	
}
