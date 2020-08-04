package com.hyd.usercontrol.service.impl;

import com.hyd.system.SPMConfig;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.factory.ResultsFactory;
import com.hyd.system.util.StringUtils;
import com.hyd.usercontrol.info.OperateQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.mapper.UserControlMapper;
import com.hyd.usercontrol.service.UserControlService;
import com.hyd.usercontrol.util.UserGenreClassify;
import com.hyd.usercontrol.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public  class UserControlServiceImpl implements UserControlService {


    @Autowired
    UserControlMapper userControlMapper;

    @Autowired
    StringUtils stringUtils;

    @Override
    public User initUserByCd(QueryUserControlinfo queryUserControlinfo) {
        try {
            return userControlMapper.initUserByCd(queryUserControlinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询用户信息失败");
        }
    }

    @Override
    public ResultFactory modifyinformation(User user) {
        try {
            userControlMapper.modifyinformation(user);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("修改用户信息失败");
        }
    }

    @Override
    public ProvinceVO initAllProvince() {
        try {
            return userControlMapper.initAllProvince(SPMConfig.getLocalPrev() + "000000");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询省失败");
        }
    }

    @Override
    public List<Organ> initOrganList(QueryUserControlinfo queryUserControlinfo) {
        try {
            if (queryUserControlinfo.getZoneCd() != "" && queryUserControlinfo.getZoneCd() != null) {
                int i = Integer.parseInt(queryUserControlinfo.getZoneCd());
                if (i % 1000000 == 0) {
                    queryUserControlinfo.setZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    queryUserControlinfo.setZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    queryUserControlinfo.setZoneCd(String.valueOf(i / 100));
                }
            }
            return userControlMapper.initOrganList(queryUserControlinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询机构失败");
        }
    }

    @Override
    public List<ProvinceVO> foundZoneByParCode(QueryUserinfo queryUserinfo) {
        try {
            return userControlMapper.foundZoneByParCode(queryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询失败");
        }

    }

    @Override
    public ProvinceVO getZoneByCd(QueryUserinfo queryUserinfo) {
        try {
            return userControlMapper.getZoneByCd(queryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询地区失败");
        }
    }






    @Override
    public Boolean isAccount(QueryUserinfo queryUserinfo) {
        try {
            return userControlMapper.isAccount(queryUserinfo)==0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询用户名失败");
        }
    }

    @Override
    public Boolean isidentity(QueryUserinfo queryUserinfo) {
        try {
            return userControlMapper.isidentity(queryUserinfo)==0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询身份证失败");
        }
    }


    @Override
    public Boolean isidentitys(QueryUserinfo queryUserinfo) {
        try {
            return userControlMapper.isidentitys(queryUserinfo)==0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询身份证失败");
        }
    }

    @Override
    public Boolean isUserRepetition(QueryUserinfo queryUserinfo) {
        try {
            return  userControlMapper.isUserRepetition(queryUserinfo)<2;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询县本1用户是否存在失败");
        }
    }
//
//    @Override
//    public Boolean isstartusing(QueryUserinfo queryUserinfo) {
//        try {
//            return  userControlMapper.isstartusing(queryUserinfo)==0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询用户是否激活失败");
//        }
//    }
//

    @Override
    public ResultFactory foundUser(User user) {
        try {
            user.setCd(stringUtils.getCd("U"));
            user.setUserroleCd(user.getCd()+user.getRoleCd());
            userControlMapper.foundUser(user);
            userControlMapper.foundUserRole(user);
            //直报2改变机构为医院
            if("Role010".equals(user.getRoleCd())){
                userControlMapper.updateOrganHospital(user.getOrganCd());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("添加用户失败");
        }
    }

    @Override
    public List<AddUser> queryByUserList(QueryUserinfo queryUserinfo) {
        try {
            return  userControlMapper.queryByUserList(queryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询用户列表失败");
        }
    }

    @Override
    public ProvinceVO queryParentZone(QueryUserinfo queryUserinfo) {
        try {
             return userControlMapper.queryParentZone(queryUserinfo);
        } catch (Exception e) {
             e.printStackTrace();
             throw new SqlException("查询父级地区失败");
         }
    }


    @Override
    public List<UserSelectRoleVO> userGenreClassify(Organ organ) {
        try {

            return  UserGenreClassify.foundSelectRoles(organ.getLevCd(),organ.getIfPerform(), organ.getIsBJ());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询用户类型失败");
        }
    }




    @Override
    public List<OperateUser> queryByUserOperateList(OperateQueryUserinfo operateQueryUserinfo) {
        try {
            if (operateQueryUserinfo.getZoneCd() != "" && operateQueryUserinfo.getZoneCd() != null) {
                int i = Integer.parseInt(operateQueryUserinfo.getZoneCd());
                if (i % 1000000 == 0) {
                    operateQueryUserinfo.setZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    operateQueryUserinfo.setZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    operateQueryUserinfo.setZoneCd(String.valueOf(i / 100));
                }
            }
            if (operateQueryUserinfo.getPsnZoneCd() != "" && operateQueryUserinfo.getPsnZoneCd() != null) {
                int i = Integer.parseInt(operateQueryUserinfo.getPsnZoneCd());
                if (i % 1000000 == 0) {
                    operateQueryUserinfo.setPsnZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    operateQueryUserinfo.setPsnZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    operateQueryUserinfo.setPsnZoneCd(String.valueOf(i / 100));
                }
            }
            return  userControlMapper.queryByUserOperateList(operateQueryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询用户操作列表失败");
        }
    }





    @Override
    public ResultFactory delOperate(QueryUserinfo queryUserinfo) {
        try {
            //删除用户
            userControlMapper.delOperate(queryUserinfo);
            //删除权限
            userControlMapper.delOperateRole(queryUserinfo);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("删除操作用户失败");
        }
    }


    @Override
    public OperateUser queryByOperateById(QueryUserinfo queryUserinfo) {
        try {
            return userControlMapper.queryByOperateById(queryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询用户失败");
        }
    }

    @Override
    public ResultFactory updateOperateById(OperateQueryUserinfo operateQueryUserinfo) {
        try {
            userControlMapper.updateOperateById(operateQueryUserinfo);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("修改用户失败");
        }
    }

    @Override
    public ResultFactory restUserPwd(QueryUserinfo queryUserinfo) {
        try {
            userControlMapper.restUserPwd(queryUserinfo);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("重置用户密码失败");
        }
    }

    @Override
    public ResultFactory activeStatusUser(QueryUserinfo queryUserinfo) {
        try {
            userControlMapper.activeStatusUser(queryUserinfo);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("操作失败");
        }
    }

    @Override
    public ResultFactory appAuthUser(QueryUserinfo queryUserinfo) {
        try {
            userControlMapper.appAuthUser(queryUserinfo);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("操作失败");
        }
    }

    @Override
    public ResultFactory deviceIDUser(QueryUserinfo queryUserinfo) {
        try {
            userControlMapper.deviceIDUser(queryUserinfo);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("操作失败");
        }
    }

    @Override
    public ResultsFactory initUserverify(QueryUserinfo queryUserinfo) {
        String errMsg="";
        try {
            userControlMapper.initUserverify(queryUserinfo);
            errMsg = queryUserinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            System.out.println(queryUserinfo.getReminder());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new SqlException(errMsg);
            }else {
                throw new SqlException("验证账号失败");
            }
        }
    }

    @Override
    public ResultsFactory initUpdateUserverify(QueryUserinfo queryUserinfo) {
        String errMsg="";
        try {
            userControlMapper.initUpdateUserverify(queryUserinfo);
            errMsg = queryUserinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            System.out.println(queryUserinfo.getReminder());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new SqlException(errMsg);
            }else {
                throw new SqlException("验证账号失败");
            }
        }
    }


    //验证管理员人数
    @Override
    public Boolean verificationRole(QueryUserinfo queryUserinfo) {
        String errMsg="";
        try {
            return  userControlMapper.verificationRole(queryUserinfo)==0;
        } catch (Exception e) {
            e.printStackTrace();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new SqlException(errMsg);
            }else {
                throw new SqlException("验证管理员人数失败");
            }
        }
    }

}
