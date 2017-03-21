package code;
    import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

    public class Test
    {
        // 代理服务器
        final static String ProxyHost = "proxy.abuyun.com";
        final static Integer ProxyPort = 9010;

        // 代理隧道验证信息
        final static String ProxyUser = "HYN02A3L87U914YP";
        final static String ProxyPass = "C497A086A8EDCED4";

        // IP切换协议头
        final static String ProxyHeadKey = "Proxy-Switch-Ip";
        final static String ProxyHeadVal = "yes";

        public static void getUrlProxyContent(String url)
        {
            BasicHeader header = new BasicHeader(ProxyHeadKey, ProxyHeadVal);
            List<Header> list = new ArrayList<Header>();
            list.add(header);

            HttpHost target = new HttpHost(ProxyHost, ProxyPort, "http");

            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(ProxyUser, ProxyPass));

            CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultCredentialsProvider(credsProvider)
            .setDefaultHeaders(list).build();

            // Create AuthCache instance
            AuthCache authCache = new BasicAuthCache();
            // Generate BASIC scheme object and add it to the local
            // auth cache
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);

            // Add AuthCache to the execution context
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);

            HttpGet httpGet = new HttpGet(url);

            CloseableHttpResponse response = null;
            try
            {
                response = httpClient.execute(target, httpGet, localContext);

                System.out.println(EntityUtils.toString(response.getEntity()));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {}
        }

        public static void main(String[] args)
        {
        	
        	String str="/viewer.do?id=22636697&ver=2";
        	
        	Pattern p = Pattern.compile("(id=.*)");
        	 Matcher m = p.matcher(str);
        	 if(m.find()){
        		 System.out.println(m.group(1));
        	 }

//        	System.out.println(StringEscapeUtils.unescapeHtml("&#22825;&#27941;&#24066;&#19996;&#20029;&#21306;&#25945;&#32946;&#23616;&#26426;&#20851;&#22825;&#27941;&#24066;&#19996;&#20029;&#21306;&#25945;&#32946;&#31995;&#32479;&#23454;&#39564;&#23567;&#23398;&#31561;51&#25152;&#20013;&#23567;&#23398;&#25945;&#23460;&#20869;&#23433;&#35013;&#25668;&#20687;&#22836;&#39033;&#30446;&#65288;&#39033;&#30446;&#32534;&#21495;&#65306;DLGPC-2016-68&#65289; &#37319;&#36141;&#38656;&#27714;&#24449;&#38598;&#24847;&#35265;&#20844;&#21578;"));
//        	 System.out.println(StringEscapeUtils.escapeHtml("你好"));
            // 要访问的目标页面
//            String targetUrl = "https://test.abuyun.com/proxy.php";
            //String targetUrl = "http://proxy.abuyun.com/switch-ip";
            //String targetUrl = "http://proxy.abuyun.com/current-ip";

//            getUrlProxyContent(targetUrl);
        }
    }