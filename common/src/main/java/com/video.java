package com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频音频处理相关
 */
public class video {


    /**
     * 网易云音乐缓冲文件解码
     * .uc!转为MP3
     * 将手机里网易云音乐目录下的缓冲文件(以.uc!结尾)放到一个自定义目录下 此处为D盘music
     * 直接使用时直接使用main方法调用此方法
     * 2019/06/03
     */
    public void wyCould() {
        //测试
        String filepath = "D:\\music";
        File file = new File(filepath);
        File[] fileList = file.listFiles();
        List<File> lists = new ArrayList<File>();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                lists.add(fileList[i]);
            }
        }
        //n为文件名
        Integer n = 1;
        for (File file1 : lists) {
            String fileName = file1.getName();
            if (fileName.endsWith("uc!")) {
                String name = String.valueOf(n);
                try {
                    File outFile = new File("D:\\music\\" + name + ".mp3");
                    DataInputStream dis = new DataInputStream(new FileInputStream(file1));
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(outFile));
                    byte[] by = new byte[1000];
                    int len;
                    while ((len = dis.read(by)) != -1) {
                        for (int i = 0; i < len; i++) {
                            by[i] ^= 0xa3;
                        }
                        dos.write(by, 0, len);
                    }
                    dis.close();
                    dos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException ioe) {
                    System.err.println(ioe);

                }
            }
            n++;
        }
    }
}


