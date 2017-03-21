package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
	
		public static void readFile(String url){
			 try {
				   // ��D:/test.txt�ļ���ȡ����������
				   InputStream input = new FileInputStream(url);
				   // ����BufferedReader����gb2312�ı��뷽ʽ��ȡ�ļ�
				   BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf8"));
				   String line = null;
				   PrintWriter pw=new PrintWriter(new File("d:/last.txt"));
				   // ���ж�ȡ�ı���ֱ��ĩβ��һ�㶼��ôд��
				   while ((line = reader.readLine()) != null) {
				    // ��ӡ��ǰ���ַ���
//				    System.out.println(line);
					System.out.println(line.split("=>")[1]);
					pw.println(line.split("=>")[1]);
//				    String punctuations = "/{1}[a-zA-Z]{2}[0-9]{6}";
//					Pattern pattern = Pattern.compile(punctuations);
//					Matcher matcher = pattern.matcher(line);
//					while (matcher.find()){
//						System.out.println(matcher.group());
//						pw.println(matcher.group());
//					}
				   }
				   pw.close();
				   reader.close();
				   input.close();
				  } catch (FileNotFoundException e) {
				   e.printStackTrace();
				  } catch (IOException e) {
				   e.printStackTrace();
				  }
	   }
	  	
		

		public static ArrayList<String>  readFileReturn(String url){
			ArrayList<String> list=new ArrayList<String>();
			 try {
				   // ��D:/test.txt�ļ���ȡ����������
				   InputStream input = new FileInputStream(url);
				   // ����BufferedReader����gb2312�ı��뷽ʽ��ȡ�ļ�
				   BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf8"));
				   String line = null;
				   // ���ж�ȡ�ı���ֱ��ĩβ��һ�㶼��ôд��
				   while ((line = reader.readLine()) != null) {
					   list.add(line.trim());
//					   String punctuations = "[ufe30-uffa0]";
//						Pattern pattern = Pattern.compile(punctuations);
//						Matcher matcher = pattern.matcher(line);
//						while (matcher.find()){
//						      System.out.println("yes");
//						      System.out.println(matcher.group());
//						}
				   }
				   reader.close();
				   input.close();
				  } catch (FileNotFoundException e) {
				   e.printStackTrace();
				  } catch (IOException e) {
				   e.printStackTrace();
				  }
			 
			 return list;
	   }
		
		
		
		
		
		
}
