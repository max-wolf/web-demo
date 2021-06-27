package org.ptkl.webdemo.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileMode;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DownloadControllerTest {

    @Test
    void chunkDownload() throws IOException {
        String orginalPath = "C:\\Users\\Patrick\\Desktop\\1.jpg";
        File file = new File(orginalPath);
        System.out.println(FileUtil.size(file));
        final long fileLength = file.length();
        final int readSize = 100*1024;
//        final int readSize = 100*1024*1024;
        long blocks = fileLength / readSize;
        long remainder = fileLength % readSize;
        final BufferedInputStream bis = FileUtil.getInputStream(orginalPath);
        byte[] bufBytes = new byte[readSize];
        int len = 0;
        final ArrayList<String> base64List = new ArrayList<>();
        String tmpFilePath = "C:\\Users\\Patrick\\Desktop\\a.txt";
        if (FileUtil.exist(tmpFilePath)) {
            FileUtil.del(tmpFilePath);
        }
        File base64File = FileUtil.file(tmpFilePath);
        PrintWriter printWriter = FileUtil.getPrintWriter(base64File, Charset.defaultCharset(), true);
        while((len=bis.read(bufBytes))!=-1) {
            byte[] readBytes;
            if (len != bufBytes.length) {
                readBytes = Arrays.copyOf(bufBytes, len);
            } else {
                readBytes = bufBytes;
            }
            String encode = Base64.encode(readBytes);
            printWriter.println(encode);
        }
        IoUtil.close(bis);
        IoUtil.close(printWriter);

        String newFilePath="C:\\Users\\Patrick\\Desktop\\11.jpg";
        if (FileUtil.exist(newFilePath)) {
            FileUtil.del(newFilePath);
        }
        OutputStream out = new FileOutputStream(newFilePath,true);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(tmpFilePath));
        String contentLine;
        while ((contentLine = bufferedReader.readLine()) != null) {
            byte[] decode = Base64.decode(contentLine);
            System.out.println(decode.length);
            out.write(decode);
        }
        IoUtil.close(out);
        IoUtil.close(bufferedReader);
    }
}