package cn.spk.data.serivce;

import java.util.List;

public interface IBeanService {
    <T> List<T> getBeansByType(Class<T> requireType);
}
