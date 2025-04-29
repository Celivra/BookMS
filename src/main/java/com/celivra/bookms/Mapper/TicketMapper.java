package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Insert("insert into ticket(ticketname, ticketrank, content, userid) values(#{ticketName}, #{ticketRank}, #{content}, #{userId})")
    boolean addTicket(Ticket ticket);

    @Update("update ticket set id = #{id}, ticketname = #{ticketName}, ticketrank = #{ticketRank}, content = #{content}, reply = #{reply}, userid = #{userId}")
    boolean updateTicket(Ticket ticket);

    //根据用户id查找工单
    @Select("select * from ticket where userid = #{userId}")
    List<Ticket> getTicket(String userId);

    //查找未回复的工单
    @Select("select * from ticket where reply is NULL")
    List<Ticket> getNoReplyTicket();
}
