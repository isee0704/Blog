package com.shyoc.service;

import com.shyoc.pojo.UVStatistic;


public interface UVStatisticService {
    /**
     * 获得最近recent天的访客统计数据
     * @param recent
     * @return
     */
    UVStatistic getStatistic(Integer recent);

    /**
     * 获得历史访客总计数量
     * @return
     */
    Long getTotal();
}
