package com.msa.file.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.msa.file.utils.FtpUtils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/res")
@RequiredArgsConstructor
public class FileRController {

    private final FtpUtils ftp;

    @SneakyThrows
    @GetMapping("/{fname}")
    public void get(@PathVariable String fname, HttpServletResponse res) {
        ftp.getResource(fname, res);
    }

    @SneakyThrows
    @GetMapping("/upload/{fname}")
    public void upload(@PathVariable String fname, MultipartFile[] files) {
        ftp.upload(files);
    }

    @SneakyThrows
    @GetMapping("/download/{fname}")
    public void getMethodName(@PathVariable String fname, HttpServletResponse res) {
        ftp.download(fname, res);
    }

}
