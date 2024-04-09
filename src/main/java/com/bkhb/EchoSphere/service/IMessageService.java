package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 私信表 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 22:24:42
 */
public interface IMessageService extends IService<Message> {

    Message sendMessage(Message message);

    void readMessage(Long messageId);

    List<Message> getInbox();
}
