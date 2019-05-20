import java.util.Objects;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/3/29
 * @Description:
 */
public class TongCheng {

    String hangye;

    String comp_name;

    String url;

    String guimo;

    String salary;

    String yaoqiu;

    String fuli;

    public String getHangye() {
        return hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGuimo() {
        return guimo;
    }

    public void setGuimo(String guimo) {
        this.guimo = guimo;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getYaoqiu() {
        return yaoqiu;
    }

    public void setYaoqiu(String yaoqiu) {
        this.yaoqiu = yaoqiu;
    }

    public String getFuli() {
        return fuli;
    }

    public void setFuli(String fuli) {
        this.fuli = fuli;
    }

    @Override
    public boolean equals(Object o) {
        TongCheng tongCheng = (TongCheng) o;
        return Objects.equals(comp_name.trim(), tongCheng.comp_name.trim());
    }

    @Override
    public int hashCode() {

        return Objects.hash(comp_name);
    }

    @Override
    public String toString() {
        return comp_name + "\t" +
                hangye + "\t" +
                guimo + "\t" +
                salary + "\t" +
                yaoqiu + "\t" +
                fuli + "\t" +
                url + "\r\n";
    }
}
