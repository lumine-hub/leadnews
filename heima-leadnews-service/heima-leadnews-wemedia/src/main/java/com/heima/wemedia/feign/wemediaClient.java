package com.heima.wemedia.feign;

import com.heima.apis.wemedia.IWemediaClient;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class wemediaClient implements IWemediaClient {

    @Autowired
    private WmChannelService wmChannelService;
    @Override
    @GetMapping("/api/v1/channel/list")
    public ResponseResult getChannels() {
        return wmChannelService.findAll();
    }
}
