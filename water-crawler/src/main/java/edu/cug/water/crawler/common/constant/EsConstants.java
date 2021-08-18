package edu.cug.water.crawler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EsConstants {

    @AllArgsConstructor
    @Getter
    public enum Config {
        ES_NAME("water");
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public enum MapperConstants {
        GATEWAY_MAPPER("gateway.xml"),
        MAPPER_DIR("esmapper");

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
    @Getter
    public enum TypeConstant {
        IMPORTANT_OF_NATIONAL_WATER_AND_RAIN_INFORMATION("important_of_national_water_and_rain_information"),
        RESERVOIR_OF_NATIONAL_WATER_AND_RAIN_INFORMATION("reservoir_of_national_water_and_rain_information"),
        RIVERS_OF_NATIONAL_WATER_AND_RAIN_INFORMATION("rivers_of_national_water_and_rain_information"),
        NATIONAL_SURFACE_WATER_QUALITY("national_surface_water_quality"),
        PEARL_WATER_("pearl_water_"),
        YELLOW_RIVER("yellow_river"),
        SONG_LIAO_WATER("song_liao_water"),
        TAI_HU_WATER("tai_hu_water"),
        CHANGE_JIANG_COMMITTEE("change_jiang_committee"),
        HAIHE_RIVER_COMMITTEE("haihe_river_committee");
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public enum NationalSurfaceWaterQualityConstants {
        DEAL_CRO_SECTION_OF_RIVER(1);

        private int value;
    }
}
