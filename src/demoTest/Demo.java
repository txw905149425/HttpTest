package demoTest;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import util.HttpUtil;

public class Demo {
//	bcookie='v=2&5d42be9e-1ace-4ca6-8fc0-094a0cacdf62'; bscookie='v=1&20160930015445785c4adf-27f2-47a9-8bcd-86dce488819aAQFgtlpw69oSWsoptEM6j6e5D2vLKbLj'; visit='v=1&G'; JSESSIONID=ajax:6436865394494421048; lidc='b=SGST03:g=2:u=1:i=1476063691:t=1476150091:s=AQFAChUXVHS4H2P9XjtJAoPvEphjBTMf'; _ga=GA1.2.575935738.1475200269;lang='v=2&lang=zh-cn'
//	bcookie='v=2&5d42be9e-1ace-4ca6-8fc0-094a0cacdf62'; bscookie='v=1&2016093001362229b09e61-a511-401b-81ab-b834bc224019AQHDH91BP5wYSW066ZYfFrv60JxsG4Vu'; visit='v=1&M'; JSESSIONID='ajax:7718963104257182190';lidc='b=SB03:g=16:u=2:i=1476157359:t=1476170972:s=AQF6adxjVBXdGvy-sFA1CA185sK13avG'; _ga=GA1.2.575935738.1475200269;lang='v=2&lang=zh-cn'join_wall=AQF35dv1OO-vmAAAAVexW2dH2jYjLStDZI2H2zexTzoTN81irxs87HzHExnjO1eOgryv_aiug5v7;  li_at=AQEDAR_ElQcAVWsrAAABV7HVt6wAAAFXskOUrFEAsMFj7yAfUpLvNY2F7BgTGicJcJmfR08gcGc8oOVisoquQJ3hdeFkPxM1zVvV8YrFzoylR1nC_OmRm5jC93cwBYbSdQj6UEdxYINTXL7-XjfYSGav; liap=true; RT=s=1476157357231&r=https%3A%2F%2Fwww.linkedin.com%2Fuas%2Flogin%3Ftrk%3DD8E90337EA%26session_redirect%3Dhttps%253A%252F%252Fwww.linkedin.com%252Fin%252F%2525E5%25258B%2525A4%2525E9%2525A2%252596-%2525E5%2525BD%2525AD-a7943a118; _lipt=0_2VrfaHWzyiwkNILOdzLnMcHqnm87NjspYY-a7L3fbGouVFdyrTPlcDMpNSIwxouddhS2yezb7xASJK9efwhgE0FxNVjhUpIZouCFWL3rTDPfMouXZqFu8Qgp5ZAvI0istvKQ6RjvCL8NmwBBZYJ59Y; wutan=Ry0OhC80Nu8BJSRDGeQN5XvA0ANfWeKmR9uTTbHGmZE=; ; 
//	bcookie='v=2&e6c9d5c3-ccc9-4eac-8cb2-9b6ef731e6a2'; _gat=1; _ga=GA1.2.62148058.1476158087; li_at=AQEDARoxnIgArJ4FAAABV7Hi97oAAAFXslDUulEAqA5iOW_he8eovFCIqsz9rrFNU4KkHrSQ2MeXblV3CWgJBp-6YGf8v1P63OP_090AN0oXjGu7GVuI_KuGwpZBZI6TM4vkfoRkVH6ovz17XKO-kb5g; liap=true; JSESSIONID='ajax:8126571459201491168'; visit='v=1&M'; wutan=OYwNmmehKs9RQXXzmdtEQE6SaMXF8II9mAOJuBo4Nrw=; lidc='b=SB52:g=6:u=7:i=1476158230:t=1476244625:s=AQEz5tIXLVeq6jGfkGC5JnNjGWgaM1KK'; RT=s=1476158241923&r=https%3A%2F%2Fwww.linkedin.com%2Ffetch%2FimportAndInviteEntry%3Ftrk%3Dnav_utilities_add_connx; _lipt=0_7L64bmg1p5dcMsn-ESyKFAIeEXLC1bimwhy9rXMph-mY9PSjoIEi_quo4F2EtLcKo-Myb43jHtp0ry31WMa8SUxk8DPwMuP_cFJpc3QnBrF8aQdQKB-F1M1ji9Wl3k-yE-LuFvsQJRXItO0U3XnwWfVbfa06C7yF9bzNZ0et7r_; lang='v=2&lang=zh-cn&c=&pps=1'
//	bcookie='v=2&5d42be9e-1ace-4ca6-8fc0-094a0cacdf62'; bscookie='v=1&2016093001362229b09e61-a511-401b-81ab-b834bc224019AQHDH91BP5wYSW066ZYfFrv60JxsG4Vu'; visit='v=1&M'; join_wall=AQF35dv1OO-vmAAAAVexW2dH2jYjLStDZI2H2zexTzoTN81irxs87HzHExnjO1eOgryv_aiug5v7; _ga=GA1.2.575935738.1475200269; JSESSIONID='ajax:7085487197441839420'; li_at=AQEDAR_ElQcAi0VXAAABV7I3busAAAFXsqVL61EAdcSIEXGtiJArRkwDxJL3-ysq44cncrXfPybW8sG1c1APyyA6t4oyyMk28dDECGpwneoWTxogihOi6saTISXWFQaMR13QAK2b_N1Vj7QortpLohSK; liap=true; RT=s=1476163779117&r=https%3A%2F%2Fwww.linkedin.com%2Fuas%2Flogin%3Ftrk%3DD8E90337EA%26session_redirect%3Dhttps%253A%252F%252Fwww.linkedin.com%252Fin%252F%2525E6%2525B9%252598%2525E5%2525B9%2525B3-%2525E5%25258D%252593-48a6abaa%253Ftrk%253Dpub-pbmap; wutan=49p2tVD+s6OcsybWZd4yfo0vrLSyXkHtRYiF/ZS9CzY=; _lipt=0_2VrfaHWzyiwkNILOdzLnMcHqnm87NjspYY-a7L3fbGouVFdyrTPlcDMpNSIwxouddhS2yezb7xASJK9efwhgE0FxNVjhUpIZouCFWL3rTDPfMouXZqFu8Qgp5ZAvI0istvKQ6RjvCL8NmwBBZYJ59Y; lidc='b=SB03:g=16:u=2:i=1476163795:t=1476170972:s=AQGGBPkzaO3mldlL0SG6vlAZ5nzRcTAC'; lang='v=2&lang=zh-cn'
//	bcookie='v=2&47d86b7c-f894-470c-8a7b-f76e8cfaa40a'; JSESSIONID=ajax:4184711891459036967; bscookie='v=1&201610110606047fa2e592-d4cd-4be5-8123-9020c27bfb30AQGHbPFXnerhb299YzNtFsno2n34ifRD'; lidc='b=SGST02:g=3:u=1:i=1476165964:t=1476252364:s=AQHoaJKgwZOpEMnUUneiX0ew8vfrJQOo'; __utmt=1; __utma=226841088.2117789793.1476165974.1476165974.1476165974.1; __utmb=226841088.1.10.1476165974; __utmc=226841088; __utmz=226841088.1476165974.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _ga=GA1.2.2117789793.1476165974; _gat=1; leo_auth_token='GST:88oJl08yUKK053__MSxBK3UvdkKcUlIZKZxCJMZV1fR81L2KnAl-_x:1476165973:ead8ac4f37051dee017b1c9b08b873e8d10b88a5'; visit='v=1&G'; lang='v=2&lang=zh-cn'
//	JSESSIONID=ajax:1906245923052310780; bcookie='v=2&f28ae603-818a-444e-8b4b-bdc4689d2b22'; bscookie='v=1&20161011063038a63c187e-1d4a-4cca-8a2a-a5d336e4a1ffAQGPpSleMH8d4K-5j6H4JqP9Q-HTZ4Oh'; lidc='b=SGST09:g=1:u=1:i=1476167439:t=1476253839:s=AQGD3CHJYSfthJGmUFCFNHdcivmwjMvh'; join_wall=AQFm_uuDFUfgYgAAAVeyc859-YUFRagyCJfKWM3DFAhx3-vOviAwis3UU50O_-XWFtqd8qf7Szj8zzgsqkD7gw; visit='v=1&G'; leo_auth_token='GST:8U5yjBZ9DfP0KssPw1ObWH3qOb-TMtcPvK1Hq6ZsDS-TZWjhhCNd8Q:1476167723:b72b14d1cdaf7dd3313ee3a9ea16478db24b3f5a'; lang='v=2&lang=zh-cn'; RT=s=1476167855361&r=https%3A%2F%2Fcn.linkedin.com%2Fin%2F%25E6%2580%259D%25E8%25BF%259C-%25E7%258E%258B-a92a03a4; __utmt=1; __utma=226841088.788745048.1476167568.1476167568.1476167568.1; __utmb=226841088.5.10.1476167568; __utmc=226841088; __utmz=226841088.1476167568.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _ga=GA1.2.788745048.1476167568; _gat=1


	public static void main(String[] args)throws Exception {
		HttpUtil ht=new HttpUtil();
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("Accept", "text/plain, */*; q=0.01");
		map.put("Accept-Encoding", "gzip, deflate");
		map.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		map.put("Host", "www.creditchina.gov.cn");
		map.put("Proxy-Authorization", "Basic Y2psOndpZmc3ZGV4");
		map.put("Referer", "http://www.creditchina.gov.cn/search_all");
		map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
		map.put("X-Requested-With", "XMLHttpRequest");
		map.put("Cookie", "Hm_lvt_0076fef7e919d8d7b24383dc8f1c852a=1476753129,1476753777,1476755618,1476756390; Hm_lpvt_0076fef7e919d8d7b24383dc8f1c852a=1476797020");
		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("keyword", "%E6%B2%AA%E7%94%B5%E8%82%A1%E4%BB%BD"));
		list.add(new BasicNameValuePair("searchtype", "1"));
		list.add(new BasicNameValuePair("objectType", "2"));
		list.add(new BasicNameValuePair("areas", ""));
		list.add(new BasicNameValuePair("creditType", ""));
		list.add(new BasicNameValuePair("dataType", "1"));
		list.add(new BasicNameValuePair("areaCode", ""));
		list.add(new BasicNameValuePair("templateId", ""));
		list.add(new BasicNameValuePair("exact", "0"));
		list.add(new BasicNameValuePair("page", "1"));
		String url="http://www.creditchina.gov.cn/publicity_info_search?t=1476797385257";
//		String url="http://www.creditchina.gov.cn/publicity_info_search?t=1476797385257&keyword=%E6%B2%AA%E7%94%B5%E8%82%A1%E4%BB%BD&searchtype=1&objectType=2&areas=&creditType=&dataType=1&areaCode=&templateId=&exact=0&page=1";
//		String html=ht.getHtml(url, map,"utf-8",2);
		String html=ht.postHtml(url, map,list,1000,2);
		System.out.println(html);
		
//		警告;代理服务器拒绝;连接被重置;页面载入出错;403 Forbidden;Too Many Request;Bad Gateway;
//		
	}
//	wifg7dex
}
