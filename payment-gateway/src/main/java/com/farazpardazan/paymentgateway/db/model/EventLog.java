/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.db.model;

import com.farazpardazan.paymentgateway.db.model.enums.Event;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_log")
@TypeDef(
        name = "psql_enum",
        typeClass = PostgreSQLEnumType.class
)
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class EventLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    private UUID userId;

    private String ipAddress;

    private String clientVersion;

    private String appVersion;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "event", columnDefinition = "payment_event")
    private Event event;

    @Type(type = "jsonb")
    @Column(name = "info", columnDefinition = "jsonb")
    private String info;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public EventLog() {
    }

    public EventLog(UUID userId, String ipAddress, String clientVersion, String appVersion, Event event, String info) {
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.clientVersion = clientVersion;
        this.appVersion = appVersion;
        this.event = event;
        this.info = info;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventLog)) return false;

        EventLog eventLog = (EventLog) o;

        return getId() != null ? getId().equals(eventLog.getId()) : eventLog.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", ipAddress='" + ipAddress + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", event=" + event +
                ", info='" + info + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {

        private UUID userId;
        private String ipAddress;
        private String clientVersion;
        private String appVersion;
        private Event event;
        private String info;

        private Builder() {
        }

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder setClientVersion(String clientVersion) {
            this.clientVersion = clientVersion;
            return this;
        }

        public Builder setAppVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        public Builder setEvent(Event event) {
            this.event = event;
            return this;
        }

        public Builder setInfo(String info) {
            this.info = info;
            return this;
        }

        public EventLog build() {
            return new EventLog(this.userId, this.ipAddress, this.clientVersion, this.appVersion, this.event, this.info);
        }
    }
}
