package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.entity.Chat;
import com.bkhb.EchoSphere.entity.Message;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.mapper.ChatMapper;
import com.bkhb.EchoSphere.mapper.MessageMapper;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 私信表 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 22:24:42
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
    private final MessageMapper messageMapper;
    private final ChatMapper chatMapper;

    @Override
    public Message sendMessage(Message message) {
        Long userId = StpUtil.getLoginIdAsLong();
        message.setSenderId(userId);
        save(message);
        // 判断两人之间的聊天室是否建立
        LambdaQueryWrapper<Chat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Chat::getUser1Id, userId).eq(Chat::getUser2Id, message.getReceiverId()).or()
                        .eq(Chat::getUser1Id, message.getReceiverId()).eq(Chat::getUser2Id, userId);
        if (Objects.isNull(chatMapper.selectOne(queryWrapper))) {
            chatMapper.insert(new Chat().setUser1Id(userId).setUser2Id(message.getReceiverId()).setLastMessage(message.getContent()));
        } else {
            chatMapper.update(new Chat().setLastMessage(message.getContent()), queryWrapper);
        }
        return getById(message.getMessageId());
    }

    @Override
    public void readMessage(Long messageId) {
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Message::getMessageId, messageId)
                .eq(Message::getReceiverId, StpUtil.getLoginIdAsLong())
                .eq(Message::getStatus, false);
        if (messageMapper.update(new Message().setStatus(true), queryWrapper) == 0){
            throw new BadRequestException(BaseResultCodeEnum.NO_OPERATE_PERMISSION);
        }
    }

    @Override
    public List<Message> getInbox() {
        Long userId = StpUtil.getLoginIdAsLong();
        return null;
    }
}
