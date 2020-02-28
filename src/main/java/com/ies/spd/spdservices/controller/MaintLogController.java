package com.ies.spd.spdservices.controller;

import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.entity.MaintLog;
import com.ies.spd.spdservices.service.MaintLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备维护
 *
 * @Athor: 吴广庆
 * @Date: 2019-11-09
 */
@RestController
@RequestMapping(value = "/maintlog")
public class MaintLogController {

    @Autowired
    private MaintLogService maintLogService;

    // 查询设备维护
    @RequestMapping(value = "/maintloglist")
   public Page<MaintLog> getMainLogList(@RequestBody PageConstants mainLogPageConstants){
      return maintLogService.getMaintLogList(mainLogPageConstants);
    }

    // 添加设备维护记录
    @RequestMapping(value = "/save")
    public MaintLog save(MaintLog maintLog){
        return maintLogService.save(maintLog);
    }
}
