package com.ies.spd.spdservices.service;

import com.ies.spd.spdservices.dao.EquipmentEventLogDao;
import com.ies.spd.spdservices.dao.mapper.EquipmentEventLogMapper;
import com.ies.spd.spdservices.entity.*;
import com.ies.spd.spdservices.tcp.entity.TcpMsg;
import com.ies.spd.spdservices.utils.Log4jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-10
 */
@Service
@Transactional
public class EquipmentEventLogService {

    @Autowired
    private EquipmentEventLogDao equipmentEventLogDao;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EquipmentEventService equipmentEventService;

    @Autowired
    private EquipmentEventLogMapper equipmentEventLogMapper;

    //    @Autowired
//    private GuavaCacheService guavaCacheService;
    @Autowired
    private CaffeineService caffeineService;

    @Autowired
    private GatewayService gatewayService;

    public void tcpReceipt(TcpMsg tcpMsg) {
        Date nowDate = new Date();
        Log4jUtils.tcpLog.info("CRC校验" + nowDate + "：" + tcpMsg.getFlagCRC());
        if (tcpMsg.getFlagCRC()) {
            EquipmentEvent equipmentEvent = caffeineService.getEquipmentEvent(tcpMsg.getSpdNo());
            Equipment equipment = caffeineService.getEquipment(tcpMsg.getSpdNo());
//            List<Gateway> gatewayList = guavaCacheService.getGateway();
            // 如果没有则刷新缓存
//            if (equipmentList == null) {
//                equipmentList = guavaCacheService.putEquipmentNo();
//            }
//
//            // 如果没有则刷新缓存
//            if (equipmentEventList == null) {
//                equipmentEventList = guavaCacheService.putEquipmentEventNo();
//            }

            // 如果没有则刷新缓存
//            if (gatewayList == null) {
//                gatewayList = guavaCacheService.putGateway();
//            }

//            Optional<Equipment> equipment = null;
//            EquipmentEvent equipmentEventBuild = new EquipmentEvent();
//            Optional<Gateway> gateway = null;
            // 如果没有则刷新缓存
//            if (equipmentList == null) {
//                equipment = equipmentList.stream().filter(a -> a.getSpdNo().equals(tcpMsg.getSpdNo())).findFirst();
//            }
//            // 如果没有则刷新缓存
//            if (equipmentEventList == null) {
//                equipmentEventBuild = equipmentEventList.stream().filter(a -> a.getSpdNo().equals(tcpMsg.getSpdNo())).findFirst();
//            }

//            EquipmentEvent equipmentEvent = new EquipmentEvent();
//            Gateway selectGateway = gatewayService.findByhostAddressAndPort(tcpMsg.getHostAddress(), tcpMsg.getPort());
//            EquipmentEvent equipmentEventBuild = equipmentEventService.getEquipmentEventBySpdNo(tcpMsg.getSpdNo());
            if (equipmentEvent == null) {
                equipmentEvent = new EquipmentEvent();
                equipmentEvent.setSpdNo(tcpMsg.getSpdNo());
                Gateway selectGateway = gatewayService.findByhostAddressAndPort(tcpMsg.getHostAddress(), tcpMsg.getPort());
                // 绑定网关信息
                equipmentEvent.setGateway(selectGateway);
            } else if (!tcpMsg.getHostAddress().equals(equipmentEvent.getGateway().getHostAddress())) {
                Gateway selectGateway = gatewayService.findByhostAddressAndPort(tcpMsg.getHostAddress(), tcpMsg.getPort());
                // 绑定网关信息
                equipmentEvent.setGateway(selectGateway);
            }
            if (equipment != null && equipmentEvent.getEquipment() == null) {
                // 查询当前设备状态信息
                System.out.println("入力设备详情");
                equipmentEvent.setEquipment(equipment);
            }

            tcpMsg.buidEquipmentEvent(equipmentEvent);
            equipmentEvent.setUpdateTime(nowDate);
            equipmentEvent.setCreateTime(nowDate);
            if (equipmentEvent.getStatus() == 0x22) {
                equipmentEvent.setLightningTime(nowDate);
            }
            equipmentEventService.save(equipmentEvent);
            EquipmentEventLog equipmentEventLog = new EquipmentEventLog();
            equipmentEventLog.setSpdNo(tcpMsg.getSpdNo());
            equipmentEventLog.setSilcL1(equipmentEvent.getSilcL1());
            equipmentEventLog.setSilcL2(equipmentEvent.getSilcL2());
            equipmentEventLog.setSilcL3(equipmentEvent.getSilcL3());
            equipmentEventLog.setSilcL4(equipmentEvent.getSilcL4());
            equipmentEventLog.setLifeTermL1(equipmentEvent.getLifeTermL1());
            equipmentEventLog.setLifeTermL2(equipmentEvent.getLifeTermL2());
            equipmentEventLog.setLifeTermL3(equipmentEvent.getLifeTermL3());
            equipmentEventLog.setLifeTermL4(equipmentEvent.getLifeTermL4());
            equipmentEventLog.setLightningAmplitudeL1(equipmentEvent.getLightningAmplitudeL1());
            equipmentEventLog.setLightningAmplitudeL2(equipmentEvent.getLightningAmplitudeL2());
            equipmentEventLog.setLightningAmplitudeL3(equipmentEvent.getLightningAmplitudeL3());
            equipmentEventLog.setLightningAmplitudeL4(equipmentEvent.getLightningAmplitudeL4());
            equipmentEventLog.setLightningCountL1(equipmentEvent.getLightningCountL1());
            equipmentEventLog.setLightningCountL2(equipmentEvent.getLightningCountL2());
            equipmentEventLog.setLightningCountL3(equipmentEvent.getLightningCountL3());
            equipmentEventLog.setLightningCountL4(equipmentEvent.getLightningCountL4());
            equipmentEventLog.setEdition(equipmentEvent.getEdition());
            equipmentEventLog.setHexMsg(tcpMsg.getHexMsg());
            equipmentEventLog.setStatus(equipmentEvent.getStatus());
            equipmentEventLog.setUpdateTime(nowDate);
            equipmentEventLogDao.save(equipmentEventLog);
        }
    }

    /**
     * 历史记录查询，预警记录
     * TODO 后期优化，需添加历史记录表及存储过程优化查询
     *
     * @return
     */
    public List<EquipmentHistory> warnDateSelect(int thisMonth, int thisYear) {
        return equipmentEventLogMapper.warnDateSelect(thisMonth, thisYear);
    }

    public List<EquipmentHistory> dangerDateSelect(int thisMonth, int thisYear) {
        return equipmentEventLogMapper.dangerDateSelect(thisMonth, thisYear);
    }
}
