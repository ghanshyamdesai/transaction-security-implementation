package myTest;

import java.util.ArrayList;
import java.util.Collection;

public class tutorial {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		

		Collection values = new ArrayList<>();
		values.add(5);
		values.add(7);
		values.add(2);
		values.add("love Line");
		String s = values.toString();
		
		 s = s.substring(1, s.length()-1);
		 System.out.println(s);	
	
	}
	
	@SuppressWarnings("unused")
	private static StringBuffer test(String Men, String girl){
		StringBuffer s = new StringBuffer();
		s.append(Men);
		s.append(" loves ");
		s.append(girl);
		
		return s;
		
	}

}
