package util;

import lombok.Data;

import java.util.List;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/12/1
 * @Description:
 */
@Data
public class One {

    public One() {
    }

    public One(Integer sort, List<Long> suppliers) {
        this.sort = sort;
        this.suppliers = suppliers;
    }

    private Integer sort;
    /**
     * 商家id
     */
    private List<Long> suppliers;

}
