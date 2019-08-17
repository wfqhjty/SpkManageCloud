package cn.spk.user.dao;

import cn.spk.user.entity.FrameDept;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameDeptMapper {
    int deleteByPrimaryKey(Integer deptid);

    int insert(FrameDept record);

    int insertSelective(FrameDept record);

    FrameDept selectByPrimaryKey(Integer deptid);

    int updateByPrimaryKeySelective(FrameDept record);

    int updateByPrimaryKey(FrameDept record);
}