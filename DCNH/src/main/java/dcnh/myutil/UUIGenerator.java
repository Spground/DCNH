package dcnh.myutil;

import java.util.UUID;

public class UUIGenerator {
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-","");
		return uuidStr;
	}
}
