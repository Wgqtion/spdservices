package com.ies.spd.spdservices.service;

import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.dao.GatewayDao;
import com.ies.spd.spdservices.dao.mapper.GatewayMapper;
import com.ies.spd.spdservices.entity.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-12-17
 */
@Service
public class GatewayService {
    @Autowired
    private GatewayDao gatewayDao;

    @Autowired
    private GatewayMapper gatewayMapper;

    /**
     * 查询当前网关
     * @param hostAddress
     * @param port
     * @return
     */
    public Gateway findByhostAddressAndPort(String hostAddress,Integer port){
        return gatewayDao.findGatewayByHostAddressAndPort(hostAddress, port);
    }

    // 查询网关信息
    public List<Gateway> findAll(){
       return gatewayMapper.findGateway();
    }

    /**
     * 更新网关信息
     */
    public Gateway save(Gateway gateway) {
        Gateway selectGateway = gatewayDao.findGatewayByHostAddressAndPort(gateway.getHostAddress(), gateway.getPort());
        // 如果为空则新建记录
        if (selectGateway == null || selectGateway.getId() == null) {
            return gatewayDao.saveAndFlush(gateway);
        }
        //如果掉线则网关恢复上线
        else if(!selectGateway.getOnline()){
            selectGateway.setOnline(true);
            selectGateway.setUpdateTime(gateway.getUpdateTime());
            return gatewayDao.saveAndFlush(selectGateway);
        }
        //网关正常直接返回
        else {
            return selectGateway;
        }
    }
    /**
     * 网关下线
     */
    public Gateway disGateway(Gateway gateway) {
        Gateway selectGateway = gatewayDao.findGatewayByHostAddressAndPort(gateway.getHostAddress(), gateway.getPort());
        // 如果为空则新建记录
        if (selectGateway != null && selectGateway.getId() != null) {
            selectGateway.setOnline(false);
            selectGateway.setUpdateTime(gateway.getUpdateTime());
            return gatewayDao.saveAndFlush(selectGateway);
        }else {
            return null;
        }
    }

    // 下线所有网关
    public List<Gateway> disAllGateway(){
       List<Gateway> gatewayList = gatewayDao.findAllByOnlineIsTrue();
       if(gatewayList != null){
           for(Gateway gateway:gatewayList){
               gateway.setOnline(false);
           }
           return gatewayDao.saveAll(gatewayList);
       }
       return null;
    }
}
