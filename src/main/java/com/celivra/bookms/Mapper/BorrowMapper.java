package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {
    List<Borrow> getAllUserBorrows(String userid);
    boolean insertBorrow(Borrow borrow);
}
