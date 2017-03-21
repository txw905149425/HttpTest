package demoTest;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import util.HttpUtil;

public class Test {
	
	
	public static void main(String[] args) throws Exception {
		 HttpUtil ht=new HttpUtil();
		 HashMap<String, String> map=new HashMap<String, String>();
//		 map.put("Accept-Encoding", "gzip, deflate, sdch, br");
//		 map.put("Accept-Language", "zh-CN,zh;q=0.8");
//		 map.put("Upgrade-Insecure-Requests", "1");
//		 map.put("Host", "api.tianyancha.com");
//		 map.put("Content-Type", "application/json");
//		 map.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.1; IUNI N1 Build/LMY47D) NewsArticle/5.5.4 okhttp/2.6.1");
//		 map.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
//		 map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		 map.put("Cache-Control", "max-age=0");
//		 map.put("Connection", "keep-alive");
//		 map.put("Referer","http://rcpu.cwun.org/PersonInfo.aspx?st=2&d=6");
		 
//		 map.put("Cookie","JSESSIONID=9943E26DFF6FE3136F81FE72C4413266");
//		 String url="http://www.linkedin.com/vsearch/f?type=all&keywords=%E7%8E%8B%E6%80%9D%E9%9B%A8&orig=GLHD&rsid=&pageKey=nprofile_view_nonself&trkInfo=tarId%3A1476255156457&trk=global_header&search=%E6%90%9C%E7%B4%A2";
		String url="http://210.12.219.18/jianguanfabuweb/handler/GetCompanyData.ashx?method=GetEngineersData&name=&card=&stampnum=&company=&major=-1&PageIndex=3&PageSize=";
		url="http://www.ccgp-shaanxi.gov.cn/zbAll_view.jsp?ClassName=C0001&pages=1";
		url="http://www.sczfcg.com/AgencyShowController.do?method=getAgencyInfo&anhui=anhui&objId=402886873b304024013b4dfbcae50697&bh=政采代（乙）字第51090号&fv=01&sqrq=2012-09-10&yxq=2015-09-09&jb=01&zzfile=7e952f52_9afb_4bce_a44f_878f66ad0c8b";
//	    url="http://www.ccgp-hubei.gov.cn/fnoticeAction!listFNoticeInfos_n.action?rank=1&queryInfo.GGLX=%E6%8B%9B%E6%A0%87%E5%85%AC%E5%91%8A&queryInfo.BEGINTIME1=1998-01-01&queryInfo.ENDTIME1=2018-01-01&queryInfo.QYBM=420001&queryInfo.pageSize=50&queryInfo.curPage=2";
//	url="http://www.ccgp-hubei.gov.cn/fnoticeAction!listFNoticeInfos_n.action?rank=1&queryInfo.GGLX=%E6%8B%9B%E6%A0%87%E5%85%AC%E5%91%8A&queryInfo.BEGINTIME1=1998-01-01&queryInfo.ENDTIME1=2018-01-01&queryInfo.pageSize=50&queryInfo.curPage=1";
//	湖北工业大学春节大米物资采购项目成交结果公告
		String html= ht.getHtml(url, map, "utf-8", 1);
		System.out.println(html);
//		Document doc=Jsoup.parse(html);
//		System.out.println(doc.select(".pagesite>div").size());
//		String test="https://cn.linkedin.com/pub/dir/敏/陈/cn-0-中国";
//		test=java.net.URLEncoder.encode(test,"utf-8");
//		System.out.println(test);
		
	}
//	wifg7dex
  
}
