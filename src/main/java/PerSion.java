/**
 * @Author: Zhou YingTao
 * @Date: 2018/10/22
 * @Description:
 */

public class PerSion {
    private  Integer id;
    private  Integer type;
    private String typeDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
//        PerEnum perEnum = PerEnum.getByCode(type);
//        this.typeDesc = perEnum != null?perEnum.getDesc():null;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public String toString() {
        return "PerSion{" +
                "id=" + id +
                ", type=" + type +
                ", typeDesc='" + typeDesc + '\'' +
                '}';
    }
}
