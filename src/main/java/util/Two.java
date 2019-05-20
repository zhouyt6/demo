package util;

import lombok.Data;

import java.util.List;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/12/1
 * @Description:
 */
@Data
public class Two {

    private Integer sort;
    /**
     * 商家id
     */
    private List<Long> suppliers;
}
