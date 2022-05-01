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
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.ReturnRecord;
import com.bistu.equip.model.principal.Sign;
import com.bistu.equip.princi.mapper.PrincipalMapper;
import com.bistu.equip.princi.service.PrincipalFrontService;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.princi.service.ReturnRecordService;
import com.bistu.equip.princi.service.SignService;
import com.bistu.equip.vo.principal.PrincipalBorrowVo;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import com.bistu.equip.vo.principal.PrincipalReturnVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * 设备借用信息表的业务层
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 16:50
 */
@Slf4j
@Service
@Transactional // 事务注解
public class PrincipalServiceImpl extends ServiceImpl<PrincipalMapper, PrincipalInfo> implements PrincipalService {
	
	@Autowired
	private EquipFeignClient equipFeignClient;
	
	@Autowired
	private SignService signService;
	
	@Autowired
	private ReturnRecordService returnRecordService;
	
	@Autowired
	private PrincipalFrontService principalFrontService;
	/**
	 * 分页查询记录
	 * @param pageParam
	 * @param principalQueryVo
	 * @return
	 */
	@Override
	public IPage<PrincipalInfo> selectPage(Page<PrincipalInfo> pageParam, PrincipalQueryVo principalQueryVo) {
		// 获取查询参数
		String equipName = principalQueryVo.getEquipName();
		Integer borrowTime = principalQueryVo.getBorrowTime();
		Integer status = principalQueryVo.getStatus();
		String lendHuman = principalQueryVo.getLendHuman();
		String reHuman = principalQueryVo.getReHuman();
		Long uid = principalQueryVo.getUid();
		String userName = principalQueryVo.getUserName();
		QueryWrapper<PrincipalInfo> wrapper = new QueryWrapper<>();
		// 设置查询条件
		
		if(!StringUtils.isEmpty(lendHuman)) {
			wrapper.like("lend_human", lendHuman);
		}
		if(!StringUtils.isEmpty(reHuman)) {
			wrapper.like("re_human", reHuman);
		}
		
		if(!StringUtils.isEmpty(uid)) {
			wrapper.eq("uid", uid);
		}
		
		if(!StringUtils.isEmpty(userName)) {
			wrapper.like("user_name", userName);
		}
		
		if(!StringUtils.isEmpty(status)) {
			wrapper.eq("status", status);
		}
		
		if(!StringUtils.isEmpty(borrowTime)) {
			wrapper.eq("borrow_time", borrowTime);
		}
		
		if(!StringUtils.isEmpty(equipName)) {
			wrapper.like("equip_name", equipName);
		}
		
		// 封装信息
		log.info("借用记录数据封装......");
		Page<PrincipalInfo> principalInfoPage = baseMapper.selectPage(pageParam, wrapper);
		principalInfoPage.getRecords().forEach(this::packageInfo);
		
		return principalInfoPage ;
	}
	
	
	/**
	 * 获取待归还的设备订单
	 * @return
	 */
	@Override
	public List<PrincipalInfo> getReturnedData(){
		QueryWrapper<PrincipalInfo> wrapper = new QueryWrapper<>();
		wrapper.le("return_count", 3);
		wrapper.eq("status", 1);
		List<PrincipalInfo> principalInfos = baseMapper.selectList(wrapper);
		return null;
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
	 * 借用设备方法
	 * @param principalBorrowVo
	 */
	@Override
	public void borrow(PrincipalBorrowVo principalBorrowVo) {
		// 后台数据信息
		PrincipalInfo principalInfo = new PrincipalInfo();
		// 前台数据信息
		PrincipalInfoFront principalInfoFront = new PrincipalInfoFront();
		BeanUtils.copyProperties(principalBorrowVo, principalInfo);
		// 存储基本信息
		principalInfo.setStatus(0);
		principalInfo.setEstimateReturnTime(this.getReturnTime(new Date(), principalInfo.getBorrowTime()));
		
		// 将后台信息复制到前台
		BeanUtils.copyProperties(principalInfo, principalInfoFront);
		baseMapper.insert(principalInfo);
		// 获取到插入之后的id
		
		Long id = principalInfo.getId();
		// 归还记录类，记录归还时间
		ReturnRecord returnRecord = new ReturnRecord();
		// 签名类，存储签名
		Sign sign = new Sign();
		
		// 设置对应的记录
		sign.setPrincipalId(id);
		returnRecord.setPrincipalId(id);
		principalInfoFront.setPrincipalId(id);
		
		// 将base64图片转化成byte数组
		byte[] userSign = baseToByte(principalBorrowVo.getUserSign());
		byte[] leHumanSign = baseToByte(principalBorrowVo.getLeHumanSign());
		byte[] tecSign = baseToByte(principalBorrowVo.getTecSign());
		// 数据存储
		sign.setLeHumanSign(leHumanSign);
		sign.setUserSign(userSign);
		sign.setTecSign(tecSign);
		returnRecord.setReturnCount(principalInfo.getBorrowTime());
		returnRecord.setStatus(0);
		principalInfoFront.setReturnCount(principalInfo.getBorrowTime());
		// 修改设备状态
		equipFeignClient.modifyStatus(principalInfo.getEquipId(), 1);
		// 插入数据
		signService.save(sign);
		returnRecordService.save(returnRecord);
		principalFrontService.save(principalInfoFront);
	}
	
	/**
	 * 归还设备方法
	 * @param principalReturnVo
	 */
	@Override
	public void returnEquip(PrincipalReturnVo principalReturnVo, Long id) {
		PrincipalInfo principalInfo = baseMapper.selectById(id);
		PrincipalInfoFront principalInfoFront = principalFrontService.getByPrincipalId(id);
		Sign sign = signService.getByPrincipalId(id);
		ReturnRecord returnRecord = returnRecordService.getByPrincipalId(id);
//		BeanUtils.copyProperties(principalReturnVo, principalInfo);
		
		principalInfo.setReHuman(principalReturnVo.getReHuman());
		principalInfo.setReUsername(principalReturnVo.getReUsername());
		
		// 设置返还时间和状态
		principalInfo.setActualReturnTime(new Date());
		principalInfo.setStatus(1);
		returnRecord.setStatus(1);
		principalInfoFront.setStatus(1);
		// base64 转 byte
		byte[] reHumanSign = baseToByte(principalReturnVo.getReHumanSign());
		byte[] reUserSign = baseToByte(principalReturnVo.getReUserSign());
		sign.setReHumanSign(reHumanSign);
		sign.setReUserSign(reUserSign);
		
		// 更新数据库信息
		equipFeignClient.modifyStatus(principalInfo.getEquipId(), 0);
		
		baseMapper.updateById(principalInfo);
		signService.updateById(sign);
		returnRecordService.updateById(returnRecord);
		principalFrontService.updateById(principalInfoFront);
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
	 * 封装数据信息
	 * 并将数据库中的byte转换成Base64
	 * @param principalInfo
	 */
	private void packageInfo(PrincipalInfo principalInfo) {
		// 获取id,获取签名信息
		Long id = principalInfo.getId();
		Sign sign = signService.getByPrincipalId(id);
		ReturnRecord returnRecord = returnRecordService.getByPrincipalId(id);
		Integer status = principalInfo.getStatus();
		
		String reHumanSignBase = null;
		String reUserSignBase = null;
		String userSignBase = byteToBase(sign.getUserSign());
		String tecSignBase = byteToBase(sign.getTecSign());
		String leHumanSignBase = byteToBase(sign.getLeHumanSign());
		String returnStatus;  //返回状态
		if(status == 1) {
			reHumanSignBase = byteToBase(sign.getReHumanSign());
			reUserSignBase = byteToBase(sign.getReUserSign());
			returnStatus = "已归还";
		} else {
			returnStatus = "未归还";
		}
		principalInfo.getParam().put("returnStatus", returnStatus);
		
		// 将base64编码放入到数据中
		principalInfo.getParam().put("reUserSignBase", reUserSignBase);
		principalInfo.getParam().put("userSignBase", userSignBase);
		principalInfo.getParam().put("tecSignBase", tecSignBase);
		principalInfo.getParam().put("leHumanSignBase", leHumanSignBase);
		principalInfo.getParam().put("reHumanSignBase", reHumanSignBase);
		principalInfo.getParam().put("returnCount", returnRecord.getReturnCount());
	}
	
	
 
	/**
	 * 计算归还日期
	 * @param now
	 * @param borrowTime
	 * @return
	 */
	private  Date getReturnTime(Date now, Integer borrowTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_YEAR, borrowTime);
		Date returnTime = calendar.getTime();
		return returnTime;
	}
	
}
