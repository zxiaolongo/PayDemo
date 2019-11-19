package com.zxl.demo.paydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createOrder();
    }
    //notice 1 交由后台生成微信订单
    private void createOrder() {
        //1 交由后台生成微信订单
//        HttpUtils.getInstance().weiXinPay(String.valueOf(item.mFee), "维修费用", item.vcOrderNo).enqueue(new com.zjxnkj.mobile.wisdompark.net.callback.Callback<WeChatPayInfo>() {
//            @Override
//            public void onResponse(Call<WeChatPayInfo> call, Response<WeChatPayInfo> response) {
//                super.onResponse(call, response);
//                DialogShow.closeDialog();
//                if (!issuccess) {
//                    return;
//                }
//                if (response.body().getnRes() != 1) {
//                    return;
//                }
//                wxPay(response.body().getObject());
//            }
//        });
    }

    //notice 2 调起微信支付   3在 WXPayEntryActivity
    private void wxPay(final WeChatPayInfo.ObjectBean objectBean) {
        MyApplication.nOrderNo = objectBean.vcOrderNo;
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayReq request = new PayReq();
                request.appId = objectBean.appid;
                request.partnerId = objectBean.partnerid;
                request.prepayId = objectBean.prepayid;
                request.packageValue = objectBean.packageX;
                request.nonceStr = objectBean.noncestr;
                request.timeStamp = objectBean.timestamp;
                request.sign = objectBean.sign;
                boolean b = request.checkArgs();
                MyApplication.msgApi.sendReq(request);
            }
        }).start();
    }
    public class WeChatPayInfo{
        private int nRes;
        private String vcRes;
        private int count;
        private ObjectBean object;
        private List<ObjectBean> rows;
        /**
         * rows : null
         * object : {"appid":"wx2d713e539bb99681","noncestr":"4127","package":"Sign=WXPay","partnerid":"1512088551","prepayid":"wx221414437431491399b749fa3260926603","sign":"A81CFC16D9CCFF4D7724D996D95FF16E","timestamp":"1534918482","vcOrderNo":"0118082214144212100"}
         */


        public  class ObjectBean {
            /**
             * appid : wx2d713e539bb99681
             * noncestr : 4127
             * package : Sign=WXPay
             * partnerid : 1512088551
             * prepayid : wx221414437431491399b749fa3260926603
             * sign : A81CFC16D9CCFF4D7724D996D95FF16E
             * timestamp : 1534918482
             * vcOrderNo : 0118082214144212100
             */

            public String appid;
            public String noncestr;
//            @SerializedName("package")//gson 依赖才有
            public String packageX;
            public String partnerid;
            public String prepayid;
            public String sign;
            public String timestamp;
            public String vcOrderNo;
        }
    }
}
