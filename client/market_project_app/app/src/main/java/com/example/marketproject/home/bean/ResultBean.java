package com.example.marketproject.home.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultBean {

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "result")
    private ResultDTO result;

    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        @JSONField(name = "act_info")
        private List<ActInfoDTO> actInfo;
        @JSONField(name = "banner_info")
        private List<BannerInfoDTO> bannerInfo;
        @JSONField(name = "channel_info")
        private List<ChannelInfoDTO> channelInfo;
        @JSONField(name = "hot_info")
        private List<HotInfoDTO> hotInfo;
        @JSONField(name = "recommend_info")
        private List<RecommendInfoDTO> recommendInfo;
        @JSONField(name = "seckill_info")
        private SeckillInfoDTO seckillInfo;

        @NoArgsConstructor
        @Data
        public static class SeckillInfoDTO {
            @JSONField(name = "end_time")
            private String endTime;
            @JSONField(name = "list")
            private List<ListDTO> list;
            @JSONField(name = "start_time")
            private String startTime;

            @NoArgsConstructor
            @Data
            public static class ListDTO {
                @JSONField(name = "cover_price")
                private String coverPrice;
                @JSONField(name = "figure")
                private String figure;
                @JSONField(name = "name")
                private String name;
                @JSONField(name = "origin_price")
                private String originPrice;
                @JSONField(name = "product_id")
                private String productId;
            }
        }

        @NoArgsConstructor
        @Data
        public static class ActInfoDTO {
            @JSONField(name = "icon_url")
            private String iconUrl;
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "url")
            private String url;
        }

        @NoArgsConstructor
        @Data
        public static class BannerInfoDTO {
            @JSONField(name = "image")
            private String image;
            @JSONField(name = "option")
            private Integer option;
            @JSONField(name = "type")
            private Integer type;
            @JSONField(name = "value")
            private ValueDTO value;

            @NoArgsConstructor
            @Data
            public static class ValueDTO {
                @JSONField(name = "url")
                private String url;
            }
        }

        @NoArgsConstructor
        @Data
        public static class ChannelInfoDTO {
            @JSONField(name = "channel_name")
            private String channelName;
            @JSONField(name = "image")
            private String image;
            @JSONField(name = "option")
            private Integer option;
            @JSONField(name = "type")
            private Integer type;
            @JSONField(name = "value")
            private ValueDTO value;

            @NoArgsConstructor
            @Data
            public static class ValueDTO {
                @JSONField(name = "channel_id")
                private String channelId;
            }
        }

        @NoArgsConstructor
        @Data
        public static class HotInfoDTO {
            @JSONField(name = "cover_price")
            private String coverPrice;
            @JSONField(name = "figure")
            private String figure;
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "product_id")
            private String productId;
        }

        @NoArgsConstructor
        @Data
        public static class RecommendInfoDTO {
            @JSONField(name = "cover_price")
            private String coverPrice;
            @JSONField(name = "figure")
            private String figure;
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "product_id")
            private String productId;
        }
    }
}
