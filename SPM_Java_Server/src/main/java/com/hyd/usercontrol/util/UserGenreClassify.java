package com.hyd.usercontrol.util;

import com.hyd.usercontrol.vo.UserSelectRoleVO;

import java.util.*;

//用户类型权限分类
public  class  UserGenreClassify {


        private static Map<String,List<UserSelectRoleVO>> roleConfigs=new HashMap<>();

        private static List<UserSelectRoleVO> templist;

        public static List<UserSelectRoleVO> foundSelectRoles(String levelCode,String ifPerform,String isBJ) {
               String keyStr = levelCode;
               List<UserSelectRoleVO> result = new ArrayList<>();
               if (!("1".equals(ifPerform) || "1".equals(isBJ))) {
                   result=new ArrayList<>();

                   keyStr = levelCode + "-isOther";
                   result = roleConfigs.get(keyStr);
                   System.out.println("其他"+result);
                   return result;
               }
               if ("1".equals(ifPerform) && "1".equals(isBJ)) {
                   result=new ArrayList<>();

                   List<UserSelectRoleVO> result1 = new ArrayList<>();
                   List<UserSelectRoleVO> result2 = new ArrayList<>();
                   //result1.addAll(result2)
                   //result1.stream().distinct().collect();

                   if("InsLevel002".equals(levelCode)){
                       if("1".equals(ifPerform)){
                           result=new ArrayList<>();
                           keyStr = levelCode + "-ifPerform";
                           result = roleConfigs.get(keyStr);
                           System.out.println("直报"+result);
                           return result;
                       }else {
                           return null;
                       }
                   }else {
                       keyStr = levelCode + "-ifPerform";
                       HashSet h1 = new HashSet(result1);
                       keyStr = levelCode + "-isBJ";
                       HashSet h2 = new HashSet(result2);
                       h2.removeAll(h1);
                       h1.addAll(h2);
                       ArrayList arrayList = new ArrayList<>(h1);
                       result = arrayList;
                       System.out.println("两个"+result);
                       return result;
                   }
               }
               if ("1".equals(ifPerform)) {
                   result=new ArrayList<>();

                   keyStr = levelCode + "-ifPerform";
                   result = roleConfigs.get(keyStr);
                   System.out.println("直报"+result);
                   return result;
               }
               if ("1".equals(isBJ)) {
                   result=new ArrayList<>();

                   keyStr = levelCode + "-isBJ";
                   result = roleConfigs.get(keyStr);
                   System.out.println("本级"+result);
                   return result;
               }

               return result;
        }

/*  Level 级别  Duty 职能
     InsLevel002 -5
     直报 本级
     zb   bj other
* */

               static {
                      /**
                       * 履行基层直报
                       */

                      templist = new ArrayList<>();
                      templist.add(new UserSelectRoleVO("zb1", "直报1(基层直报)","Role011"));
                      templist.add(new UserSelectRoleVO("zb2", "直报2(医院直报)","Role010"));
                      //省
                      roleConfigs.put("InsLevel002-ifPerform", templist);
                      //市
                      roleConfigs.put("InsLevel003-ifPerform", templist);
                      //县级
                      roleConfigs.put("InsLevel004-ifPerform", templist);
                      //乡级
                      roleConfigs.put("InsLevel005-ifPerform", templist);
                      //村级
                      roleConfigs.put("InsLevel006-ifPerform", templist);

                      /**
                       * 履行区县质控
                       */
                      //市级
                      templist = new ArrayList<>();
                      templist.add(new UserSelectRoleVO("zb2", "直报2(医院直报)","Role010"));
                      templist.add(new UserSelectRoleVO("qubj1", "区县本级1(数据质控员)","Role007"));
                      templist.add(new UserSelectRoleVO("qubj2", "区县本级2(数据浏览员)","Role009"));
                      templist.add(new UserSelectRoleVO("shibj1", "市级本级1(数据质控员)","Role004"));
                      templist.add(new UserSelectRoleVO("shibj2", "市级本级2(数据浏览员)","Role006"));
                      templist.add(new UserSelectRoleVO("shigly", "市级业务管理员","Role005"));
                      roleConfigs.put("InsLevel003-isBJ", templist);

                      templist = new ArrayList<>();
                      templist.add(new UserSelectRoleVO("zb2", "直报2(医院直报)","Role010"));
                      templist.add(new UserSelectRoleVO("qubj1", "区县本级1(数据质控员)","Role007"));
                      templist.add(new UserSelectRoleVO("qubj2", "区县本级2(数据浏览员)","Role009"));
                      templist.add(new UserSelectRoleVO("qugly", "县级业务管理员","Role008"));
                      //县级
                      roleConfigs.put("InsLevel004-isBJ", templist);
                      //乡级
                      roleConfigs.put("InsLevel005-isBJ", templist);


                      /**
                       * 其他
                       */
                      //省级
                      templist = new ArrayList<>();
                      templist.add(new UserSelectRoleVO("zb2", "直报2(医院直报)","Role010"));



                      templist.add(new UserSelectRoleVO("shibj1", "市级本级1(数据质控员)","Role004"));
                      templist.add(new UserSelectRoleVO("shibj2", "市级本级2(数据浏览员)","Role006"));
                      templist.add(new UserSelectRoleVO("shengbj1", "省级本级1(数据质控员)","Role001"));
                      templist.add(new UserSelectRoleVO("shengbj2", "省级本级2(数据浏览员)","Role003"));
                      templist.add(new UserSelectRoleVO("shenggly", "省级业务管理员","Role002"));
                      roleConfigs.put("InsLevel002-isOther", templist);

                      //市级
                      templist = new ArrayList<>();
                      templist.add(new UserSelectRoleVO("zb2", "直报2(医院直报)","Role010"));

                   templist.add(new UserSelectRoleVO("qubj2", "区县本级2(数据浏览员)","Role009"));
                   templist.add(new UserSelectRoleVO("qugly", "县级业务管理员","Role008"));

                      templist.add(new UserSelectRoleVO("shibj1", "市级本级1(数据质控员)","Role004"));
                      templist.add(new UserSelectRoleVO("shibj2", "市级本级2(数据浏览员)","Role006"));
                      templist.add(new UserSelectRoleVO("shigly", "市级业务管理员","Role005"));
                      roleConfigs.put("InsLevel003-isOther", templist);

                      templist = new ArrayList<>();
                      templist.add(new UserSelectRoleVO("zb2", "直报2(医院直报)","Role010"));
                      templist.add(new UserSelectRoleVO("qubj2", "区县本级2(数据浏览员)","Role009"));
                      templist.add(new UserSelectRoleVO("qugly", "县级业务管理员","Role008"));
                      //县级
                      roleConfigs.put("InsLevel004-isOther", templist);
                      //乡级
                      roleConfigs.put("InsLevel005-isOther", templist);

               }



}
