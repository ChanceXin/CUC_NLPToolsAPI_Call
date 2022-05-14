package bidui;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.util.resources.ParallelListResourceBundle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MultiBiDuiTest {
    public static void main(String[] args) throws URISyntaxException, IOException {


        CloseableHttpClient httpclient = HttpClients.createDefault();

        //输入url
        HttpPost httpPost = new HttpPost("https://eae266ec46b040f9afb1ae22bef2676e.apig.cn-north-4.huaweicloudapis.com/v1/infers/240dd325-dfaf-4950-81a1-992f3aae0164/api/MultiBidui");
        //Body参数
        JSONObject json = new JSONObject();
        ArrayList<ArrayList<String>> paraList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("中国传媒大学是教育部直属的首批“双一流”建设高校，“211工程”重点建设大学，“985优势学科创新平台”重点建设高校");
        list.add("中传是教育部直属的首批“双一流”建设高校，“211工程”重点建设大学，“985优势学科创新平台”重点建设高校。");
        paraList.add(list);
        json.put("token", "showdoc");
        json.put("sourParaList",paraList.toArray());

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
