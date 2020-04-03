package com.ventilator.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ventilator.Tools.CopyFiles;
import com.ventilator.Tools.UsbUtils;
import com.xzkydz.function.menu.setting.SettingActivity;

import java.io.File;
import java.text.SimpleDateFormat;

import static com.tencent.bugly.beta.Beta.checkUpgrade;

public class MySettingActivity extends SettingActivity {
    private UsbUtils usbUtils;
    private File srcFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usbUtils = new UsbUtils(MySettingActivity.this);
        usbUtils.openUsbDevice();
    }

    @Override
    public Class<?> setConfigActivity() {
        return MyConfigActivity.class;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //显示数据导出按钮
        this.showDataBtn(true);
    }

    /**
     * 检查升级
     */
    @Override
    public void checkUpdate() {
        super.checkUpdate();
        checkUpgrade();
    }

    /**
     * 导出测试数据事件
     */
    @Override
    public void dataOut() {
        super.dataOut();
       // Toast.makeText(this, "导出测试数据!!!!", Toast.LENGTH_SHORT).show();
        //初始化
       // usbUtils = new UsbUtils(this);
        ShowTrans();
    }

    private void ShowTrans() {
        new CopyFiles().copy(this);
        /*
          应用文件目录,type为空字符串时获取
        */
        String destpath = this.getExternalFilesDir("") + "";
        Log.i("copy", "destpath: " + destpath);
        File f = new File(destpath);

        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
                Log.i("copy", "files=== : " + files[i].getName());
            }
            srcFile = files[0];
            if (srcFile.exists()) {
                copyLocalFile(srcFile);
            } else {
                Toast.makeText(this, "导出文件失败，数据库不存在", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void copyLocalFile(final File file) {
//        Toast.makeText(this, "fileName===" + file.getName(), Toast.LENGTH_SHORT).show();
        //复制到本地的文件路径
        new Thread(new Runnable() {
            @Override
            public void run() {
                //复制结果
                final boolean result = usbUtils.saveSDFileToUsb(file, usbUtils.getCurrentFolder());
                //主线程更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result) {
                            //    openUsbFile(usbHelper.getCurrentFolder());
                            Toast.makeText(MySettingActivity.this, "导出成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MySettingActivity.this, "导出失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

}
