package com.bistu.equip.equipment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bistu.equip.model.equipment.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 16:23
 */
@Mapper
@Component
public interface EquipmentMapper extends BaseMapper<Equipment> {
}
