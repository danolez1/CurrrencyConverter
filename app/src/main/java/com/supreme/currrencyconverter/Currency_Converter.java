package com.supreme.currrencyconverter;

/**
 * Created by supreme on 6/24/17.
 */

public class Currency_Converter {

    private String mCountryFlag;
    private String mCurrencyType;
    private String mETH;
    private String mBTC;
    private int mCountryImage;

    public Currency_Converter(String mCountryFlag, String mCurrencyType, String mETH, String mBTC, int mCountryImage) {
        this.mCountryFlag = mCountryFlag;
        this.mCurrencyType = mCurrencyType;
        this.mETH = mETH;
        this.mBTC = mBTC;
        this.mCountryImage = mCountryImage;
    }

    public String getmCountryFlag() {
        return mCountryFlag;
    }

    public String getmCurrencyType() {
        return mCurrencyType;
    }

    public String getmETH() {
        return mETH;
    }

    public String getmBTC() {
        return mBTC;
    }

    public int getmCountryImage() {
        return mCountryImage;
    }
}