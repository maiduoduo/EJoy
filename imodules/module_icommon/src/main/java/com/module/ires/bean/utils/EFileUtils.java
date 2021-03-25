package com.module.ires.bean.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

import com.module.ires.bean.common.CommonConstant.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @ClassName: FileUtils
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/
 * @des:
 */
public class EFileUtils {
    public static String TAG =  "EFileUtils";
    /**
     * 读取文件字节流到字节数组
     * @param srcPath
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static byte[] fileToByteArray(String srcPath) {
        // 创建源和目的地
        File src = new File(srcPath);
        byte[] datas = null;
        // 选择流
        try (InputStream is = new BufferedInputStream(new FileInputStream(src));
             ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            // 操作（分段读取）
            byte[] flush = new byte[1024];// 缓冲容器
            int len = -1;// 接收长度
            while ((len = is.read(flush)) != -1) {
                baos.write(flush, 0, len);
            }
            baos.flush();
            // 获取数据
            datas = baos.toByteArray();
            return datas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //字节数组写入文件
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void byteArrayToFile(byte[] datas, String destPath) {
        // 创建源
        File dest = new File(destPath);
        // 选择流
        try (InputStream is = new ByteArrayInputStream(datas);
             OutputStream os = new BufferedOutputStream(new FileOutputStream(dest, false));) {
            // 操作（分段读取）
            byte[] flush = new byte[1024];// 缓冲容器
            int len = -1;// 接收长度
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //==================================读取文件数据到byte数组，并将读出的数据写入文件中强制刷新====================================================

    /**
     * 读取文件数据到byte数组典型做法
     * @param path
     * @return
     */
    private byte[] readFileToByteArray(String path) {
        FileInputStream in = null;
        File file = new File(path);
        if(!file.exists()) {
            Log.e(TAG,"File doesn't exist!");
            return null;
        }
        try {
            in = new FileInputStream(file);
            //判断FileInputStream中是否有内容
            long inSize = in.getChannel().size();
            if (inSize == 0) {
                Log.d(TAG,"The FileInputStream has no content!");
                return null;
            }
            //in.available() 表示要读取的文件中的数据长度
            byte[] buffer = new byte[in.available()];
            //将文件中的数据读到buffer中
            in.read(buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                return null;
            }
            //或IoUtils.closeQuietly(in);
        }
    }


    /**
     * 将读出来的数据写入文件中并强制刷新到文件中
     * @param path
     * @param buffer
     */
    private void writeAndFlush(String path, byte[] buffer) {
        try {
            //指定写到哪个路径中
            FileOutputStream out = new FileOutputStream(path);
            FileChannel fileChannel = out.getChannel();
            //将字节流写入文件中
            fileChannel.write(ByteBuffer.wrap(buffer));
            //强制刷新
            fileChannel.force(true);
            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //==================================读取文件数据到byte数组，并将读出的数据写入文件中强制刷新====================================================






    //========================================================================================
    private static File file = null;
    private static void createFile() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");// HH:mm:ss
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        try {
            file = new File(Environment.getExternalStorageDirectory(), "InputFile" + time + ".txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

        } catch (Exception e) {
            Log.e(TAG, "Error on write File:" + e);
        }
    }




      /**
      * 此方法为android程序写入sd文件文件，用到了android-annotation的支持库@
      *
       * @param mActivity
       * @param buffer   写入文件的内容
       * @param folder   保存文件的文件夹名称,如log；可为null，默认保存在sd卡根目录
       * @param fileName 文件名称，默认app_log.txt
       * @param append   是否追加写入，true为追加写入，false为重写文件
       * @param autoLine 针对追加模式，true为增加时换行，false为增加时不换行
       */
    public synchronized static void writeFileToRootSpace(final Activity mActivity, @NonNull final byte[] buffer, @Nullable final String folder,
                                                      @Nullable final String fileName, final boolean append, final boolean autoLine) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                String time = simpleDateFormat.format(date);


                boolean sdCardExist = Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED);
                String folderPath = "";
                if (sdCardExist) {
                    //TextUtils为android自带的帮助类
                    if (TextUtils.isEmpty(folder)) {
                        //如果folder为空，则直接保存在sd卡的根目录
                        folderPath = Environment.getExternalStorageDirectory()
                                + File.separator;
//                        File file = new File(mActivity.getFilesDir(),folder);
                    } else {
//                        folderPath = Environment.getExternalStorageDirectory()
//                                + File.separator + folder + File.separator;
                        folderPath = mActivity.getFilesDir() + "/"+folder;
                    }
                } else {
                    return;
                }

                File fileDir = new File(folderPath);
                if (!fileDir.exists()) {
                    if (!fileDir.mkdirs()) {
                        return;
                    }
                }
                File file;
                //判断文件名是否为空
                if (TextUtils.isEmpty(fileName)) {
                    file = new File(folderPath,"app_log.txt");
                } else {
                    file = new File(folderPath ,fileName);
                }
                RandomAccessFile raf = null;
                FileOutputStream out = null;
                try {
                    if (append) {
                        //如果为追加则在原来的基础上继续写文件
                        raf = new RandomAccessFile(file, "rw");
                        raf.seek(file.length());
                        raf.write(buffer);
                        if (autoLine) {
//                            raf.write("\n".getBytes());
                        }
                    } else {
                        //重写文件，覆盖掉原来的数据
                        out = new FileOutputStream(file);
                        out.write(buffer);
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (raf != null) {
                            raf.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    //========================================================================================
}
