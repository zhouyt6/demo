package jdk;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ZYT
 * @Date: 2019/04/24
 * @Description:
 */
@Data
public class AreaExport {

    @Excel(name = "序号")
    private int no;

    @Excel(name = "区域名称", orderNum = "2")
    private String areaName;

    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm", orderNum = "8")
    private Date createTime;

    public AreaExport() {
    }

    public AreaExport(int no, String areaName, Date createTime) {
        this.no = no;
        this.areaName = areaName;
        this.createTime = createTime;
    }
}
