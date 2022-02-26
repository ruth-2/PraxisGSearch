package com.praxis.gsearch.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Component
public class ApiConstants implements CommandLineRunner {
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    public static final int HTTP_TIMEOUT = 3 * 600000;
    public static final int SEARCH_ITEM_COUNT = 10;         // valid range: 1 - 10

    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    @Value("${GOOGLE_SEARCH_ENGINE_ID}")
    private String GOOGLE_SEARCH_ENGINE_ID;

    public String getGoogleApiKey() {
        return this.GOOGLE_API_KEY;
    }

    public String getGoogleSearchId() {
        return this.GOOGLE_SEARCH_ENGINE_ID;
    }

    @Override
    public void run(String... args) {
        context.scan("com.praxis.gsearch.constant");
        context.refresh();
    }
}
