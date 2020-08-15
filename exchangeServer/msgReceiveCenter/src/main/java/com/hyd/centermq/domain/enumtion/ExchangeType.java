package com.hyd.centermq.domain.enumtion;



/**
 * Created by xieshuai on 2020/6/1 11:06
 * 交换机类型
 */
public enum  ExchangeType {

      DIRECT("direct"),
      FANOUT("fanout"),
      TOPIC("topic");

      private final String value;

      ExchangeType(String value){
         this.value = value;
      }

      public String value() {
         return this.value;
      }



}
