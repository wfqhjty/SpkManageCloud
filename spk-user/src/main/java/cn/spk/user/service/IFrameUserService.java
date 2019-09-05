package cn.spk.user.service;


import cn.spk.user.entity.FrameUser;

import java.util.List;

public interface IFrameUserService {
    FrameUser selectByNamePasswd(String username, String passwd);

    FrameUser selectByPrimaryKey(Integer uid);

    FrameUser selectByUsername(String username);

    void insert(FrameUser frameUser);

    void deleteByPrimaryKey(Integer uid);

    List<FrameUser> listFrameUsers();

    List<FrameUser> selectByDeptid(Integer deptid);
}
