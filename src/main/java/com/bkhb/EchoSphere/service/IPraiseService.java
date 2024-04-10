package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.entity.Praise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 点赞信息表 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-08 17:20:18
 */
public interface IPraiseService extends IService<Praise> {

    void addPraiseByPostId(Long postId);

    void delPraiseByPostId(Long postId);

    void addPraiseByCommentId(Long commentId);

    void delPraiseByCommentId(Long commentId);

    void addPraise(Praise praise);

    void delPraise(Praise praise);
}
