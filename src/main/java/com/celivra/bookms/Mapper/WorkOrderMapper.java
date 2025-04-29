package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.WorkOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WorkOrderMapper {
    @Insert("insert into workorder(ordername, orderrank, content, userid) values(#{orderName}, #{orderRank}, #{content}, #{userId})")
    boolean addWorkOrder(WorkOrder workOrder);

    @Update("update workorder set id = #{id}, ordername = ${orderName}, orderrank = #{orderRank}, content = #{content}, reply = #{reply}, userid = #{userId}")
    boolean updateWorkOrder(WorkOrder workOrder);

    //根据用户id查找工单
    @Select("select * from workorder where userid = #{userId}")
    List<WorkOrder> getWorkOrders(String userId);

    //查找未回复的工单
    @Select("select * from workorder where reply is NULL")
    List<WorkOrder> getNoReplyWorkOrders();
}
