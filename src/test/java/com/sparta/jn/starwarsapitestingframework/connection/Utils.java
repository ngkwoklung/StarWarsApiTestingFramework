package com.sparta.jn.starwarsapitestingframework.connection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification requestSpec;

    public RequestSpecification requestSpecification() {
        if(requestSpec == null) {
            PrintStream log = null;
            try {
                log = new PrintStream(new FileOutputStream("logging.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseURL"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpec;
        }
        return requestSpec;
        }

    private String getGlobalValue(String key) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kwokl\\Sparta Global\\" +
                    "StarWarsApiTestingFramework\\src\\test\\resources\\global.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
