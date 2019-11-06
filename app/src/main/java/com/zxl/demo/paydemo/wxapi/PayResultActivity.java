package com.zxl.demo.paydemo.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.zxl.demo.paydemo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by l on 2018/8/22.
 */
public class PayResultActivity extends AppCompatActivity {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_spanString)
    TextView tvSpanString;
    @BindView(R.id.iv_enter)
    ImageView ivEnter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payresult);
        ButterKnife.bind(this);
        String nMoney = getIntent().getStringExtra("nMoney");
        tvMoney.setText(nMoney);

    }

    @OnClick(R.id.iv_enter)
    public void onViewClicked() {
        finish();
    }
}
