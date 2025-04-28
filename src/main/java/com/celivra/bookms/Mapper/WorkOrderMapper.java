package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.WorkOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WorkOrderMapper {
    @Insert("insert into workorder(ordername, rank, content) values(#{orderName}, #{rank}, #{content})")
    boolean addWorkOrder(WorkOrder workOrder);

    @Update("update workorder set id = #{id}, ordername = ${orderName}, rank = #{rank}, content = #{content}, reply = #{reply}")
    boolean updateWorkOrder(WorkOrder workOrder);
}
