package cn.spk.user.dao;

import cn.spk.user.entity.FrameDept;
import cn.spk.user.entity.FrameUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameDeptMapper {
    int deleteByPrimaryKey(Integer deptid);

    int insert(FrameDept record);

    int insertSelective(FrameDept record);

    FrameDept selectByPrimaryKey(Integer deptid);

    int updateByPrimaryKeySelective(FrameDept record);

    int updateByPrimaryKey(FrameDept record);

    List<FrameDept> listFrameDepts();
}