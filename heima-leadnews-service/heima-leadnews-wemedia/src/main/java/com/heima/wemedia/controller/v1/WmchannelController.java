package com.heima.wemedia.controller.v1;

import com.heima.model.admin.dtos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.ChannelDto;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class WmchannelController {

    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/channels")
    public ResponseResult findAll(){
        return wmChannelService.findAll();
    }

    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto) {
        return null;
    }

    @GetMapping("/del/{id}")
    public ResponseResult delChannel(@PathVariable("id") String id) {
        return wmChannelService.delChannel(id);
    }

    @PostMapping("/list")
    public ResponseResult list(@RequestBody ChannelDto dto){
        return wmChannelService.list(dto);
    }
    @PostMapping("/save")
    public ResponseResult save(@RequestBody AdChannel adChannel){
        return wmChannelService.save(adChannel);
    }


}