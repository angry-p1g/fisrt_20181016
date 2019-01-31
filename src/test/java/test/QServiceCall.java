package test;

import com.alibaba.fastjson.JSON;
import com.gq.util.MD5Util;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

/**
 * 牵牛花服务调用测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QServiceCall {
    private static final String STG_URL = "http://stg-api.m-glory.net";
    private static final String ONLINE_URL = "http://openapi.m-glory.net";
    private static final String CREATE_ORDER_URL = "/qnh-oms/order/mddata/addorder";
    private static final String APP_ID_KEY = "appid";
    private static final String SIGN_KEY = "sign";
    private static final String APP_ID = "154235576655544";
    private static final String SECRET = "06bb239e-1ba4-41a3-8a03-84784ab5a4d7";

    @Test
    public void callCreateORCancelOrder() throws IOException {
        CloseableHttpClient client = new DefaultHttpClient();

        StringBuilder params = new StringBuilder(STG_URL+CREATE_ORDER_URL);
        //拼接请求参数
        params.append("?appid=").append(APP_ID).append("&body=").append(QServiceCallContants.CREATE_ORDER_JSONSTRING)
                .append("&qid=11111&timestamp=1389751221&version=2.3&secret=").append(SECRET);

        //获取签名
        String sign = MD5Util.MD5(params.toString());

        //转换JSON为map
        Map<String,Object> jsonMap = (Map)JSON.parse(QServiceCallContants.CREATE_ORDER_JSONSTRING);

        jsonMap.put(SIGN_KEY,sign);

        String resultJson = JSON.toJSONString(jsonMap);
        System.out.println("牵牛花请求参数："+resultJson);

        HttpPost post = new HttpPost(STG_URL+CREATE_ORDER_URL);
        post.setEntity(new StringEntity(resultJson, "UTF8"));
        post.setHeader(APP_ID_KEY, APP_ID);
        CloseableHttpResponse response = client.execute(post);
        System.out.println("牵牛花返回："+JSON.toJSONString(response));

    }
}
