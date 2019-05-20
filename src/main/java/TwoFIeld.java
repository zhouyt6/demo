import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/2/26
 * @Description:
 */
@Data
public class TwoFIeld {
    CloseableHttpClient closeableHttpClient;
    HttpContext localContext;
}
