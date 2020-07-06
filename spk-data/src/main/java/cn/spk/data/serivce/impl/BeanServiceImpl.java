package cn.spk.data.serivce.impl;

import cn.spk.common.util.SpringContextUtils;
import cn.spk.data.serivce.IBeanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeanServiceImpl implements IBeanService {

    @Override
    public <T> List<T> getBeansByType(Class<T> requireType) {
        List<T> result = SpringContextUtils.getBeanListByClass(requireType);
        return result;
    }
}
