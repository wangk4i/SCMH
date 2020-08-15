package com.hyd.centermq.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Queue")
public class QueueConfig {

    @XmlElement(name = "Item")
    private List<QueueItem> configs;

    public List<QueueItem> getConfigs() {
        return configs;
    }

    public void setConfigs(List<QueueItem> configs) {
        this.configs = configs;
    }
}