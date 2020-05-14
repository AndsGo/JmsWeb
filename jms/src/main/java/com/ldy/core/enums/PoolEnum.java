package com.ldy.core.enums;

import java.util.Objects;

/**
 * 内存区域
 */
public enum PoolEnum {


    /**
     * -XX:+UseSerialGC
     */
    EDEN_SPACE("Eden Space"),
    /**
     * -XX:+UseSerialGC
     */
    SURVIVOR_SPACE("Survivor Space"),
    /**
     * -XX:+UseSerialGC
     */
    TENURED_GEN("Tenured Gen"),

    /**
     *
     */
    PS_EDEN_SPACE("PS Eden Space"),
    /**
     *
     */
    PS_SURVIVOR_SPACE("PS Survivor Space"),
    /**
     *
     */
    PS_OLD_GEN("PS Old Gen"),


    /**
     * -XX:+UseConcMarkSweepGC
     */
    PAR_EDEN_SPACE("Par Eden Space"),
    /**
     * -XX:+UseConcMarkSweepGC
     */
    PAR_SURVIVOR_SPACE("Par Survivor Space"),
    /**
     * -XX:+UseConcMarkSweepGC
     */
    CMS_OLD_GEN("CMS Old Gen"),


    /**
     * -XX:+UseG1GC
     */
    G1_EDEN_SPACE("G1 Eden Space"),
    /**
     * -XX:+UseG1GC
     */
    G1_SURVIVOR_SPACE("G1 Survivor Space"),
    /**
     * -XX:+UseG1GC
     */
    G1_OLD_GEN("G1 Old Gen"),


    /**
     *
     */
    META_SPACE("Metaspace"),
    /**
     *
     */
    CODE_CACHE("Code Cache"),
    /**
     *
     */
    COMPRESSED_CLASS_SPACE("Compressed Class Space"),
    ;

    private String poolName;

    PoolEnum(String poolName) {
        this.poolName = poolName;
    }

    public String getPoolName() {
        return poolName;
    }

    public static PoolEnum enumOf(String name) {
        PoolEnum[] values = PoolEnum.values();
        for (PoolEnum value : values) {
            if (Objects.equals(value.poolName, name)) {
                return value;
            }
        }
        return null;
    }
}
