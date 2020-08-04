package com.hyd.system.vo;

//数据字典
public class DictVo {
      private String dictTpCd;
      private String dictTpToJSON;

    @Override
    public String toString() {
        return "DictVo{" +
                "dictTpCd='" + dictTpCd + '\'' +
                ", dictTpToJSON='" + dictTpToJSON + '\'' +
                '}';
    }

    public String getDictTpCd() {
        return dictTpCd;
    }

    public void setDictTpCd(String dictTpCd) {
        this.dictTpCd = dictTpCd;
    }

    public String getDictTpToJSON() {
        return dictTpToJSON;
    }

    public void setDictTpToJSON(String dictTpToJSON) {
        this.dictTpToJSON = dictTpToJSON;
    }
}
