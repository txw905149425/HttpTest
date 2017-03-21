package demoTest;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import util.HttpUtil;

public class ShiXinDemo {
   
	public static void main(String[] args) {
		HttpUtil ht=new HttpUtil();
		
	   String url="http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/showAbstractInfo-viewAbstractInfo.shtml?nrdAn=CN97307948&cid=CN97307948.719990203&sid=CN97307948.719990203";
//	  url="http://www.bjda.gov.cn/eportal/ui?pageId=331007&filter_LIKE_XKZH=&filter_LIKE_TITLE=&filter_EQ_FZJG=&currentPage=1&pageSize=20";
//	   curl "http://shiju.tax861.gov.cn/bjds/swcx/fzczxhcx/result.asp?aa=2"
//	   -H "Accept-Encoding: gzip, deflate, sdch" 
//	   -H "Accept-Language: zh-CN,zh;q=0.8" 
//	   -H "Upgrade-Insecure-Requests: 1" 
//	   -H "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36" 
//	   -H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8" 
//	   -H "Referer: http://shiju.tax861.gov.cn/bjds/swcx/fzczxhcx/result.asp?aa=1" 
//	   -H "Cookie: gwdshare_firstime=1477899548786; _gscu_825391512=779082192wrsku20; _gscu_163945954=77908219dkkr7q20; ASPSESSIONIDAQTSRTCQ=FKMNPDDBGKKMLCMNEGEAFDEE; ASPSESSIONIDASRRQQDS=PEMDDEDBAFDIHIFMMENBENOD; ASPSESSIONIDSSABBBQT=OHHIBEDBHDPKFDPFBJDFOECP; ASPSESSIONIDAQTQTQBT=CPMPCAACGAAALJGKFGNCPINM; ASPSESSIONIDSQCCCBTQ=FNKLEAACMOLOBFKDNEEIHJAB; ASPSESSIONIDAQRQQSCR=KCKHGAACLNJEELJDGJOAKFDK; ASPSESSIONIDSQADDBSR=HABOHFCCIMJCHFDPDMGKKBJP; _gscs_1145275138=t793468576b04kj17^|pv:5; _gscbrs_1145275138=1; _gscu_1145275138=77899548i20cie28" 
//	   -H "Connection: keep-alive" 

	  url="http://shiju.tax861.gov.cn/bjds/swcx/wfhcx/result_detail.asp?id=56";
	  HashMap<String, String> map=new HashMap<String, String>();
	  String html="";
	   try {
		html=ht.getHtml(url, map, "gb2312",1);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(html);
	}
	
//	public void parse(String html){
//		
//	}
	
}
