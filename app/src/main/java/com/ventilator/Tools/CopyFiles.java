package com.ventilator.Tools;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyFiles {
    public void copy(Context context) {
        String UrlSource;
        String srcDir=context.getExternalFilesDir("") +"";
        deleteDir(srcDir);
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
            String generate_time= sdf.format(d).toString();
            Log.i("FileUtils","time="+generate_time);
            UrlSource = context.getExternalFilesDir("") +"/database"+generate_time+".db";
            Log.e("FileUtils","url=="+UrlSource);
            File file1=new File("/data/data/com.example.administrator.ventilator/databases/database");
            File file2=new File(UrlSource);
            if(!file2.exists())
                file2.createNewFile();
            copyFile(file1,file2);
            //MyApp.isReback=true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          //  MyApp.isReback=false;
        }
    }
    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
    /**
     * 复制文件夹或文件夹
     */

    public static void CopyWholeDirectiory(String url1, String url2)
            throws IOException {
        // 创建目标文件夹
        (new File(url2)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(url1)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 复制文件
                Log.i("FileUtils","copy file"+url2+file[i].getName());
                copyFile(file[i], new File(url2 + file[i].getName()));
            }
            if (file[i].isDirectory() && i != 3) {
                Log.i("FileUtils","copy dir");
                // 复制目录
                String sourceDir = url1 + File.separator + file[i].getName();
                String targetDir = url2 + File.separator + file[i].getName();

                copyDirectiory(sourceDir, targetDir);
            }
        }
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        Log.i("FileUtils","final copy=="+targetFile.getAbsolutePath());

        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        // 关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                Log.i("FileUtils","copyFile"+targetFile.getAbsolutePath());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();

                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
                Log.i("FileUtils","copyDirectiory"+dir2);
            }
        }
    }
}
