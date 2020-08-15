package com.hyd.receivecdc.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Date;


/**
 * HTTP客户端工具类（支持HTTPS）
 *
 */
@Slf4j
public class HttpClientUtils {



    /**
     * 判断指定URL是否能ping通
     * @param ip
     * @return
     */
    public static boolean isConnect(String ip) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("ping " + ip);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                // 优化速度
                if (line.contains("请求超时")) {
                    return false;
                }
            }
            is.close();
            isr.close();
            br.close();
            if (!sb.toString().equals("")) {
                return sb.toString().indexOf("TTL") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * http的get请求
     *
     * @param url
     */
    public static String get(String url) {
        return get(url, "UTF-8");
    }

    /**
     * http的get请求
     *
     * @param url
     */
    public static String get(String url, String charset) {
        HttpGet httpGet = new HttpGet(url);
        return executeRequest(httpGet, charset);
    }

    /**
     * http的get请求，增加异步请求头参数
     *
     * @param url
     */
    public static String ajaxGet(String url) {
        return ajaxGet(url, "UTF-8");
    }

    /**
     * http的get请求，增加异步请求头参数
     *
     * @param url
     */
    public static String ajaxGet(String url, String charset) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        return executeRequest(httpGet, charset);
    }

    /**
     * http的get请求,添加自定义头参数
     *
     * @param url
     * @param headers
     * @return
     */
    public static String ajaxGet(String url, List<Header> headers) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("X--With", "XMLHttpRequest");
        httpGet.setHeaders(headers.toArray(new Header[headers.size()]));
        return executeRequest(httpGet, "UTF-8");
    }

    /**
     * http的post请求，传递map格式参数
     */
    public static String post(String url, Map<String, String> dataMap, List<Header> headers) {
        return post(url, dataMap, "UTF-8", headers);
    }

    /**
     * http的post请求，传递map格式参数
     */
    public static String post(String url, Map<String, String> dataMap, String charset, List<Header> headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        try {
            if (dataMap != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, charset);
                formEntity.setContentEncoding(charset);
                httpPost.setEntity(formEntity);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return executeRequest(httpPost, charset);
    }


    /**
     * 执行一个无参数Http Post请求
     * @param url
     * @return
     */
    public static String post(String url){
        HttpPost httpPost = new HttpPost(url);
        return executeRequest(httpPost, "UTF-8");
    }


    /**
     * http的post请求，传递map格式参数
     * 添加自定义token
     * json
     */
    public static String postByToken(String url, String jsonStr, String token) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("token", token);
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
        httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
        httpPost.setEntity(new StringEntity(jsonStr, Charset.forName("UTF-8")));
        return executeRequest(httpPost, "UTF-8");
    }


    /**
     * http的post请求，传递map格式参数
     * 添加自定义的head头信息
     */
    public static String postByTokenSetHead(String url, String jsonStr, List<String> listKey, List<String> listValue) {
        HttpPost httpPOst = new HttpPost(url);
        for (int i = 0; i < listKey.size() && i < listValue.size(); i++) {
            httpPOst.addHeader("" + listKey.get(i) + "", listValue.get(i));
        }
        httpPOst.setEntity(new StringEntity(jsonStr, Charset.forName("utf-8")));
        return executeRequest(httpPOst, "utf-8");
    }


    /**
     * http的post请求，增加异步请求头参数，传递map格式参数
     */
    public static String ajaxPost(String url, Map<String, String> dataMap) {
        return ajaxPost(url, dataMap, "UTF-8");
    }

    /**
     * http的post请求，增加异步请求头参数，传递map格式参数
     */
    public static String ajaxPost(String url, Map<String, String> dataMap, String charset) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        try {
            if (dataMap != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, charset);
                formEntity.setContentEncoding(charset);
                httpPost.setEntity(formEntity);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return executeRequest(httpPost, charset);
    }

    /**
     * http的post请求，
     * 增加异步请求头参数
     * 传递map格式参数
     * 添加自定义消息头
     */
    public static String ajaxPost(String url, String jsonString, List<Header> headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        if (headers != null && headers.size() > 0) {
            httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        }
        StringEntity stringEntity = new StringEntity(jsonString, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        return executeRequest(httpPost, "UTF-8");
    }

    /**
     * http的post请求，增加异步请求头参数，传递json格式参数
     */
    public static String ajaxPostJson(String url, String jsonString) {
        return ajaxPostJson(url, jsonString, "UTF-8");
    }

    /**
     * http的post请求，增加异步请求头参数，传递json格式参数
     */
    public static String ajaxPostJson(String url, String jsonString, String charset) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
//		try {
        StringEntity stringEntity = new StringEntity(jsonString, charset);
        stringEntity.setContentEncoding(charset);
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
        return executeRequest(httpPost, charset);
    }


    /**
     * 执行一个http请求，传递HttpGet或HttpPost参数
     */
    public static String executeRequest(HttpUriRequest httpRequest) {
        return executeRequest(httpRequest, "UTF-8");
    }

    /**
     * 执行一个http请求，传递HttpGet或HttpPost参数
     */
    public static String executeRequest(HttpUriRequest httpRequest, String charset) {
        CloseableHttpClient httpclient;
        if ("https".equals(httpRequest.getURI().getScheme())) {
            httpclient = createSSLInsecureClient();
        } else {
            httpclient = HttpClients.createDefault();
        }
        String result = "";
        try {
            try {
                CloseableHttpResponse response = httpclient.execute(httpRequest);
                HttpEntity entity = null;
                try {
                    entity = response.getEntity();
                    result = EntityUtils.toString(entity, charset);
                } finally {
                    EntityUtils.consume(entity);
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //log.warn("发起请求:{},参数:{}，结果：{}", httpRequest.getURI(), httpRequest.getParams(), result);
        return result;
    }

    /**
     * 创建 SSL连接
     */
    public static CloseableHttpClient createSSLInsecureClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

}
