package jdk;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/21
 * @Description:
 */
@Data
public class Upload implements Serializable {

    /**
     * 文件id
     */
    private String id;

    /**
     * 文件url
     */
    private String url;

    Integer picUrl;
}
