package org.marketcetera.core.module;

import java.util.Map;

import javax.management.MXBean;

/* $License$ */
/**
 * The management / monitoring interface for the sink module.
 *
 * @version $Id: SinkModuleMXBean.java 16063 2012-01-31 18:21:55Z colin $
 * @since 1.0.0
 */
@MXBean(true)
@DisplayName("Sink Module")
public interface SinkModuleMXBean {
    /**
     * Returns statistics on various types of data received by
     * the sink module.
     * The map has the data type name as the key and a counter of number
     * of instances that have been received by the sink module as the value
     *
     * @return map containing statistics on the different types of data
     * received by the module
     */
    @DisplayName("Statistics based on data types")
    Map<String, Integer> getTypeStats();

    /**
     * Returns statistics on the number of data instances received by
     * the sink module for different data flows.
     * The map has the data flow ID as they key and a counter of
     * number of data instances received for that data flow as a value.
     *
     * @return map containing statistics on the number of data instances
     * received by the sink module for different data flows.
     */
    @DisplayName("Statistics based on data flows")
    Map<DataFlowID, Integer> getDataFlowStats();

    /**
     * Resets the statistics for data flows and data types.
     */
    @DisplayName("Reset all statistics")  
    void resetStats();
}