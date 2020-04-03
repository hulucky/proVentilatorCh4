package com.ventilator.main;

import android.widget.Toast;

import com.xzkydz.function.menu.setting.ConfigActivity;
import com.xzkydz.function.net.Submit;

public class MyConfigActivity extends ConfigActivity {
    private Submit sendToServer;

    @Override
    public void testLink(String urlStr) {
        super.testLink(urlStr);
        if (sendToServer==null){
            sendToServer = new Submit();
            sendToServer.setOnBackListener(new Submit.OnBackListener() {
                @Override
                public void getResponse(String response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MyConfigActivity.this, "连接服务器成功!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void getFailure(String failure) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MyConfigActivity.this, "连接服务器失败!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        sendToServer.send(null,new String());
    }
}
