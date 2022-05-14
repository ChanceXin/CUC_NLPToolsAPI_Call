package seg;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URISyntaxException;


public class SegTest {
    public static void main(String[] args) throws URISyntaxException, IOException {


        CloseableHttpClient httpclient = HttpClients.createDefault();

        //输入url
        HttpPost httpPost = new HttpPost("https://eae266ec46b040f9afb1ae22bef2676e.apig.cn-north-4.huaweicloudapis.com/v1/infers/94c8d63f-db0b-4f52-910c-551511807461/api/SegTag");
        //Body参数
        JSONObject json = new JSONObject();
        json.put("token", "showdoc");
        json.put("isTag","1");
        json.put("sour", "阳光明媚，大好时光");
        StringEntity ReqEntity = new StringEntity(json.toString(),"UTF-8");
        httpPost.setEntity(ReqEntity );
        //Header参数
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("X-Apig-AppCode", "2fbd1dee3ec64bf3a35c860027f00d84faa45118659841f3a28153759f78e2cc");


        //response
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            //输出状态码
            System.out.println(response.getStatusLine());
            //响应实体
            HttpEntity ResEntity = response.getEntity();
            System.out.println(EntityUtils.toString(ResEntity,"UTF-8"));
            //释放资源
            EntityUtils.consume(ResEntity);

        } finally {
            response.close();
        }


    }


}
