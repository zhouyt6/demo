import java.util.Objects;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/3/29
 * @Description:
 */
public class Entity58 {

    int total_page;

    String comp_name;

    String url;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
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
    public Entity58() {
    }
    public Entity58(int total_page,String comp_name, String url) {
        this.total_page = total_page;
        this.comp_name = comp_name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        Entity58 entity58 = (Entity58) o;
        return Objects.equals(comp_name.trim(), entity58.comp_name.trim());
    }

    @Override
    public int hashCode() {

        return Objects.hash(total_page, comp_name, url);
    }

    @Override
    public String toString() {
        return "Entity58{" +
                "total_page=" + total_page +
                ", comp_name='" + comp_name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
