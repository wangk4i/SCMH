package com.hyd.batchprocess.domain.info;

import lombok.Data;

/**
 * Created by xieshuai on 2020/5/29 11:54
 */

@Data
public class MessageInfo {

    /**
     * 省网系统主键
     */
    private String id;

    /**
     * 地区编码
     */
    private String zone;

    /**
     * 机构编码
     */
    private String organ;

    /**
     * 消息类型
     */
    private Integer msgcate;

    /**
     * 档案编码  1.档案 2.随访 3.出院单 4 应急处置
     */
    private Integer msgtype;

    /**
     * 操作选项  1.新增 2.编辑 3.删除
     */
    private Integer msgaction;

    /**
     * 记录文件生成时间 开始生成xml赋值当前时间
     */
    private String generalFileTime;

    /**
     * 生成文件耗时  xml生成完毕赋值消耗时间
     */
    private String generalFileElapse;



}
