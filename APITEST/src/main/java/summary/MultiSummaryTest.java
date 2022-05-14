package summary;

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

public class MultiSummaryTest {
    public static void main(String[] args) throws URISyntaxException, IOException {


        CloseableHttpClient httpclient = HttpClients.createDefault();

        //输入url
        HttpPost httpPost = new HttpPost("https://eae266ec46b040f9afb1ae22bef2676e.apig.cn-north-4.huaweicloudapis.com/v1/infers/240dd325-dfaf-4950-81a1-992f3aae0164/api/MultiSummary");
        //Body参数
        JSONObject json = new JSONObject();
        ArrayList<String> list = new ArrayList<>();

        list.add("\"吉林：境外输入奥密克戎BA.2造成本地传播\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3月12日，吉林省疫情防控工作新闻发布会通报，吉林省疾控中心对早期报告的珲春市、吉林市感染者以及辽源市、长春市、四平市等地送检的共90名感染者标本完成了新冠病毒的全基因组测序，均属于奥密克戎变异株BA.2进化分支。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"经疾控中心比对，病毒基因序列高度同源，结合流行病学调查情况，此次疫情为一起新的境外输入新冠病毒，引入吉林省造成的本地传播。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"山东：3条传播链为奥密克戎BA.2\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3月12日，从山东省疫情防控工作新闻发布会通报，除青岛市为奥密克戎BA.1.1进化分支外，其他威海、淄博、德州3市均为奥密克戎BA.2进化分支，经中国疾控中心比对，均不同源。山东省本轮疫情共有6个传播链条。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"浙江：奥密克戎BA.2迅速发展成主要流行株\\n\" ");

        list.add("吉林：境外输入奥密克戎BA.2造成本地传播\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3月12日，吉林省疫情防控工作新闻发布会通报，吉林省疾控中心对早期报告的珲春市、吉林市感染者以及辽源市、长春市、四平市等地送检的共90名感染者标本完成了新冠病毒的全基因组测序，均属于奥密克戎变异株BA.2进化分支。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"经疾控中心比对，病毒基因序列高度同源，结合流行病学调查情况，此次疫情为一起新的境外输入新冠病毒，引入吉林省造成的本地传播。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"山东：3条传播链为奥密克戎BA.2\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3月12日，从山东省疫情防控工作新闻发布会通报，除青岛市为奥密克戎BA.1.1进化分支外，其他威海、淄博、德州3市均为奥密克戎BA.2进化分支，经中国疾控中心比对，均不同源。山东省本轮疫情共有6个传播链条。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"浙江：奥密克戎BA.2迅速发展成主要流行株\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3月11日，浙江省新冠肺炎疫情防控工作新闻发布会通报，从病毒溯源看，奥密克戎变异株BA.2亚型迅速发展成为浙江省本土疫情的主要流行株。9起疫情中，7起为奥密克戎BA.2进化分支，2起为奥密克戎BA.1.1进化分支。显示出BA.2亚型较以往病毒株更容易造成人体的感染和传播，且隐匿性增加。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"苏州：无症状感染者为奥密克戎BA.2\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3月10日，苏州市疫情通报显示，无症状感染者经基因测序分析，均为奥密克戎BA.2进化分支，与外省市已有病例的病原高度同源。");

        json.put("token", "showdoc");

        json.put("sentenceNumber","3");
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
