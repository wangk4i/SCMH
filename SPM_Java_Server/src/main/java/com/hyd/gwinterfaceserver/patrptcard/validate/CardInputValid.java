package com.hyd.gwinterfaceserver.patrptcard.validate;

import com.hyd.gwinterfaceserver.patrptcard.info.CallBackMoveInput;
import com.hyd.gwinterfaceserver.patrptcard.info.PatInfoCallBackMoveRequest;
import com.hyd.gwinterfaceserver.patrptcard.info.PatMoveOut;
import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
import com.hyd.system.vo.ValidateVO;
import org.springframework.stereotype.Component;

@Component
public class CardInputValid {

    public ValidateVO PatMoveOutValid(PatMoveOut input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if(input.moveOutCd.isEmpty()||input.moveOutCd==null){
            result.setErrMessage("流转主键不能为空");
            return result;
        }
        result.setError(false);
        return result;
    }


    public ValidateVO PatInfoCallBackMoveRequestValid(PatInfoCallBackMoveRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj == null){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if (input.extInfoObj.psnCd.isEmpty()||input.extInfoObj.psnCd==null)
        {
            result.setErrMessage("操作员不能为空");
            return result;
        }
        if (input.extInfoObj.organCode.isEmpty() || input.extInfoObj.organCode==null)
        {
            result.setErrMessage("机构不能为空");
            return result;
        }
        result.setError(false);
        return result;
    }


    public ValidateVO callBackMove(CallBackMoveInput input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.moveInAndOutId == null){
            result.setErrMessage("入参不能为空");
            return result;
        }
        result.setError(false);
        return result;
    }



    public ValidateVO PatReportMoveOutRequestValid(PatReportMoveOutRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.getExtInfoObj() == null){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if (input.getExtInfoObj().psnCd.isEmpty()||input.getExtInfoObj().psnCd==null)
        {
            result.setErrMessage("操作员不能为空");
            return result;
        }
        if (input.getExtInfoObj().organCode.isEmpty() || input.getExtInfoObj().organCode==null)
        {
            result.setErrMessage("机构不能为空");
            return result;
        }
        if (input.getRptCardID().isEmpty()||input.getRptCardID()==null)
        {
            result.setErrMessage("患者报告卡编号不能为空");
            return result;
        }

        if(!"00".equals(input.getCurrentZone().substring(6))||Integer.parseInt(input.getCurrentZone().substring(0,6))%10000==0||Integer.parseInt(input.getCurrentZone().substring(0,6))%100==0)
        {
            result.setErrMessage("迁往地区必须为区县");
            return result;
        }

        result.setError(false);
        return result;
    }

    public ValidateVO PatReportDistributionBJValid(PatReportMoveOutRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.getExtInfoObj() == null){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if (input.getExtInfoObj().psnCd.isEmpty()||input.getExtInfoObj().psnCd==null)
        {
            result.setErrMessage("操作员不能为空");
            return result;
        }
        if (input.getExtInfoObj().organCode.isEmpty() || input.getExtInfoObj().organCode==null)
        {
            result.setErrMessage("机构不能为空");
            return result;
        }
        if (input.getCd().isEmpty()||input.getCd()==null)
        {
            result.setErrMessage("流转编号不能为空");
            return result;
        }
        if(!"00".equals(input.getCurrentZone().substring(6))||Integer.parseInt(input.getCurrentZone().substring(0,6))%10000==0||Integer.parseInt(input.getCurrentZone().substring(0,6))%100==0)
        {
            result.setErrMessage("迁往地区必须为区县");
            return result;
        }
        result.setError(false);
        return result;
    }


    public ValidateVO PatReportAcceptMoveInValid(PatReportMoveOutRequest input) {
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if (input.getCd().isEmpty()||input.getCd()==null)
        {
            result.setErrMessage("流转编号不能为空");
            return result;
        }
        if (input.getRptCardID().isEmpty() || input.getRptCardID()==null)
        {
            result.setErrMessage("报告卡编号不能为空");
            return result;
        }
        result.setError(false);
        return result;
    }



    /**
     * 日期格式检验
     * @param dateTime
     * @return
     */
    public boolean inspectDateTime(String dateTime) {
        try {
            if (dateTime != null && !dateTime.equals("")) {
                if (dateTime.length() == 8) {
                    // 闰年标志
                    boolean isLeapYear = false;
                    String year = dateTime.substring(0, 4);
                    String month = dateTime.substring(4, 6);
                    String day = dateTime.substring(6, 8);
                    int vYear = Integer.parseInt(year);
                    // 判断年份是否合法
                    if (vYear < 1900 || vYear > 2200) {
                        return false;
                    }
                    // 判断是否为闰年
                    if (vYear % 4 == 0 && vYear % 100 != 0 || vYear % 400 == 0) {
                        isLeapYear = true;
                    }
                    // 判断月份
                    // 1.判断月份
                    if (month.startsWith("0")) {
                        String units4Month = month.substring(1, 2);
                        int vUnits4Month = Integer.parseInt(units4Month);
                        if (vUnits4Month == 0) {
                            return false;
                        }
                        if (vUnits4Month == 2) {
                            // 获取2月的天数
                            int vDays4February = Integer.parseInt(day);
                            if (isLeapYear) {
                                if (vDays4February > 29)
                                    return false;
                            } else {
                                if (vDays4February > 28)
                                    return false;
                            }

                        }
                    } else {
                        // 2.判断非0打头的月份是否合法
                        int vMonth = Integer.parseInt(month);
                        if (vMonth != 10 && vMonth != 11 && vMonth != 12) {
                            return false;
                        }
                    }
                    // 判断日期
                    // 1.判断日期
                    if (day.startsWith("0")) {
                        String units4Day = day.substring(1, 2);
                        int vUnits4Day = Integer.parseInt(units4Day);
                        if (vUnits4Day == 0) {
                            return false;
                        }
                    } else {
                        // 2.判断非0打头的日期是否合法
                        int vDay = Integer.parseInt(day);
                        if (vDay < 10 || vDay > 31) {
                            return false;
                        }
                        int vMonth = Integer.parseInt(month);
                        if (vMonth == 4||vMonth == 6||vMonth == 9||vMonth == 11){
                            if (vDay > 30) {
                                return false;
                            }
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }






}
