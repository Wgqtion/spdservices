package com.ies.spd.spdservices.controller;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.entity.Area;
import com.ies.spd.spdservices.entity.VArea;
import com.ies.spd.spdservices.service.AreaService;
import com.ies.spd.spdservices.utils.Log4jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 片区查询接口
 *
 * @Athor: 吴广庆
 * @Date: 2019-11-12
 */
@RestController
@RequestMapping(value = "/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 获取片区根节点信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/areaList")
    public List<Area> findArea() throws Exception {
        return areaService.getRootArea();
    }

    /**
     * 新建或变更片区信息
     *
     * @param area
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save")
    public Area save(@RequestBody Area area) throws Exception {
        Log4jUtils.areaLog.info(JSON.toJSONString(area));
        return areaService.save(area);
    }

    /**
     * 删除片区信息
     *
     * @param area
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteArea")
    public String deleteArea(@RequestBody Area area) throws Exception {
        Log4jUtils.areaLog.info(JSON.toJSONString(area));
        int d = areaService.deleteArea(area);
        return "删除成功！" + d + "条";
    }
}
