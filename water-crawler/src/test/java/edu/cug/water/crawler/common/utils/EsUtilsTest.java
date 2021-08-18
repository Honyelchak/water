package edu.cug.water.crawler.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class EsUtilsTest {


    @Autowired
    private EsUtils esUtils;

    @Test
    void addDateDocument() {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("name", "dere");
            map.put("pwd", "ewrfet");
            String s = esUtils.addDateDocument("national", "national", map);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addDocuments() {
        List list = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 9; i ++)
        {
            Map<String, String> map = new HashMap<>();
            map.put("name", "dere");
            map.put("pwd", "ewrfet");
            list.add(map);
        }

        String s = esUtils.addDocuments("national", "national", list);
        System.out.println(s);
    }
}