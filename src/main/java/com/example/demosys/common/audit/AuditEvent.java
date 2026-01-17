package com.example.demosys.common.audit;

import java.lang.annotation.*;

/**
 * AuditEvent
 * 用于标注需要审计的接口/方法。
 *
 * 使用示例：
 * @AuditEvent(action = "JOB_RUN", module = "jobs", resource = "job", recordArgs = true, recordResult = false)
 * public ApiResponse runJob(...) { ... }
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditEvent {

    /** 动作标识：如 LOGIN / JOB_RUN / FILE_UPLOAD / CREATE_STUDENT 等 */
    String action();

    /** 模块：auth/admissions/education/files/jobs/notification... */
    String module() default "";

    /** 资源：candidate/job/file/template... */
    String resource() default "";

    /** 是否记录入参（注意脱敏） */
    boolean recordArgs() default false;

    /** 是否记录返回结果（注意脱敏 & 体积） */
    boolean recordResult() default false;

    /** 是否记录异常堆栈（生产建议 false，只记 message + code） */
    boolean recordStacktrace() default false;

    /** 自定义描述 */
    String description() default "";
}
