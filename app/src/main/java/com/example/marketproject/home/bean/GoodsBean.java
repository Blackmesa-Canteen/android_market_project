package com.example.marketproject.home.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsBean implements Serializable {
    private String cover_price;
    private String figure;
    private String name;
    private String product_id;


}
