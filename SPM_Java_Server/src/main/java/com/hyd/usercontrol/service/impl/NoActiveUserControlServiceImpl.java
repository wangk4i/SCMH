package com.hyd.usercontrol.service.impl;

import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.util.StringUtils;
import com.hyd.usercontrol.info.NoActiveQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.mapper.NoActiveUserControllMapper;
import com.hyd.usercontrol.service.NoActiveUserControlService;
import com.hyd.usercontrol.vo.NoActiveUser;
import com.hyd.usercontrol.vo.Organ;
import com.hyd.usercontrol.vo.ProvinceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class NoActiveUserControlServiceImpl implements NoActiveUserControlService {
    @Autowired
    NoActiveUserControllMapper noActiveUserControllMapper;

    @Autowired
    StringUtils stringUtils;


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
            return noActiveUserControllMapper.initOrganList(queryUserControlinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询机构失败");
        }
    }

    @Override
    public List<NoActiveUser> queryByNoActiveUserList(NoActiveQueryUserinfo noActiveQueryUserinfo) {
        try {
            if (noActiveQueryUserinfo.getZoneCd() != "" && noActiveQueryUserinfo.getZoneCd() != null) {
                int i = Integer.parseInt(noActiveQueryUserinfo.getZoneCd());
                if (i % 1000000 == 0) {
                    noActiveQueryUserinfo.setZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    noActiveQueryUserinfo.setZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    noActiveQueryUserinfo.setZoneCd(String.valueOf(i / 100));
                }
            }
            if (noActiveQueryUserinfo.getPsnZoneCd() != "" && noActiveQueryUserinfo.getPsnZoneCd() != null) {
                int i = Integer.parseInt(noActiveQueryUserinfo.getPsnZoneCd());
                if (i % 1000000 == 0) {
                    noActiveQueryUserinfo.setPsnZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    noActiveQueryUserinfo.setPsnZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    noActiveQueryUserinfo.setPsnZoneCd(String.valueOf(i / 100));
                }
            }
            System.out.println(noActiveQueryUserinfo.getExtInfoObj());
            return noActiveUserControllMapper.queryByNoActiveUserList(noActiveQueryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询用户列表失败");
        }
    }

    @Override
    public NoActiveUser queryByNoActiveUserById(QueryUserinfo queryUserinfo) {
        try {
            return noActiveUserControllMapper.queryByNoActiveUserById(queryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询无活动用户失败");
        }
    }

    @Override
    public ResultFactory delNoActive(QueryUserinfo queryUserinfo) {
        try {
            //删除用户
            noActiveUserControllMapper.delNoActive(queryUserinfo);
            //删除权限
            noActiveUserControllMapper.delNoActiveRole(queryUserinfo);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("删除无活动用户失败");
        }
    }


    @Override
    public ProvinceVO queryParentZone(QueryUserinfo queryUserinfo) {
        try {
            return noActiveUserControllMapper.queryParentZone(queryUserinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询父级地区失败");
        }
    }
}
