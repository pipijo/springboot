package com.yunhe.mapper;

import com.yunhe.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/6/006 11:59
 */
@Mapper
public interface LogMapper {

    /**
     * 查询所有日志
     * @return 日志记录
     */
    @Select("select * from syslog")
    List<SysLog> findAll();

    /**
     * 添加日志记录
     * @param sysLog 日志信息
     */
    @Insert("insert into syslog values (null,#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void addLog(SysLog sysLog);
}
