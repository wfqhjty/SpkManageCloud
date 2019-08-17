package cn.spk.service.impl;

import cn.spk.dao.FrameUserMapper;
import cn.spk.entity.FrameUser;
import cn.spk.service.IFrameUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class FrameUserServiceImpl implements IFrameUserService {

    @Resource
    private FrameUserMapper frameUserMapper;

    @Override
    public FrameUser selectByNamePasswd(String username, String passwd) {
        return frameUserMapper.selectByNamePasswd(username, passwd);
    }

    @Override
    public FrameUser selectByPrimaryKey(Integer uid) {
        return frameUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public FrameUser selectByUsername(String username) {
        return frameUserMapper.selectByUsername(username);
    }

    @Override
    public void insert(FrameUser frameUser) {
        frameUserMapper.insert(frameUser);
    }

    @Override
    public void deleteByPrimaryKey(Integer uid) {
        frameUserMapper.deleteByPrimaryKey(uid);
    }
}
