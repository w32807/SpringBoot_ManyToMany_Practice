package com.zerock.mreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
// 파일 업로드 테스트를 위한 컨트롤러
public class UploadTestController {

    @GetMapping("/uploadEx")
    public void uploadEx(){}
}

