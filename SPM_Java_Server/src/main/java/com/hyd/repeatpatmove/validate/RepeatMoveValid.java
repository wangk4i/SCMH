package com.hyd.repeatpatmove.validate;

import com.hyd.repeatpatmove.info.RepeatPatApplyInfo;
import com.hyd.repeatpatmove.vo.ValidateVO;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/4 10:46
 */
@Component
public class RepeatMoveValid {

    public ValidateVO RepeatInfoValid(RepeatPatApplyInfo input){
        ValidateVO result = new ValidateVO();
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        result.setError(false);
        return result;

    }

}
