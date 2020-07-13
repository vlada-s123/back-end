package com.brainacad;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class RestTest{

    private static final String URL="https://reqres.in/";

    @Test//GET метод
    public void checkGetResponseStatusCode() throws IOException {
        String endpoint="/api/users";

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2");

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals( 200, statusCode,"Response status code should be 200");
    }

    @Test//GET метод
    public void checkGetResponseBodyNotNull() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2");

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    @Test//POST метод
    public void checkPostResponseStatusCode() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals( 201, statusCode,"Response status code should be 201");
    }

    @Test//POST метод
    public void checkPostResponseBodyNotNull() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
        Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("Content-Type", "application/json");

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody, headers);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    //TODO: напишите по тесткейсу на каждый вариант запроса на сайте https://reqres.in
    //TODO: в тескейсах проверьте Result Code и несколько параметров из JSON ответа (если он есть)

}
