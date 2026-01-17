package com.example.demosys.infra.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * NoopMessagePublisher
 * 默认实现：没有 MQ 时打印日志（方便本地跑通）。
 * 若启用 MQ，请替换为 Rabbit/Kafka 发布实现。
 */
@Slf4j
@Component
public class NoopMessagePublisher implements MessagePublisher {

    @Override
    public void publish(String topic, Object payload) {
        log.info("[MQ:NOOP] topic={}, payload={}", topic, payload);
    }
}
