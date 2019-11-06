package com.zxl.demo.paydemo;

import android.app.Application;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MyApplication extends Application {
    public static String nOrderNo;
    public static IWXAPI msgApi;
    @Override
    public void onCreate() {
        super.onCreate();
        msgApi = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        msgApi.registerApp("wx2d713e539bb99681");
    }
}
