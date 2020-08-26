package com.white.bird.common.net.bean;

/**
 * author : ZYK
 * createTime   : 2020/7/16 10:57
 * function   :
 */
public class Token {


    /**
     * sysDate : 1457858035524
     * token : 3ds3iWMOcUuTMCcAVW5rMKGNIucGyq8754TVOxQm0JvHrU0cbpqLQjkxGS2V4J/fl7Oq9xDF1hZkCRfIbopMRfksONxtZOpAarQVBZ6pBOALkLRb1Ek3ji4MnDW9+5P8
     * tokenExpire : 1458117235524
     */

    private String sysDate;
    private String token;
    private String tokenExpire;

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenExpire(String tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public String getSysDate() {
        return sysDate;
    }

    public String getToken() {
        return token;
    }

    public String getTokenExpire() {
        return tokenExpire;
    }
}
