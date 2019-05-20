import lombok.Data;

import java.util.Date;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/10/31
 * @Description:
 */
@Data
public class Child extends PerSion {

    private String typeDesc;

    private Date startCreateTime;

    private Date endCreateTime;

    private Byte isTest;
}
