package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.dto.EventRemindDto;
import com.bkhb.EchoSphere.entity.EventRemind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 事件提醒表 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-10 14:39:15
 */
public interface IEventRemindService extends IService<EventRemind> {

    List<EventRemindDto> getPraiseList();

    String getUrl(Long eventRemindId);
}
