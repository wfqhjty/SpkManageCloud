package cn.spk.data.serivce.impl;

import cn.spk.base.util.SpringContestUtils;
import cn.spk.data.serivce.IBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeanServiceImpl implements IBeanService {

    @Override
    public <T> List<T> getBeansByType(Class<T> requireType) {
        List<T> result = SpringContestUtils.getBeanListByClass(requireType);
        return result;
    }
}
