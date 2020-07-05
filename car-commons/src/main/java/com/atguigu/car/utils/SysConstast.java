package com.atguigu.car.utils;

public class SysConstast {
    public static final String LOGININ_ERROR_MESSAGE = "账号或者密码错误";
    // 用户状态
    public static final Integer AVAIABLE_TRUE = 1;
    public static final Integer AVAIABLE_FALSE = 0;

    // 用户类型
    public static final Integer USER_TYPE_SUPER = 1;
    public static final Integer USER_TYPE_NOMAL = 2;
    // 是否展开
    public static final Integer SPEEC_TRUE = 1;
    public static final Integer SPEEC_FALSE = 0;
    
    // 操作是否成功
    public static final Integer CODE_SUCCESS = 0;
    public static final Integer CODE_ERROR = -1;
    
    // 是否添加成功
    public static final String ADD_SUCCESS = "添加成功";
    public static final String ADD_ERROR = "添加失败";
    
    // 是否更改成功
    public static final String UPDATE_SUCCESS = "更改成功";
    public static final String UPDATE_ERROR = "更改失败";

    // 是否删除成功
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String DELETE_ERROR = "删除失败";

    // 是否重置成功
    public static final String RESET_SUCCESS = "重置成功";
    public static final String RESET_ERROR = "重置失败";

    // 是否分配成功
    public static final String DISPATCH_SUCCESS = "分配成功";
    public static final String DISPATCH_ERROR = "分配失败";

    // 是否可以获得
    public static final Integer AVAILABLE_TRUE = 1;
    public static final Integer AVAILABLE_FALSE = 0;

    // 状态码
    public static final Integer CODE_ZERO = 0;
    public static final Integer CODE_ONE = 1;
    public static final Integer CODE_TWO = 2;

    // 是否展开
    public static final Integer SPREAD_TRUE = 1;
    public static final Integer SPREAD_FALSE = 0;

     //用户默认密码
    public static final String USER_DEFAULT_PWD = "123456";

    // 临时文件标记
    public static final String FILE_UPLOAD_TEMP = "_temp";
    // 默认图片地址
    public static final Object DEFAULT_CAR_IMG = "images/defaultImage.jpg";

    // 出租单号前缀
    public static final String CAR_ORDER_CZ = "CZ";
    public static final String CAR_ORDER_JC = "JC";

    // 汽车出租状态
    public static final Integer CAR_RENT_TRUE = 1;
    public static final Integer CAR_RENT_FALSE = 0;

    // 汽车归还状态
    public static final Integer CAR_BACK_TRUE = 1;
    public static final Integer CAR_BACK_FALSE = 0;
}
