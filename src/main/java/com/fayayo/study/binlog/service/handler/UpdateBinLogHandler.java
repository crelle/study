package com.fayayo.study.binlog.service.handler;

import com.fayayo.study.binlog.dto.BaseLogInfo;
import com.fayayo.study.binlog.service.AbstractBinLogHandler;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;

/**
 * @author dalizu on 2019/1/18.
 * @version v1.0
 * @desc
 */
public class UpdateBinLogHandler extends AbstractBinLogHandler {
    @Override
    protected BaseLogInfo doEvent(Event event) {

        UpdateRowsEventData eventData=event.getData();


        return null;
    }
}
