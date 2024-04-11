package com.bkhb.EchoSphere.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.bkhb.EchoSphere.service.IPictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "图片管理", description = "图片管理")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class PictureController {

    private final IPictureService pictureService;

    @Operation(summary = "上传图片", description = "上传图片")
    @SaIgnore
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return pictureService.upload(file);
    }
}