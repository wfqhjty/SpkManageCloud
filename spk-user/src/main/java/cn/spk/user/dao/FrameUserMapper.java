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

    FrameUser selectByNamePasswd(@Param("username") String username, @Param("passwd") String passwd);

    int updateByPrimaryKeySelective(FrameUser record);

    int updateByPrimaryKey(FrameUser record);

    int deleteByPrimaryKey(Integer userid);

    List<FrameUser> listFrameUsers();

    List<FrameUser> selectByDeptid(Integer deptid);
}