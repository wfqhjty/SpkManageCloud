package cn.spk.user.service.impl;

import cn.spk.user.dao.FrameDeptMapper;
import cn.spk.user.entity.FrameDept;
import cn.spk.user.service.IFrameDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameDeptServiceImpl implements IFrameDeptService {

    @Autowired
    private FrameDeptMapper frameDeptMapper;

    @Override
    public List<FrameDept> query() {
        return frameDeptMapper.query();
    }
}
