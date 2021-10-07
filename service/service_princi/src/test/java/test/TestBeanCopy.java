package test;

import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.vo.principal.PrincipalBorrowVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/4 - 19:52
 */
public class TestBeanCopy {
	
	@Test
	public void Test1() {
		PrincipalInfo principalInfo = new PrincipalInfo();
		
		
		PrincipalBorrowVo principalBorrowVo = new PrincipalBorrowVo();
		principalBorrowVo.setUid(1L);
		principalBorrowVo.setUsername("asdas");
		principalBorrowVo.setBorrowTime(123);
		principalBorrowVo.setEquipId(1231L);
		principalBorrowVo.setLeHumanSign("asdasd");
		principalBorrowVo.setTecSign("asdasd");
		
		BeanUtils.copyProperties(principalBorrowVo, principalInfo);
		System.out.println(principalInfo);
	}
}
