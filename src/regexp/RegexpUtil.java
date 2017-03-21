package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUtil {
	public static String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/.?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
//	 "[.+?]";
	
	
	public static void main(String[] args) {
		String test="+?";
		System.out.println(matchSpecialMark(test));
		
	}
	
	
	/*
	 * 是否包含特殊字符
	 **/
   public static boolean matchSpecialMark(String obj){
	   boolean flag=false;
	   Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(obj);
		if(m.find()){
			System.out.println(m.group()+"\n");
			flag=true;
		}
		return flag;
   }
   
   
}
