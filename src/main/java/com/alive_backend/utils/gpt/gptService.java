package com.alive_backend.utils.gpt;
import com.alibaba.fastjson2.JSON;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.util.UUID;

public class gptService {
    private static final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        // 认证信息
        String appId = "20230804101433A057";
        String secret = "K8075013458f9613302379e9dc45768";
        String token = getToken(appId, secret);
        System.out.println("token:" + token);
        OkHttpClient client = new OkHttpClient();

        // 请求
        String question = "你是谁？";
        String roleCode = "gpt";
        String replyId = UUID.randomUUID().toString();

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("question", question);
        requestBodyMap.put("roleCode", roleCode);
        requestBodyMap.put("replyId", replyId);

        RequestBody requestBody = RequestBody.create(JSON.toJSONString(requestBodyMap),
                MediaType.parse("application/json; charset=utf-8"));


        Request request = new Request.Builder()
                .url("http://api-openai.dtgarden.com/question")
                .post(requestBody)
                .header("token", token)
                .header("appId", appId)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                throw new RuntimeException("API异常:" + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    //接收响应流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("接收处理内容:" + line);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
    }
    public static String getToken(String appId, String secret) throws IOException {
        String baseUrl = "http://api-openai.dtgarden.com/user/token";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        String param = "appId=" + appId + "&secret=" + md5Hash(appId + secret);
        String url = urlBuilder + "?" + param;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                System.out.println("获取token响应内容:" + responseData);
                JSONObject json = new JSONObject(responseData);
                if (json.getBoolean("success")) {
                    JSONObject data = json.getJSONObject("data");
                    return data.getString("token");
                } else {
                    throw new RuntimeException("获取token失败: " + json.getString("message"));
                }
            } else {
                throw new RuntimeException("网络请求失败: " + response.code());
            }
        }
    }
    private static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }

}
