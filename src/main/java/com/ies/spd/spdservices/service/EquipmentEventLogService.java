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

    @Autowired
    private GatewayService gatewayService;

    public void tcpReceipt(TcpMsg tcpMsg) {
        Date nowDate = new Date();
        Log4jUtils.tcpLog.info("CRC校验" + nowDate + "：" + tcpMsg.getFlagCRC());
        if (tcpMsg.getFlagCRC()) {
            Equipment equipment = equipmentService.getEquipmentBySpdNo(tcpMsg.getSpdNo());
            EquipmentEvent equipmentEvent = new EquipmentEvent();
            Gateway selectGateway = gatewayService.findByhostAddressAndPort(tcpMsg.getHostAddress(),tcpMsg.getPort());
            if (equipment != null) {
                EquipmentEvent equipmentEventBuild = equipmentEventService.getEquipmentEventByEquipment(equipment);
                // 查询当前设备状态信息
                if (equipmentEventBuild == null) {
                    equipmentEvent.setEquipment(equipment);
                } else {
                    equipmentEvent = equipmentEventBuild;
                }
            } else {
                EquipmentEvent equipmentEventBuild = equipmentEventService.getEquipmentEventBySpdNo(tcpMsg.getSpdNo());
                if (equipmentEventBuild != null) {
                    equipmentEvent = equipmentEventBuild;
                } else {
                    equipmentEvent.setSpdNo(tcpMsg.getSpdNo());
                }
            }
            // 绑定网关信息
            equipmentEvent.setGateway(selectGateway);
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
