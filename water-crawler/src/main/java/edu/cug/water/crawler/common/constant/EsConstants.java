package edu.cug.water.crawler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EsConstants {

    @AllArgsConstructor
    public enum Config {
        ES_NAME("water");
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public enum RequestMethod {
        GET("GET"),
        POST("POST"),
        PUT("PUT");

        private String value;
    }

    @AllArgsConstructor
    public enum IndexConstant {

        WATER("water");
        private String value;
    }

    @AllArgsConstructor
    public enum TypeConstant {
        NATIONAL_SURFACE_WATER_QUALITY("NATIONAL_SURFACE_WATER_QUALITY");
        private String value;
    }
}
