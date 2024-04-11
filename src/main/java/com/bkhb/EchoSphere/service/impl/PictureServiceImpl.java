package com.bkhb.EchoSphere.service.impl;

import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.IPictureService;
import com.bkhb.EchoSphere.utils.QiniuUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PictureServiceImpl implements IPictureService {


    private final QiniuUtils qiniuUtils;

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException(BaseResultCodeEnum.BAD_REQUEST);
        }
        return qiniuUtils.uploadImageQiniu(file);
    }
}