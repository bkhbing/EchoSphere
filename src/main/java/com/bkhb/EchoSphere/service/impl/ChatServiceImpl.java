package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.dto.ChatDto;
import com.bkhb.EchoSphere.entity.Chat;
import com.bkhb.EchoSphere.entity.Message;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.mapper.ChatMapper;
import com.bkhb.EchoSphere.mapper.MessageMapper;
import com.bkhb.EchoSphere.mapper.UserMapper;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.IChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 聊天室表 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 23:03:16
 */
@RequiredArgsConstructor
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {
    private final ChatMapper chatMapper;
    private final UserMapper userMapper;
    private final MessageMapper messageMapper;

    @Override
    public List<ChatDto> getChatByLoginUserId() {
        Long userId = StpUtil.getLoginIdAsLong();
        LambdaQueryWrapper<Chat> chatLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chatLambdaQueryWrapper.eq(Chat::getUser1Id, userId).or().eq(Chat::getUser2Id, userId);
        return chatMapper.selectList(chatLambdaQueryWrapper).stream().map(chat -> {
            User user = userMapper.selectById(chat.getUser1Id().equals(userId) ? chat.getUser2Id() : chat.getUser1Id());
            return ChatDto.builder()
                    .chatId(chat.getChatId())
                    .userId(user.getUserId())
                    .nickName(user.getNickName())
                    .avatarPath(user.getAvatarPath())
                    .lastMessage(chat.getLastMessage())
                    .createTime(chat.getCreateTime())
                    .build();
        }).toList();
    }

    @Override
    public List<Message> getMessageByChatId(Long chatId) {
        Long userId = StpUtil.getLoginIdAsLong();
        // 判断聊天室是否属于登陆用户
        LambdaQueryWrapper<Chat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Chat::getChatId, chatId).or().eq(Chat::getUser1Id, userId).eq(Chat::getUser2Id, userId);
        Chat chat = chatMapper.selectOne(queryWrapper);
        if (Objects.isNull(chat)) {
            throw new BadRequestException(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
        }
        // 查看聊天记录
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getSenderRemove, false)
                .eq(Message::getSenderId, userId)
                .eq(Message::getReceiverId, chat.getUser1Id().equals(userId) ? chat.getUser2Id() : chat.getUser1Id())
                .or()
                .eq(Message::getSenderId, chat.getUser1Id().equals(userId) ? chat.getUser2Id() : chat.getUser1Id())
                .eq(Message::getReceiverId, userId)
                .orderByAsc(Message::getCreateTime);
        return messageMapper.selectList(messageLambdaQueryWrapper);
    }
}
