package com.hyd.gwinterfaceserver.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyd.system.exceptclass.ResultException;
import com.hyd.system.exceptclass.SqlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Http 访问工具类
 */
public class HttpUtils{

    private static final Logger logger = LogManager.getLogger("gw");

    /**
     * 证书信任管理器（用于https请求）
     */
    public static class MyX509TrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    /**
     * GET请求 默认是 utf-8 编码
     *
     * @param url
     * @param params
     * @param
     * @return
     */
    public static <T> T get(String url, Map<String,String> params,Class<T> tClass) {
        String result = proxyHttpRequest(url+"?"+getRequestBody(params),null,"GET", Charset.forName("utf-8"));
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<T>() {}.getType());
    }

    /**
     * GET请求
     *
     * @param url 请求URL
     * @return
     */
    public static String get(String url, String params,String method, Charset charset) {
        String result = "";
        InputStream in = null;

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();
//			// 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
//			// 定义 BufferedReader输入流来读取URL的响应
//			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			String line;
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}

//			in = conn.getInputStream();
//			result = HttpsURLConnection.(in);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        return result;
    }

    /**
     * POST请求 默认是 utf-8 编码
     *
     * @param url
     * @param params
     * @return
     */
    public static <T> T post(String url, String params,Class<T> tClass) {
        String result = proxyHttpRequest(url, params, "POST", Charset.forName("utf-8"));
        Gson gson = new Gson();
        return  gson.fromJson(result, new TypeToken<T>() {}.getType());
    }

    /**
     *
     * @param url   请求URL
     * @param param 请求参数，请求参数格式 name1=value1&name2=value2  {name1:"value1", name2: "value2"}
     * @return
     */
    public static String proxyHttpRequest(String url, String param,String method, Charset charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        List list = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            conn.setDoInput(true);

			if (!(param == null || param.trim().equals(""))) {
				out = new PrintWriter(conn.getOutputStream());
				try{
					// 发送请求参数
					out.print(param);
				} finally {
						out.flush();
				}
			}

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new ResultException(responseCode + ":"
                        + getReturn(conn.getErrorStream(),
                        charset));
            }
            result = getReturn(conn.getInputStream(), charset);
        } catch (Exception e) {
            e.printStackTrace();
            //记录异常日志
            //抛出 国网调用失败 提示
            logger.error("**************************");
            logger.error("请求国网接口失败");
            logger.error("Url："+url);
            logger.error("Data："+param);
            logger.error("error："+e.getStackTrace());
            logger.error("**************************");
            throw new SqlException("请求国网失败"+e.getStackTrace());

        }
        return result;
    }


    /*请求url获取返回的内容*/
    public static String getReturn(InputStream input, Charset encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            result.append(temp);
        }
        return result.toString();
    }

	/**
	 * 将参数化为 body
	 * @param params
	 * @return
	 */
	public static String getRequestBody(Map<String, String> params) {
		StringBuilder body = new StringBuilder();
		Iterator<String> iteratorHeader = params.keySet().iterator();
		while (iteratorHeader.hasNext()) {
			String key = iteratorHeader.next();
			String value = params.get(key);
			body.append(key + "=" + value + "&");
		}
		if (body.length() == 0) {
			return "";
		}
		return body.substring(0, body.length() - 1);
	}


}