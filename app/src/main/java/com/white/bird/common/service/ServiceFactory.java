package com.white.bird.common.service;

import com.white.bird.common.net.NetWorks;

/**
 * author : ZYK
 * createTime   : 2020/8/25 10:06
 * function   :
 */
public class ServiceFactory {
    private static MineService mMineService;
    public static MineService getMineService() {
        return mMineService == null ? NetWorks.getInstance().createService(MineService.class) : mMineService;
    }
}
