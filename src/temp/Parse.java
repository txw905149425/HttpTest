package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class Parse {
     
	public  HashMap<String, List<HashMap<String, Object>>>  parseMain(String html,String url){
		 HashMap<String, List<HashMap<String, Object>>>  map=new HashMap<String, List<HashMap<String,Object>>>();
		 List<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>(); 
         Document doc=Jsoup.parse(html);
         Element table=doc.select("table.box6").get(0);
         Elements tr=table.select("tr:contains(ÐÐÒµ)");
        	 HashMap<String,Object> hmap=new HashMap<String,Object>();
        	 Element td1=tr.get(0).getElementsByTag("td").get(1);
        	 Element td12=tr.get(0).getElementsByTag("td").get(2);
        	 Element td2=tr.get(1).getElementsByTag("td").get(1);
        	 Element td22=tr.get(1).getElementsByTag("td").get(2);
        	 hmap.put("hangye", td1.text());
        	 hmap.put("hangyemiaosu", td12.text());
        	 hmap.put("ICB",td2.text());
        	 hmap.put("ICBmiaosu", td22.text());
        	 hmap.put("url",url);
             list.add(hmap);
             map.put("test", list);
             
        return map;
    }
	
}
