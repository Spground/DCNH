package dcnh.globalInfo;

public enum UserPermission implements GenericEnum{
	SUPERADMIN(1,"superAdmin"),
	SCHOOLADMIN(2,"schoolAdmin"),
	JUDGE(3,"judge");
	
	private int code;
	
	private String name;

	UserPermission(int code,String name){
		this.code = code;
		this.name = name;
	}
	
	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public static UserPermission stringToEnum(String name){
		UserPermission[] permissions = UserPermission.values();
		for(UserPermission permission : permissions){
			if(permission.getName().equals(name)){
				return permission;
			}
		}
		return null;
	}
}
