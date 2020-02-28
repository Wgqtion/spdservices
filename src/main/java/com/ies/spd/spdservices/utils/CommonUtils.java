package com.ies.spd.spdservices.utils;

import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-08
 */
public class CommonUtils {

    public synchronized static int checkStatus(int lifeTerm, Long lifeThreshold, Long updateTimeLong) {
        Date nowDate = new Date();
        if (lifeTerm == 0 || nowDate.getTime() - updateTimeLong > 1000 * 60 * 60 * 2) {
            return 2;
        } else if (lifeThreshold != null) {
            if (lifeTerm < lifeThreshold) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
