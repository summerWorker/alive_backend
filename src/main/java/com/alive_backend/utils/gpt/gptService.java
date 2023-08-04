package com.alive_backend.utils.gpt;
import com.alibaba.fastjson2.JSON;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class gptService {
    private static final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        //提问的问题
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(Map.of(
                "input", "你是谁？"
        )), MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://cps.ytnetwork.club/ai/tools/chat")
                .post(requestBody)
                .headers(Headers.of(Map.of("Content-Type", "application/json; charset=utf-8")))
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
//        urlBuilder.addQueryParameter("appId", appId);
//        urlBuilder.addQueryParameter("secret", secret);

        String requestUrl = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
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

}
