package titleDang;

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
import java.util.ArrayList;

public class MultiTitleDangTest {
    public static void main(String[] args) throws URISyntaxException, IOException {


        CloseableHttpClient httpclient = HttpClients.createDefault();

        //输入url
        HttpPost httpPost = new HttpPost("https://eae266ec46b040f9afb1ae22bef2676e.apig.cn-north-4.huaweicloudapis.com/v1/infers/240dd325-dfaf-4950-81a1-992f3aae0164/api/MultiTitleDang");
        //Body参数
        JSONObject json = new JSONObject();
        ArrayList<String> list = new ArrayList<>();
        list.add("中国传媒大学2022年春季工作会议召开。相较往年，今年的春季工作会召开时间提前了将近一个月，实现早部署、早落实、早见效，激励教职员工以更快的速度、更好的状态投入到工作中。会议形式也进行了创新，由原来只是书记、校长作报告，拓展为除书记、校长全面部署学校工作外，各分管校领导也对各自分管工作进行布置安排，实现热传导效应，推动党建工作与学校工作的一体贯通。同时将参会人员扩大到全体教职工，有助于大家更好地了解学校发展和工作部署，统一理想、统一思想、统一步调。这些创新做法，受到了干部教师的一致欢迎和好评。\"");
        json.put("token", "string");
        json.put("sourList", list.toArray());
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
