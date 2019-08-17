package cn.spk.service;


import cn.spk.entity.FrameUser;

public interface IFrameUserService {
    FrameUser selectByNamePasswd(String username, String passwd);

    FrameUser selectByPrimaryKey(Integer uid);

    FrameUser selectByUsername(String username);

    void insert(FrameUser frameUser);

    void deleteByPrimaryKey(Integer uid);
}
