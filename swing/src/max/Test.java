package max;

import java.io.Serializable;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			Integer a=new Integer(5);
			Integer b=new Integer(3);
			Integer[] iArray=new Integer[100];
			double d1=6.0,d2=7.5;
			String s1="Eins";
			String s2="Zwei";
			Serializable so1 = new Serializable() { }; 
			Serializable so2 = new Serializable() { };
			System.out.println("max("+a+","+b+"): "+Util.max(a,b));
			System.out.println("max("+d1+","+d2+"): "+Util.max(d1,d2));
			System.out.println("max("+s1+","+s2+"): "+Util.max(s1,s2));
			System.out.println("random("+a+","+b+"): "+Util.random(a,b));
			System.out.println("random("+d1+","+d2+"): "+Util.random(d1,d2));
			System.out.println("random("+s1+","+s2+"): "+Util.random(s1,s2));
			System.out.println("min("+a+","+b+"): "+Util.min(a,b));
			System.out.println("min("+d1+","+d2+"): "+Util.min(d1,d2));
			System.out.println("min("+s1+","+s2+"): "+Util.min(s1,s2));
			System.out.println("equalTo("+s1+","+s1+"): "+Util.equalTo(s1,s1));
			for (int i=0;i<iArray.length;i++)
				iArray[i]=i;
			System.out.println("random iArray: "+Util.random(iArray));
			System.out.println("grösste Zahl des iArray: "+Util.max(iArray));
			String s ="FF6347";
			System.out.println("String "+s+"="+Util.hexToInteger(s,0,1));
			
	}

}
