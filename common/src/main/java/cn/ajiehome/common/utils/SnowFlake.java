package cn.ajiehome.common.utils;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 符号位    时间       机房号        机器号     同一时间内产生不同的ID（4095个）
 * bit(0) + time + dataCenterId + workerId  + sequence;
 *   1       41        5             5            12
 */
@Component
public class SnowFlake {
    /**
     * 机器标识
     */
    @Value("${app.worker.id}")
    private long workerId;
    /**
     * 数据中心编号
     */
    @Value("${app.data.center.id}")
    private long dataCenterId;
    /**
     * 序列号
     */
    private long sequence = 0L;

    private static final Integer WORKER_ID_BITS = 5;
    private static final Integer DATA_CENTER_ID_BITS = 5;
    private static final Integer SEQUENCE_BITS = 12;
    /**
     * 初始状态时间  2010-04-42 09:42:54
     */
    private static final Long INITIAL_TIME = 1288834974657L;
    /**
     * maxWorkerId=31,bit=>11111
     * maxDataCenterId=31,bit=>11111
     * MAX_SEQUENCE=4095.bit=>111111111111
     */
    private Long maxWorkerId = ~(-1L << WORKER_ID_BITS);
    private Long maxDataCenterId = ~(-1L << DATA_CENTER_ID_BITS);
    private static final Long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);
    /**
     * 所有组成部分移动的距离
     */
    private static final Integer WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final Integer DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final Integer TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    /**
     * 上一次的时间戳
     */
    private long lastTimestamp = -1L;

    public synchronized Long nextId(){
        long timestamp = timeGen();

        //当前时间小于过去时间,抛出异常表示时间指针被回拨
        if (timestamp < lastTimestamp){
            throw new ApplicationException(CodeType.TIME_POINTER_BACK);
        }
        //当前时间等于过去时间，表示同一时间出现两个
        if (timestamp == lastTimestamp){
            sequence = (sequence+1) & MAX_SEQUENCE;
            //超出12位长度就会是0，由于运算符（&）的特性，然后重新获取一个时间戳
            if (sequence == 0){
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else {
            //恢复到初始序列号数
            sequence = 0L;
        }
        //赋值为过去的时间戳
        lastTimestamp = timestamp;
        return (timestamp - INITIAL_TIME) << TIMESTAMP_SHIFT
                | dataCenterId << DATA_CENTER_ID_SHIFT
                | workerId << WORKER_ID_SHIFT
                | sequence;
    }
    private Long tilNextMillis(long lastTimestamp){
        long timestamp = timeGen();
        while (timestamp<=lastTimestamp){
            timestamp = timeGen();
        }
        return timestamp;
    }
    private Long timeGen(){
        return System.currentTimeMillis();
    }
}