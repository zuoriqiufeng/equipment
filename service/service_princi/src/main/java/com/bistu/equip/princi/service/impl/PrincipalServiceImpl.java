package com.bistu.equip.princi.service.impl;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.equip.equipment.client.EquipFeignClient;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.princi.mapper.PrincipalMapper;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.vo.principal.PrincipalBorrowVo;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import com.bistu.equip.vo.principal.PrincipalReturnVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 设备借用信息表的业务层
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 16:50
 */
@Service
public class PrincipalServiceImpl extends ServiceImpl<PrincipalMapper, PrincipalInfo> implements PrincipalService {
	
	@Autowired
	private EquipFeignClient equipFeignClient;
	
	/**
	 * 分页查询记录
	 * @param pageParam
	 * @param principalQueryVo
	 * @return
	 */
	@Override
	public IPage<PrincipalInfo> selectPage(Page<PrincipalInfo> pageParam, PrincipalQueryVo principalQueryVo) {
		// 获取查询参数
		String lendHuman = principalQueryVo.getLendHuman();
		String reHuman = principalQueryVo.getReHuman();
		Long uid = principalQueryVo.getUid();
		String userName = principalQueryVo.getUserName();
		QueryWrapper<PrincipalInfo> wrapper = new QueryWrapper<>();
		// 设置查询条件
		if(!StringUtils.isEmpty(lendHuman)) {
			wrapper.eq("lend_human", lendHuman);
		}
		if(!StringUtils.isEmpty(reHuman)) {
			wrapper.eq("re_human", reHuman);
		}
		if(!StringUtils.isEmpty(lendHuman)) {
			wrapper.eq("lend_human", lendHuman);
		}
		if(!StringUtils.isEmpty(uid)) {
			wrapper.eq("uid", uid);
		}
		
		if(!StringUtils.isEmpty(userName)) {
			wrapper.eq("user_name", userName);
		}
		// 封装信息
		Page<PrincipalInfo> principalInfoPage = baseMapper.selectPage(pageParam, wrapper);
		principalInfoPage.getRecords().forEach(this::codeExchange);
		return principalInfoPage ;

	}
	
	/**
	 * 上传教师签名接口
	 * @param uid
	 * @param equipId
	 * @param bytes
	 */
	@Override
	public void uploadImgTeacher(Long uid, Long equipId, byte[] bytes) {
		QueryWrapper<PrincipalInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("uid", uid);
		wrapper.eq("equip_id", equipId);
		PrincipalInfo result = baseMapper.selectOne(wrapper);
		// 判断数据库中是否有这条记录
		if(result == null) {
			result = new PrincipalInfo();
		}
		if(result.getLendTime() == null) {
			result.setLendTime(new Date());
		}
		result.setTecSign(bytes);

	}
	
	/**
	 * 获取全部的数据库数据
	 * @return
	 */
	@Override
	public List<PrincipalInfo> getAllInfo() {
		List<PrincipalInfo> principalInfos = baseMapper.selectList(null);
		return principalInfos;
	}
	
	/**
	 * 归还设备，补充数据库信息
	 * @param principalInfo
	 */
	@Override
	public void updatePrincipal(PrincipalInfo principalInfo) {
		UpdateWrapper<PrincipalInfo> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("uid", principalInfo.getUid());
		updateWrapper.eq("equip_id", principalInfo.getEquipId());
		baseMapper.update(principalInfo, updateWrapper);
	}
	
	/**
	 * 借用设备方法
	 * @param principalBorrowVo
	 */
	@Override
	public void borrow(PrincipalBorrowVo principalBorrowVo) {
		PrincipalInfo principalInfo = new PrincipalInfo();
		BeanUtils.copyProperties(principalBorrowVo, principalInfo);
		// 将base64图片转化成byte数组
		byte[] userSign = baseToByte(principalBorrowVo.getUserSign());
		byte[] leHumanSign = baseToByte(principalBorrowVo.getLeHumanSign());
		byte[] tecSign = baseToByte(principalBorrowVo.getTecSign());
		// 数据存储
		principalInfo.setTecSign(tecSign);
		principalInfo.setUserSign(userSign);
		principalInfo.setLeHumanSign(leHumanSign);
		equipFeignClient.modifyStatus(principalInfo.getEquipId(), 1);
		this.save(principalInfo);
	}
	
	/**
	 * 归还设备方法
	 * @param principalReturnVo
	 */
	@Override
	public void returnEquip(PrincipalReturnVo principalReturnVo) {
		PrincipalInfo principalInfo = new PrincipalInfo();
		BeanUtils.copyProperties(principalReturnVo,principalInfo);
		// 设置返还时间
		principalInfo.setReturnTime(new Date());
		// base64 转 byte
		byte[] reHumanSign = baseToByte(principalReturnVo.getReHumanSign());
		principalInfo.setReHumanSign(reHumanSign);
		UpdateWrapper<PrincipalInfo> wrapper = new UpdateWrapper<>();
		wrapper.eq("uid", principalInfo.getUid());
		wrapper.eq("equip_id", principalInfo.getEquipId());
		equipFeignClient.modifyStatus(principalInfo.getEquipId(), 0);
		baseMapper.update(principalInfo, wrapper);
	}
	
	/**
	 * base64 转 byte
	 * @param img
	 * @return
	 */
	private byte[] baseToByte(String img) {
		img = img.replaceAll(" ", "+");
		return DatatypeConverter.parseBase64Binary(img);
	}
	
	/**
	 * byte 转 base64
	 * @param img
	 * @return
	 */
	private String byteToBase(byte[] img) {
		return  DatatypeConverter.printBase64Binary(img);
	}
	
	
	/**
	 * 将数据库中的byte转换成Base64
	 * @param principalInfo
	 */
	private void codeExchange(PrincipalInfo principalInfo) {
		String reHumanSignBase = null;
		String returnStatus;
		String userSignBase = byteToBase(principalInfo.getUserSign());
		String tecSignBase = byteToBase(principalInfo.getTecSign());
		String leHumanSignBase = byteToBase(principalInfo.getLeHumanSign());
		if(principalInfo.getStatus() == 1) {
			reHumanSignBase = byteToBase(principalInfo.getReHumanSign());
			returnStatus = "已归还";
		} else {
			returnStatus = "未归还";
		}
		principalInfo.getParam().put("userSignBase", userSignBase);
		principalInfo.getParam().put("tecSignBase", tecSignBase);
		principalInfo.getParam().put("leHumanSignBase", leHumanSignBase);
		principalInfo.getParam().put("reHumanSignBase", reHumanSignBase);
		principalInfo.getParam().put("returnStatus", returnStatus);
	}
	
}
