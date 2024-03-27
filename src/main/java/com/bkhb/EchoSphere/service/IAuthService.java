package com.bkhb.EchoSphere.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.bkhb.EchoSphere.api.AuthenticationRequest;

/**
 * TODO
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/27 2:03
 */
public interface IAuthService {

    SaTokenInfo login(AuthenticationRequest request);

    void logout();
}
