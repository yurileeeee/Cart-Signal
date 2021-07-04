package com.example.samplesenti;

import java.util.HashMap;
import java.util.Map;

public class Post {
    String minMoney;
    String maxUser;
    String moreInfo;
    String endDate;

    Post(){}

    Post(String minMoney, String maxUser, String moreInfo, String endDate) {
        this.minMoney = minMoney;
        this.maxUser = maxUser;
        this.moreInfo = moreInfo;
        this.endDate = endDate;

    }

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }

    public String getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(String maxUser) {
        this.maxUser = maxUser;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
