import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 15:10
 */
public class TimeTest {
	@Test
	public void getLocalTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		System.out.println(dateStr);
		Date  date = new Date();
		System.out.println(date);
	}
}
