package util;

import com.qf58.email.Email;
import com.qf58.email.EmailUtil;
import org.junit.Test;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/12/1
 * @Description:
 */
public class CopyTest {

    @Test
    public void copy() {

        Email email = new Email("新增商机数","测试");
        email.addReceiver("zhouyingtao@58qf.com");
        email.addReceiver("850374855@qq.com");
        try {
            EmailUtil.send(email);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
