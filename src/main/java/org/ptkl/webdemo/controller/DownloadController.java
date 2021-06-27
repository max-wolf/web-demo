package org.ptkl.webdemo.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @RequestMapping("/streamDownload")
    public void streamDownload(HttpServletResponse response) {
        byte[] data = FileUtil.readBytes("C:\\Users\\Patrick\\Desktop\\1.jpg");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=1.jpg");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        try(ServletOutputStream outputStream1 = response.getOutputStream();
            OutputStream outputStream = new BufferedOutputStream(outputStream1)) {
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chunkDownload(HttpServletResponse response) {

    }
}
