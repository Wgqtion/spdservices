package com.ies.spd.spdservices.service;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.dao.VersionDao;
import com.ies.spd.spdservices.dao.mapper.VersionMapper;
import com.ies.spd.spdservices.entity.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-24
 */
@Service
@Transactional
public class VersionService {

    @Autowired
    private VersionMapper versionMapper;

    @Autowired
    private VersionDao versionDao;


    /**
     * 统计类型设备数量
     * @return
     */
    public List<Version> getRootCount()throws Exception{
        List<Version> versionList = versionMapper.getRootCount();
        return versionList;
    }

    /**
     * 获取所有类型列表
     * @return
     */
    public List<Version> getVersionList()throws Exception{
        return versionDao.findAll();
    }

    /**
     * 修改或变更设备类型信息
     * @param version
     * @return
     * @throws Exception
     */
    public Version save(Version version) throws Exception{
        return versionDao.save(version);
    }
}
