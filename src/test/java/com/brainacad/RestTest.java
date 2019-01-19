package com.brainacad;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class RestTest
{
    /**
     * Rigorous Test :-)
     * String url = "http://www.google.com/search?q=httpClient";
     */
    @Test
    public void checkResponseStatusCode() throws IOException {
        Map<String, String> headers=new HashMap<>();
        headers.put("User-Agent", "User-Agent");

        HttpResponse response = HttpClientHelper.get("http://www.google.com/search","q=Main%20Academy", headers);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test
    public void checkResponseBodyNotNull() throws IOException {
        //Создаём переменую headers типа Map
        Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("User-Agent", "User-Agent");

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get("http://www.google.com/search","q=Main%20Academy", headers);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }
}
