import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentEeVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/10 - 15:09
 */
public class CopyTest {
	
	@Test
	public void test1() {
		Equipment equipment = new Equipment();
		
		equipment.setId(1231L);
		
		EquipmentEeVo equipmentEeVo = new EquipmentEeVo();
		
		BeanUtils.copyProperties(equipment, equipmentEeVo);
		
		System.out.println(equipmentEeVo);
	}
}
