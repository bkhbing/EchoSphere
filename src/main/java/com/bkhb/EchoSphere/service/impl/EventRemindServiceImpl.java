package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.dto.EventRemindDto;
import com.bkhb.EchoSphere.entity.EventRemind;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.mapper.EventRemindMapper;
import com.bkhb.EchoSphere.mapper.UserMapper;
import com.bkhb.EchoSphere.service.IEventRemindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * <p>
 * 事件提醒表 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-10 14:39:15
 */
@RequiredArgsConstructor
@Service
public class EventRemindServiceImpl extends ServiceImpl<EventRemindMapper, EventRemind> implements IEventRemindService {
    private final EventRemindMapper eventRemindMapper;
    private final UserMapper userMapper;

    @Override
    public List<EventRemindDto> getPraiseList() {
        Long userId = StpUtil.getLoginIdAsLong();
        LambdaQueryWrapper<EventRemind> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EventRemind::getRecipientId, userId)
                .eq(EventRemind::getAction, 1)
                .orderByAsc(EventRemind::getCreateTime)
                .groupBy(EventRemind::getSourceId, EventRemind::getSourceType);
        return eventRemindMapper.selectList(queryWrapper).stream().map(eventRemind -> {
            EventRemindDto eventRemindDto = new EventRemindDto();
            BeanUtil.copyProperties(eventRemind, eventRemindDto);
            User senderUser = userMapper.selectById(eventRemind.getSenderId());
            if (senderUser != null) {
                eventRemindDto.setSenderName(senderUser.getNickName());
                eventRemindDto.setSenderAvatarPath(senderUser.getAvatarPath());
            }
            return eventRemindDto;
        }).toList();
    }

    @Override
    public String getUrl(Long eventRemindId) {
        Long userId = StpUtil.getLoginIdAsLong();
        LambdaQueryWrapper<EventRemind> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EventRemind::getRecipientId, userId)
                .eq(EventRemind::getEventRemindId, eventRemindId);
        EventRemind eventRemind = eventRemindMapper.selectOne(queryWrapper);
        if (eventRemind == null) {
            return null;
        }
        // 更新为已读
        eventRemind.setStatus(true);
        eventRemindMapper.updateById(eventRemind);
        return eventRemind.getUrl();
    }

    @Override
    public void addEventRemind(EventRemind eventRemind) {
        eventRemind.setSourceContent(HtmlUtils.htmlEscape(eventRemind.getSourceContent()));
        eventRemindMapper.insert(eventRemind);
    }

    @Async
    @Override
    public void addEventRemindAsync(EventRemind eventRemind) {
        addEventRemind(eventRemind);
    }
}
