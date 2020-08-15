package com.hyd.centermq.domain.info;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by xieshuai on 2020/5/29 11:54
 */

@Data
public class MessageInfo {

    @JSONField(name="id")
    private String id;

    @JSONField(name="zone")
    private String zone;

    @JSONField(name = "organ")
    private String organ;

    @JSONField(name = "msgcate")
    private Integer msgcate;

    private Integer msgtype;

    private Integer msgaction;

    private ExtensionInfo extensionInfo;

}
