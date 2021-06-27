package com.netflix.loadbalancer;

/**
 * 服务列表更新器，DynamicServerListLoadBalancer的策略，动态服务列表更新
 * strategy for {@link com.netflix.loadbalancer.DynamicServerListLoadBalancer} to use for different ways
 * of doing dynamic server list updates.
 *
 * @author David Liu
 */
public interface ServerListUpdater {

    /**
     * an interface for the updateAction that actually executes a server list update
     */
    interface UpdateAction {
        void doUpdate();
    }

    /**
     * 开启服务列表更新器
     * start the serverList updater with the given update action
     * This call should be idempotent.
     *
     * @param updateAction 更新动作
     */
    void start(UpdateAction updateAction);

    /**
     * stop the serverList updater. This call should be idempotent
     */
    void stop();

    /**
     * @return the last update timestamp as a {@link java.util.Date} string
     */
    String getLastUpdate();

    /**
     * @return the number of ms that has elapsed since last update
     */
    long getDurationSinceLastUpdateMs();

    /**
     * @return the number of update cycles missed, if valid
     */
    int getNumberMissedCycles();

    /**
     * @return the number of threads used, if vaid
     */
    int getCoreThreads();
}
