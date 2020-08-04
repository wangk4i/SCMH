package com.hyd.gwinterfaceserver.patinfo.validate;

import com.hyd.gwinterfaceserver.patinfo.info.*;
import com.hyd.system.vo.ValidateVO;
import org.springframework.stereotype.Component;

@Component
public class InfoQueryValid {

    public ValidateVO ApplyRepeatInfoRequestValid(ApplyRepeatInfoRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null ){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if (input.extInfoObj.psnCd.isEmpty()||input.extInfoObj.psnCd==null)
        {
            result.setErrMessage("操作员不能为空");
            return result;
        }
        if (input.repeatInfoId.isEmpty()||input.repeatInfoId==null)
        {
            result.setErrMessage("申请编号不能为空");
            return result;
        }
        if(input.state.isEmpty()||input.state==null){
            result.setErrMessage("审核状态不能为空");
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO  RepeatCallBackApplyRequestValid(RepeatCallBackApplyRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null ){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if (input.extInfoObj.psnCd.isEmpty()||input.extInfoObj.psnCd==null)
        {
            result.setErrMessage("操作员不能为空");
            return result;
        }
        if (input.repeatInfoId.isEmpty()||input.repeatInfoId==null)
        {
            result.setErrMessage("申请编号不能为空");
            return result;
        }
        result.setError(false);
        return result;

    }


    public ValidateVO  repeatInfoApplyRequestValid(repeatInfoApplyRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null ){
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


    public ValidateVO  PatInfoRefuseMoveInRequestValid(PatInfoRefuseMoveInRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null ){
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

    public ValidateVO PatInfoMoveInBJValid(PatInfoMoveInRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null || input.moveCode==null){
            result.setErrMessage("入参不能为空");
            return result;
        }
        if (input.extInfoObj.psnCd.isEmpty()||input.extInfoObj.psnCd==null)
        {
            result.setErrMessage("操作员不能为空");
            return result;
        }
        if (input.extInfoObj.organCode.isEmpty() || input.extInfoObj.organCode==null||input.inOrganCode.isEmpty()||input.inOrganCode==null)
        {
            result.setErrMessage("机构不能为空");
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO PatInfoMoveInRequestValid(PatInfoMoveInRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null || input.moveCode==null || input.moveCode.isEmpty()){
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

    public ValidateVO PatInfoMoveOutRequestValid(PatInfoMoveOutRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null || input.moveOutInfo==null){
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

        if(input.moveOutInfo.patInfoCd.isEmpty()||input.moveOutInfo.patInfoCd==null){
            result.setErrMessage("入参MoveOutInfo.PatInfoCd不能为空");
            return result;
        }
        if(input.moveOutInfo.inZoneCd.isEmpty()||input.moveOutInfo.inZoneCd==null){
            result.setErrMessage("入参MoveOutInfo.InZoneCd不能为空");
            return result;
        }
        result.setError(false);
        return result;
    }




    public ValidateVO getRepeatInfoByIdcodeRequestValid(getRepeatInfoByIdcodeRequest input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null || input.extInfoObj==null || input.idcode.isEmpty() || input.idcode==null){
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

//    public ValidateVO intervalNotMoreThan31Days(String startDate, String endDate)  {
//        ValidateVO valid = new ValidateVO();
//        valid.setError(true);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        Date endTime  = null;
//        Date startTime = null;
//        try {
//            endTime = sdf.parse(endDate);
//            startTime = sdf.parse(startDate);
//        } catch (ParseException e) {
//            valid.setErrMessage("日期转码错误");
//            return valid;
//        }
//        long day = 60*1000*60*24; // 1天
//        if (startTime.getTime() > endTime.getTime()){
//            valid.setErrMessage("开始日期不得大于截止日期");
//            return valid;
//        }
//        if (endTime.getTime() - (day * 31) > startTime.getTime()) {
//            valid.setErrMessage("截止日期不超出开始日期31天");
//            return valid;
//        }
//        valid.setError(false);
//        return valid;
//    }




}
