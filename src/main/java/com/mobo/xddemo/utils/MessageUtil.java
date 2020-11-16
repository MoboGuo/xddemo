package com.mobo.xddemo.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

/**
 * 短信发送工具类
 *
 * @author Mobo
 * @create 2020-11-12-15:34
 */
@Configuration
@PropertySource({"classpath:application.yml"})
public class MessageUtil {

    @Value("${message.submail.send}")
    private String API_SEND;
    @Value("${message.submail.xsend}")
    private String API_XSEND;
    @Value("${message.submail.appid}")
    private String API_APPID;
    @Value("${message.submail.signType}")
    private String API_SIGNTYPE;
    @Value("${message.submail.appkey}")
    private String API_APPKEY;
    private CloseableHttpClient closeableHttpClient = null;

    public MessageUtil(){
        closeableHttpClient = HttpClientBuilder.create().build();
    }
    /**
     * 发送
     * @param data
     * @return
     * @throws Exception
     */
    public String request(Map<String, Object> data) throws Exception {
        HttpPost httppost = new HttpPost(API_XSEND);
        httppost.addHeader("charset", "UTF-8");
        httppost.setEntity(build(data));
        HttpResponse response = this.closeableHttpClient.execute(httppost);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
            return jsonStr;
        } else {
            this.closeableHttpClient.close();
            return null;
        }
    }

    private HttpEntity build(Map<String, Object> data) throws IOException, NoSuchAlgorithmException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("appid", API_APPID);
        builder.addTextBody("timestamp", this.getTimestamp());
        builder.addTextBody("sign_type", API_SIGNTYPE);
        data.put("appid", API_APPID);
        data.put("timestamp", this.getTimestamp());
        data.put("sign_type", API_SIGNTYPE);
        ContentType contentType = ContentType.create("text/plain", "UTF-8");
        builder.addTextBody("signature", createSignature(RequestEncoder.formatRequest(data)), contentType);
        Iterator var5 = data.entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var5.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                builder.addTextBody(key, String.valueOf(value), contentType);
            } else if (value instanceof File) {
                builder.addBinaryBody(key, (File)value);
            }
        }

        return builder.build();
    }

    private String getTimestamp() throws ParseException, IOException {
        HttpGet httpget = new HttpGet("http://api.submail.cn/service/timestamp.json");
        HttpResponse response = this.closeableHttpClient.execute(httpget);
        HttpEntity httpEntity = response.getEntity();
        String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
        if (jsonStr != null) {
            JSONObject json = JSONObject.fromObject(jsonStr);
            return json.getString("timestamp");
        } else {
            this.closeableHttpClient.close();
            return null;
        }
    }

    private String  createSignature(String data) throws NoSuchAlgorithmException {
        return "normal".equals(API_SIGNTYPE) ? API_APPKEY : this.buildSignature(data);
    }

    private String buildSignature(String data) throws NoSuchAlgorithmException {
        String app = API_APPID;
        String appKey = API_APPKEY;
        String jointData = app + appKey + data + app + appKey;
        if ("md5".equals(API_SIGNTYPE)) {
            return RequestEncoder.encode("MD5", jointData);
        } else {
            return "sha1".equals(API_SIGNTYPE) ? RequestEncoder.encode("SHA1", jointData) : null;
        }
    }
}