package com.alanwalker.getuuid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alanwalker.getuuid.util.Installation;
import com.alanwalker.getuuid.util.UniquePsuedoID;
import com.alanwalker.getuuid.util.UniversalID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button installtionBtn, uniquePsuedoBtn, universalBtn;
    private TextView installtionText, uniquePsuedoText, universalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        installtionBtn = (Button) findViewById(R.id.btn_installtion);
        installtionBtn.setOnClickListener(this);
        installtionText = (TextView) findViewById(R.id.text_installtion);
        uniquePsuedoBtn = (Button) findViewById(R.id.btn_unique_psuedo);
        uniquePsuedoBtn.setOnClickListener(this);
        uniquePsuedoText = (TextView) findViewById(R.id.text_unique_psuedo);
        universalBtn = (Button) findViewById(R.id.btn_universal_id);
        universalBtn.setOnClickListener(this);
        universalText = (TextView) findViewById(R.id.text_universal_id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 在程序安装后第一次运行时生成一个ID，该方式和设备唯一标识不一样，不同的应用程序会产生不同的ID，
             * 同一个程序重新安装也会不同。所以这不是设备的唯一ID，但是可以保证每个用户的ID是不同的。
             * 可以说是用来标识每一份应用程序的唯一ID（即Installtion ID），可以用来跟踪应用的安装数量等。
             *
             * 其实就是UUID,用来跟踪应用的安装数量
             */
            case R.id.btn_installtion:
                installtionText.setText("Installtion ID:" + Installation.id(MainActivity.this));
                break;

            /**
             * 通过读取设备的ROM版本号、厂商名、CPU型号和其他硬件信息来组合出一串15位的号码
             * 和设备硬件序列号（Devices without telephony are required to report a unique device ID here; some phones may do so also.）
             * 作为种子生成UUID
             *
             * 一串15位的号码（批量生产的设备每项信息基本相同，所以这一段相同的可能性特别高）
             * 硬件序列，在一些没有电话功能的设备会提供，某些手机上也可能提供（所以就是经常会返回Unknown）
             */
            case R.id.btn_unique_psuedo:
                uniquePsuedoText.setText("UniquePsuedo ID:" + UniquePsuedoID.getUniquePsuedoID());
                break;

            /**
             * 首先通过读取Android_id,作为UUID的种子。若得到Android_Id等于9774d56d682e549c 或者 发生错误
             * 则 random 一个 UUID 作为备用方案，最后把得到的UUID同时存入内部存储和外部存储。
             * 下次使用UUID的时候优先从外部存储读取，再从背部存储读取，最后在重新生成，尽可能的保证其不变性
             */
            case R.id.btn_universal_id:
                universalText.setText("Universal ID:" + UniversalID.getUniversalID(MainActivity.this));
                break;

        }
    }
}
