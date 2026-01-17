package com.example.demosys.infra.messaging;

/**
 * MessagePublisher
 * 事件/消息发布抽象：用于通知、异步任务等。
 */
public interface MessagePublisher {

    /** 发布消息到主题/路由 */
    void publish(String topic, Object payload);

}
