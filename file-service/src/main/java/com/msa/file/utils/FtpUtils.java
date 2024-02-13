package com.msa.file.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.URLEncoder;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FtpUtils {

    @Value("${ftp.server.host}")
    private String server;

    @Value("${ftp.server.port}")
    private int port;

    @Value("${ftp.server.username}")
    private String username;

    @Value("${ftp.server.password}")
    private String password;

    private FTPClient ftp;

    public void open() throws SocketException, IOException {
        ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");

        // log에 주고받은 명령 출력해주는 설정
        ftp.addProtocolCommandListener(
                new PrintCommandListener(new PrintWriter(System.out), true));

        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            log.error("FTP Connection Failed...");
        }

        ftp.setSoTimeout(1000);
        ftp.login(username, password);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    @SneakyThrows
    public void close() {
        ftp.logout();
        ftp.disconnect();
    }

    @SneakyThrows
    public void upload(MultipartFile[] multipartFiles) {
        open();
        InputStream inputStream = InputStream.nullInputStream();

        for (MultipartFile file : multipartFiles) {
            inputStream = file.getInputStream();
            ftp.storeFile(file.getName(), inputStream);
        }

        close();
    }

    @SneakyThrows
    public void download(String fName, HttpServletResponse res) {
        String fileName = URLEncoder.encode(fName, "UTF-8");
        OutputStream outputStream = OutputStream.nullOutputStream();
        InputStream inputStream = InputStream.nullInputStream();

        try {
            // 간단히 하기위해 크롬 브라우져 기준 설정!
            res.setContentType("application/octet-stream");
            res.setHeader(
                    "Content-Disposition",
                    "attachment; filename="
                            .concat("\\")
                            .concat(fileName)
                            .concat("\\"));
            open();

            outputStream = new BufferedOutputStream(res.getOutputStream());
            inputStream = ftp.retrieveFileStream(fName);

            byte[] bytesArray = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
            inputStream.close();
            close();
        }
    }

    @SneakyThrows
    public void getResource(String fName, HttpServletResponse response) {
        String retrievePath = "/".concat(fName);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        this.open();
        InputStream inputStream = ftp.retrieveFileStream(retrievePath);

        if (inputStream != null) {
            int bytesRead;
            byte[] buffer = new byte[1024];

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }

            inputStream.close();
            response.getOutputStream().flush();
        }

        this.close();
    }
}
