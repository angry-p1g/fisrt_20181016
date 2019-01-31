package com.gq.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import javax.net.ssl.SSLContext;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * 模拟http请求工具类
 *
 * @author matao
 * @date 2016年7月14日
 */
public class HttpClientUtil {

    private static CloseableHttpClient httpClient = createSSLClientDefault();

    public static String getUrlParamsFromMap(Map<String, String> map) {
        try {
            if (null != map) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if(StringUtils.isBlank(entry.getValue())){
                        continue;
                    }
                    stringBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
                }
                String content = stringBuilder.toString();
                if (content.endsWith("&")) {
                    content = StringUtils.substringBeforeLast(content, "&");
                }
                return content;
            }
        } catch (Exception e) {
//            logger.error("转换参数异常",e);
        }
        return null;
    }

    /**
     * 模拟https发送post请求(发送内容为json)
     *
     * @author matao
     * @date 2016年7月21日
     */
    public static String sendPost(String url, String params,String contentType) {
        HttpPost post = new HttpPost(url);
        return sendPost1(url, params, post,contentType);
    }

    public static void sendPost(String url, String params, int timeout,String contentType) {
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();// 设置请求和传输超时时间
        post.setConfig(requestConfig);
        try {
            // 设置请求实体
            StringEntity reqEntity = new StringEntity(params, "utf-8");
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType(contentType);
            post.setEntity(reqEntity);
            // 发送请求，返回响应对象
            httpClient.execute(post);

        } catch (Exception e) {
        }
    }


    public static String sendPost1(String url, String params, HttpPost post,String contentType) {
        // 响应内容
        String responseContent = "";
        try {
            // 设置请求实体
            StringEntity reqEntity = new StringEntity(params, "utf-8");
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType(contentType);
            post.setEntity(reqEntity);

            // 发送请求，返回响应对象
            HttpResponse response = httpClient.execute(post);

            if (response == null) {
                return responseContent;
            }

            // 获得响应实体
            HttpEntity resEntity = response.getEntity();
            // 获得状态码
            int statusCode = response.getStatusLine() == null ? 0 : response.getStatusLine().getStatusCode();
            // 获取响应内容
            if (resEntity != null && statusCode == HttpStatus.SC_OK) {
                responseContent = EntityUtils.toString(resEntity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    /**
     * 创建HttpClient对象（用于发送https请求）
     *
     * @author matao
     * @date 2016年7月14日
     */
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            TrustStrategy trustStrategy = new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true;
                }
            };

            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, trustStrategy).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return HttpClients.createDefault();
    }
}

