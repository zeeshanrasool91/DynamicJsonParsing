package com.example.dynamicjsonparsing.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class GsonFoo {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Map<String, String>>>() {
        }.getType();
        Map<String, Map<String, String>> map = gson.fromJson(new FileReader("testjson"), mapType);
        System.out.println(map);

        // Get the count...
        int count = Integer.parseInt(map.get("0").get("count"));

        // Get each numbered entry...
        for (int i = 1; i <= count; i++) {
            System.out.println("Entry " + i + ":");
            Map<String, String> numberedEntry = map.get(String.valueOf(i));
            for (String key : numberedEntry.keySet())
                System.out.printf("key=%s, value=%s\n", key, numberedEntry.get(key));
        }

        // Get the routes...
        Map<String, String> routes = map.get("routes");

        // Get each route...
        System.out.println("Routes:");
        for (String key : routes.keySet())
            System.out.printf("key=%s, value=%s\n", key, routes.get(key));
    }
}
