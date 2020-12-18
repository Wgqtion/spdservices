package com.ies.spd.spdservices.tcp.entity;

import com.ies.spd.spdservices.entity.EquipmentEvent;
import com.ies.spd.spdservices.utils.HexUtils;

/**
 * tcp消息类
 *
 * @author XiangXiaoLin
 */
public class TcpMsg {

    /**
     * 字节消息
     */
    private byte[] reds;

    /**
     * 网关IP
     */
    private String hostAddress;
    /**
     * 网关端口
     */
    private Integer port;

    public TcpMsg(byte[] reds, String hostAddress, Integer port) {
        this.reds = reds;
        this.hostAddress = hostAddress;
        this.port = port;
    }

    public byte[] getReds() {
        return reds;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public Integer getPort() {
        return port;
    }

    public String getHexMsg() {
        return HexUtils.getHexStr(reds);
    }

    public boolean getFlagCRC() {
        return CRC16M.checkCRC(reds, 23);
    }

    public String getSpdNo() {
//        return Integer.toHexString(reds[3]).concat(Integer.toHexString(reds[4])).concat(Integer.toHexString(reds[5]));
        return String.valueOf(reds[3] * 65536 + reds[4] * 256 + reds[5]);
    }

    public int getByte(int Lx) {
        int l = reds[Lx] & 0xff;
        if (l != 0) {
            return l;
        } else {
            return 0;
        }
    }

    public void buidEquipmentEvent(EquipmentEvent equipmentEvent) {
        for (int i = 0; i < reds.length; i++) {
            switch (i) {
                case 2:
                    equipmentEvent.setStatus(reds[i] & 0xff);
                    break;
                case 6:
                    equipmentEvent.setSilcL1(reds[i] & 0xff);
                    break;
                case 7:
                    equipmentEvent.setSilcL2(reds[i] & 0xff);
                    break;
                case 8:
                    equipmentEvent.setSilcL3(reds[i] & 0xff);
                    break;
                case 9:
                    equipmentEvent.setSilcL4(reds[i] & 0xff);
                    break;
                case 10:
                    equipmentEvent.setLightningAmplitudeL1(reds[i] & 0xff);
                    break;
                case 11:
                    equipmentEvent.setLightningAmplitudeL2(reds[i] & 0xff);
                    break;
                case 12:
                    equipmentEvent.setLightningAmplitudeL3(reds[i] & 0xff);
                    break;
                case 13:
                    equipmentEvent.setLightningAmplitudeL4(reds[i] & 0xff);
                    break;
                case 14:
                    equipmentEvent.setLightningCountL1(reds[i] & 0xff);
                    break;
                case 15:
                    equipmentEvent.setLightningCountL2(reds[i] & 0xff);
                    break;
                case 16:
                    equipmentEvent.setLightningCountL3(reds[i] & 0xff);
                    break;
                case 17:
                    equipmentEvent.setLightningCountL4(reds[i] & 0xff);
                    break;
                case 18:
                    if (reds[i] != 0x00 && reds[i] < 0) {
                        equipmentEvent.setLifeTermL1((reds[i] ^ 0x80) & 0xff);
                    } else {
                        equipmentEvent.setLifeTermL1(0);
                    }
                    break;
                case 19:
                    if (reds[i] != 0x00 && reds[i] < 0) {
                        equipmentEvent.setLifeTermL2((reds[i] ^ 0x80) & 0xff);
                    } else {
                        equipmentEvent.setLifeTermL2(0);
                    }
                    break;
                case 20:
                    if (reds[i] != 0x00 && reds[i] < 0) {
                        equipmentEvent.setLifeTermL3((reds[i] ^ 0x80) & 0xff);
                    } else {
                        equipmentEvent.setLifeTermL3(0);
                    }
                    break;
                case 21:
                    if (reds[i] != 0x00 && reds[i] < 0) {
                        equipmentEvent.setLifeTermL4((reds[i] ^ 0x80) & 0xff);
                    } else {
                        equipmentEvent.setLifeTermL4(0);
                    }
                    break;
                case 22:
                    equipmentEvent.setEdition(reds[i] & 0xff);
                    break;
                default:
            }
        }

    }

    public int getLightningAmplitude() {
        int l = reds[6] & 0xff;
        if (l != 0) {
            return l;
        } else {
            return 0;
        }
    }

    public Integer getLifeTerm() {
        if (reds[2] == 0x22) {
            return reds[18] & 0xff;
        }
        return 0;
    }
}
