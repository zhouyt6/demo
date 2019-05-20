/**
 * @Author: Zhou YingTao
 * @Date: 2018/10/22
 * @Description:
 */
public enum PerEnum {
    YES(1,"是"),
    NO(2,"不是");

    private Integer code;

    private String desc;

    PerEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PerEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (PerEnum perEnum : PerEnum.values()) {
            if (perEnum.getCode().equals(code)) {
                return perEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
