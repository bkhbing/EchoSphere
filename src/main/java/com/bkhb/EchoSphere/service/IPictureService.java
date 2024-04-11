package com.bkhb.EchoSphere.service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPictureService {
    String upload(MultipartFile file);
}
