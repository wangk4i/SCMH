package com.hyd.repeatpatmove.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/3 17:27
 */
@Data
public class RepeatPatMoveInfo {
    private String zoneCd;
    private String organCd;
    private String patNam;
    @NotBlank
    private String idCode;
    private String genderCd;
    private String applyState;
    private ExtInfo extInfoObj;
}
