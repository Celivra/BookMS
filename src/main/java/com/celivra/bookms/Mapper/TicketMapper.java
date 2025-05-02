package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Insert("insert into ticket(ticketname, ticketrank, content, userid, createDate, replyDate, status, isclosed) " +
            "values(#{ticketName}, #{ticketRank}, #{content}, #{userId}, #{createDate}, #{replyDate}, #{status}, #{isClosed})")
    boolean addTicket(Ticket ticket);

    @Update("update ticket set ticketname = #{ticketName}, ticketrank = #{ticketRank}, " +
            "content = #{content}, reply = #{reply}, userid = #{userId}, " +
            "status = #{status}, createDate = #{createDate}, replyDate = #{replyDate}, isclosed = #{isClosed} " +
            "where id = #{id}")
    boolean updateTicket(Ticket ticket);

    @Delete("delete from ticket where userid = #{userId}")
    boolean deleteTicket(String userId);

    @Select("select * from ticket where id = #{id}")
    Ticket getTicketById(String id);

    //根据用户id查找工单
    @Select("select * from ticket where userid = #{userId}")
    List<Ticket> getAllTicket(String userId);

    //查找未回复的工单
    @Select("select * from ticket")
    List<Ticket> getAllTicket();
}
