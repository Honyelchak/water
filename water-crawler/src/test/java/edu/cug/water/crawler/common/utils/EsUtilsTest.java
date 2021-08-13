package edu.cug.water.crawler.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EsUtilsTest {


    @Autowired
    private EsUtils esUtils;

    @Test
    void insert() {
        try {
            esUtils.insert();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}