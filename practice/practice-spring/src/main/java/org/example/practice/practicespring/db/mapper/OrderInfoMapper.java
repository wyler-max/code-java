package org.example.practice.practicespring.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.practice.practicespring.db.model.OrderInfo;

@Mapper
public interface OrderInfoMapper {
    List<OrderInfo> selectAll();

    int insert(OrderInfo orderInfo);
}
