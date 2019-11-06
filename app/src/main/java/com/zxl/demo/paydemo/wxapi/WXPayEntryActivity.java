package com.zxl.demo.paydemo.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
/**
 *微信支付后会调起该Activity
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    String WECHAT_APP_ID = "wx2d713e539bb99681";

    private IWXAPI wxapi;
    private int requestTimes = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wxapi = WXAPIFactory.createWXAPI(this, WECHAT_APP_ID);
        wxapi.handleIntent(getIntent(),this);
    }


    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode == 0) {//支付成功  -- 后台查询订单是否真的成功
            //延迟1.5秒查询支付结果
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    queryResult();
                }
            },1500);
        }else if (baseResp.errCode == -1) {//支付失败
            Toast.makeText(this,"支付失败",Toast.LENGTH_SHORT).show();
            finish();
        }else {//取消
            Toast.makeText(this,"支付取消",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //轮询请求3次
    private void queryResult() {
//        HttpUtils.getInstance().checkOrder(App.nOrderNo).enqueue(new Callback<ServerResponse>(){
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                super.onResponse(call, response);
//                if(response.body().getnRes() == 1){//支付成功
//                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                    Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(WXPayEntryActivity.this, PayResultActivity.class);
//                    intent.putExtra("nMoney", App.nMoney);
//                    startActivity(intent);
//                    Message msg1 = Message.obtain();
//                    msg1.what = Constans.ACTIVITY_FINISH;
//                    EventBus.getDefault().post(msg1);
//                    finish();
//                }else {//失败
//                    if(requestTimes <=3 ){
//                        requestTimes++;
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                queryResult();
//                            }
//                        },1000);
//                    }else {
//                        ToastUtils.showToast(getApplicationContext(),response.body().getVcRes());
//                        finish();
//                    }
//                }
//            }
//        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestTimes = 0;
    }
}
