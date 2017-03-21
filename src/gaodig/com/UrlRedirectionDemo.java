package gaodig.com;

import java.io.IOException; 
import java.net.URI; 
import java.util.List; 
  
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.config.CookieSpecs; 
import org.apache.http.client.config.RequestConfig; 
import org.apache.http.client.methods.CloseableHttpResponse; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.client.protocol.HttpClientContext; 
import org.apache.http.impl.client.CloseableHttpClient; 
import org.apache.http.impl.client.HttpClients; 
public class UrlRedirectionDemo {
	  // �����Agent 
    public static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19"; 
      
    // ����������HttpClient 
    private static final CloseableHttpClient httpClient = HttpClients 
            .custom() 
            .setUserAgent(USER_AGENT) 
            .setDefaultRequestConfig( 
                    RequestConfig.custom() 
                            .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY) 
                            .build()).build(); 
      
    /** 
     * ���ݸ��������ӻ�ȡ���е��ض���λ�� 
     * @param link ���������� 
     * @return 
     * @throws ClientProtocolException 
     * @throws IOException 
     */ 
    public List<URI> getAllRedirectLocations(String link) throws ClientProtocolException, IOException{ 
        List<URI> redirectLocations = null; 
        CloseableHttpResponse response = null; 
        try{ 
            HttpClientContext context = HttpClientContext.create(); 
            HttpGet httpGet = new HttpGet(link); 
            response = httpClient.execute(httpGet, context); 
              
            // ��ȡ���е��ض���λ�� 
            redirectLocations = context.getRedirectLocations(); 
        } finally{ 
            if(response!=null){ 
                response.close(); 
            } 
        } 
        return redirectLocations; 
    } 
      
    public static void main(String[] args) throws ClientProtocolException, IOException{ 
        // ����URL 
        String link = "http://www.cninfo.com.cn/unit/list.html?w=672&s=%2Fdisclosure%2Fsummary%2Fstocks%2Fsummary1y%2Fcninfo%2F002217.js&t=1,3"; 
        UrlRedirectionDemo demo = new UrlRedirectionDemo(); 
        List<URI> allRedirectLocations = demo.getAllRedirectLocations(link); 
        if(allRedirectLocations!=null){ 
            System.out.println(link); 
            for(URI uri : allRedirectLocations){ 
                System.out.println("|\nv\n" + uri.toASCIIString()); 
            } 
        } else{ 
            System.out.println("Not found!"); 
        } 
    } 
}
