package com.ies.spd.spdservices.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ies.spd.spdservices.dao.VersionDao;
import com.ies.spd.spdservices.entity.Version;
import com.ies.spd.spdservices.service.VersionService;
import com.ies.spd.spdservices.utils.Log4jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-26
 */
@RestController
@RequestMapping(value = "/version")
public class VersionController {

    @Autowired
    private VersionService versionService;

    /**
     * 查询设备类型列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "versionList")
    public List<Version> getVersionList()throws Exception{
        return versionService.getRootCount();
    }
    /**
     * 新建或编辑设备类型
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "save")
    public Version save(@RequestBody Version version)throws Exception{
        Log4jUtils.versionLog.info(JSON.toJSONString(version));
        return versionService.save(version);
    }
    /**
     * 删除设备类型
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deleteVersion")
    public Version deleteVersion(@RequestBody Version version)throws Exception{
        Log4jUtils.versionLog.info(JSON.toJSONString(version));
        return versionService.save(version);
    }
}
