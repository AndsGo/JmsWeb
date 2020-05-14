package com.ldy.enums;

import java.util.Objects;

/**
 * 垃圾收集名称
 */
public enum CollectorEnum {

    /**
     * -XX:+UseSerialGC
     */
    COPY("Copy"),

    /**
     * -XX:+UseSerialGC
     */
    MARK_SWEEP_COMPACT("MarkSweepCompact"),

    /**
     * -XX:+UseParallelOldGC
     * -XX:+UseParallelGC
     */
    PS_SCAVENGE("PS Scavenge"),

    /**
     * -XX:+UseParallelOldGC
     * -XX:+UseParallelGC
     */
    PS_MARKSWEEP("PS MarkSweep"),

    /**
     * -XX:+UseConcMarkSweepGC
     * -XX:+UseParNewGC
     */
    Par_New("ParNew"),

    /**
     * -XX:+UseConcMarkSweepGC
     * -XX:+UseParNewGC
     */
    Concurrent_Mark_Sweep("ConcurrentMarkSweep"),

    /**
     * -XX:+UseG1GC
     */
    G1_Young("G1 Young Generation"),

    /**
     * -XX:+UseG1GC
     */
    G1_Old("G1 Old Generation"),;

    private String collectorName;

    CollectorEnum(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public static CollectorEnum enumOf(String name) {
        CollectorEnum[] values = CollectorEnum.values();
        for (CollectorEnum value : values) {
            if (Objects.equals(value.collectorName, name)) {
                return value;
            }
        }
        return null;
    }


}
