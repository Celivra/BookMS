package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.WorkOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WorkOrderMapper {
    @Insert("insert into workorder(ordername, rank, content, userid) values(#{orderName}, #{rank}, #{content}, #{userId})")
    boolean addWorkOrder(WorkOrder workOrder);

    @Update("update workorder set id = #{id}, ordername = ${orderName}, rank = #{rank}, content = #{content}, reply = #{reply}, userid = #{userId}")
    boolean updateWorkOrder(WorkOrder workOrder);
}
