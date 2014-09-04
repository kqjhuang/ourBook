package com.bookfriend.http;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpAgent {

    private  String ip = "182.92.103.191";
    private  String port = "8080";
    private  HttpPost httppost ;
    private  DefaultHttpClient client ;
    private static String jSessionId = null;
    private HttpResponse response ;
    private UrlEncodedFormEntity urlEntity;  
    //创建接收返回数据的对象  
    private HttpEntity entity;  
    //创建流对象  
    private InputStream is;   
    
    /**
     * 
     * @param url 服务器资源定位
     * @param paras 传入的参数localhost:8080/login_ad
     * @return result 返回的Json
     */
    public  String request(String url,Map<String,Object> paras,String sessionId){
        String result=null;  
         StringBuffer sb=new StringBuffer(); 
         httppost = new HttpPost("http://"+ip+":"+port+"/"+url);
        
        //创建默认的客户端对象  
        client=new DefaultHttpClient(); 
        
        //用list封装要向服务器端发送的参数  
        List<BasicNameValuePair> pairs=new ArrayList<BasicNameValuePair>();
        
        if(paras != null)
        for(String key : paras.keySet())
        pairs.add(new BasicNameValuePair(key, (String) paras.get(key)));
        
        
        try {
            //用UrlEncodedFormEntity来封装List对象
        	 urlEntity = new UrlEncodedFormEntity(pairs,"utf-8");
            //设置使用的Entity  
            httppost.setEntity(urlEntity);
            
            if(jSessionId!=null)
            httppost.addHeader("Cookie", "JSESSIONID="+jSessionId);
            
            response=client.execute(httppost);
    
            CookieStore mCookieStore = client.getCookieStore();
            List<Cookie> cookies = mCookieStore.getCookies();
            
            for(Cookie cookie:cookies)
                if(cookie.getName().equals("JSESSIONID"))
                    jSessionId = cookie.getValue();
            entity=response.getEntity();  
            is=entity.getContent();  
            //下面是读取数据的过程  
            BufferedReader br=new BufferedReader(new InputStreamReader(is));  
            
            while((result=br.readLine())!=null){  
                sb.append(result);  
            }  
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
        return sb.toString();
    }
    
//    public static void httpname(){
//        HttpAgent httpAgent = new HttpAgent();      
//        HashMap<String, Object> paras = new HashMap<String, Object>();
//        paras.put("email", "admin@gmail.com");
//        paras.put("passwd", "123456");
//        String result = httpAgent.request("api/app/login", paras, "");
//        System.out.print(result);;
//    }
}