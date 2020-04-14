package com.zzz.service;

import java.util.Date;

public interface AlarmService {
    int getThreshold();

    int getMaxTipNum();

    int getSumTipNum(int anchorId, Date datetime, int threshold);

    void setThresholdAndMaxNum(int newThreshold, int maxTipNum);
}
