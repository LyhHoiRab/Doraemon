package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.wah.doraemon.security.exception.UtilsException;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils{

    public HttpClientUtils(){

    }

    private static CloseableHttpClient createHttpsClient(String certificate, String password){
        if(StringUtils.isBlank(certificate)){
            throw new UtilsException("证书路径不能为空");
        }

        try(FileInputStream fis = new FileInputStream(new File(certificate))){
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(fis, password.toCharArray());

            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy()).loadKeyMaterial(keyStore, password.toCharArray()).build();
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            return HttpClients.custom().setSSLSocketFactory(factory).build();
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    private static CloseableHttpClient createHttpsClient(){
        try{
            TrustStrategy trust = new TrustStrategy(){
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s){
                    return true;
                }
            };

            SSLContext context = new SSLContextBuilder().loadTrustMaterial(null, trust).build();
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(context);

            return HttpClients.custom().setSSLSocketFactory(factory).build();
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    private static CloseableHttpClient createHttpClient(){
        return HttpClients.createDefault();
    }

    private static void close(CloseableHttpClient client){
        if(client != null){
            try{
                client.close();
            }catch(IOException e){
                //忽略
            }
        }
    }

    private static String handler(HttpResponse response, Charset charset){
        if(response == null){
            throw new UtilsException("请求相应对象不能为空");
        }

        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new UtilsException("请求失败[{0}]", response.getEntity());
        }

        try{
            return EntityUtils.toString(response.getEntity(), charset);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static String post(CloseableHttpClient client, String url, Map<String, Object> params, Charset charset){
        try{
            if(client == null){
                throw new UtilsException("客户端请求不能为空");
            }
            if(StringUtils.isBlank(url)){
                throw new UtilsException("请求路径不能为空");
            }

            //创建请求
            HttpPost post = new HttpPost(url);

            if(params != null && !params.isEmpty()){
                List<NameValuePair> list = new ArrayList<NameValuePair>();

                for(Map.Entry<String, Object> param : params.entrySet()){
                    list.add(new BasicNameValuePair(param.getKey(), ObjectUtils.serialize(param.getValue())));
                }

                post.setEntity(new UrlEncodedFormEntity(list, charset));
            }

            HttpResponse response = client.execute(post);
            return handler(response, charset);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(client);
        }
    }

    public static String post(CloseableHttpClient client, String url, Object params, Charset charset){
        try{
            if(client == null){
                throw new UtilsException("客户端请求不能为空");
            }
            if(StringUtils.isBlank(url)){
                throw new UtilsException("请求路径不能为空");
            }

            //创建请求
            HttpPost post = new HttpPost(url);

            if(params != null){
                String json = ObjectUtils.serialize(params);
                post.setEntity(new StringEntity(json, charset));
            }

            HttpResponse response = client.execute(post);
            return handler(response, charset);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(client);
        }
    }

    public static String get(CloseableHttpClient client, String url, Map<String, Object> params, Charset charset){
        try{
            if(client == null){
                throw new UtilsException("客户端请求不能为空");
            }
            if(StringUtils.isBlank(url)){
                throw new UtilsException("请求路径不能为空");
            }

            if(params != null && !params.isEmpty()){
                String paramsStr = SortUtils.toString(params, "&");

                if(url.lastIndexOf("?") == -1){
                    url += "?";
                }

                url += paramsStr;
            }

            //创建请求
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            return handler(response, charset);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            close(client);
        }
    }
}
