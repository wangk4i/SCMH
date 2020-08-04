package com.hyd.village.vo;

import com.hyd.system.info.Pageinfo;
import lombok.Data;

@Data
public class VillageUserManageVo extends Pageinfo{

      private String  Nam;
      private String  ReportOrgNam;
      private String  ReportZoneNam;
      private String  Phone;
      private String  CreTime;

}
