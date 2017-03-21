package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeChar {
  
	public static String NCRToChina(String ncr){
       Pattern p=Pattern.compile("&#x.{4};");
		Matcher m=p.matcher(ncr);
		String ss="";
		while(m.find()){
			ss+=m.group().toString();
			System.out.println(m.group());
		}
		String str=ss.replaceAll("&#x","\\\\u").replaceAll(";","");
		    int n = str.length() / 6;  
		    StringBuilder sb = new StringBuilder(n);  
		    for (int i = 0, j = 2; i < n; i++, j += 6) {  
		        String code = str.substring(j, j + 4);  
		        char ch = (char) Integer.parseInt(code, 16);  
		        sb.append(ch);  
		    }  
	  return sb.toString();
	}
	
	public static String ascii2native(String ascii) {  
	    int n = ascii.length() / 6;  
	    StringBuilder sb = new StringBuilder(n);  
	    for (int i = 0, j = 2; i < n; i++, j += 6) {  
	        String code = ascii.substring(j, j + 4);  
	        char ch = (char) Integer.parseInt(code, 16);  
	        sb.append(ch);  
	    }  
	    return sb.toString();  
	}  
	public static void main(String[] args) {
		String str=NCRToChina("storm -base)&#x6846;sksksl&#x67B6;&#x5206;&#x6790;");
////		System.out.println(str);
//		String china=ascii2native(str);
		System.out.println(str);
	}
}
