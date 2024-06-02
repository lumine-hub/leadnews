package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.ChannelDto;
import com.heima.model.wemedia.pojos.WmChannel;

public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有频道
     * @return
     */
    public ResponseResult findAll();

    public ResponseResult delChannel(String id);

    public ResponseResult list(ChannelDto dto);
    public ResponseResult save(AdChannel adChannel);



}