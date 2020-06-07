package cn.spk.user.dao;

import cn.spk.user.entity.FrameUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameUserMapper {

    int insert(FrameUser record);

    int insertSelective(FrameUser record);

    FrameUser selectByPrimaryKey(Integer userid);

    FrameUser selectByUsername(String username);

    int updateByPrimaryKeySelective(FrameUser record);

    int updateByPrimaryKey(FrameUser record);

    int deleteByPrimaryKey(Integer userid);

    List<FrameUser> query();

    List<FrameUser> selectByDeptid(Integer deptid);
}