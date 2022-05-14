package mod;

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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModTest {

    private  String token = null;
    private  String textType = null;

    //输入语料
    private  String sour = null;
    private  ArrayList<String> sourList = null;

    private static final String FILE_NAME = "D:\\Springlearning\\CUC_NLPToolsAPI_Call\\APITEST\\src\\main\\resources\\examples.txt";

    private static final String SINGLE_CALL_URL = "https://eae266ec46b040f9afb1ae22bef2676e.apig.cn-north-4.huaweicloudapis.com/v1/infers/240dd325-dfaf-4950-81a1-992f3aae0164/api/Mod";
    private static final String MULTI_CALL_URL = "https://eae266ec46b040f9afb1ae22bef2676e.apig.cn-north-4.huaweicloudapis.com/v1/infers/240dd325-dfaf-4950-81a1-992f3aae0164/api/MultiMod";

    // 单文本
    public ModTest(String token, String textType, String singleSour) {
        this.token = token;
        this.textType = textType;
        this.sour = singleSour;
    }
    // 多文本
    public ModTest(String token, String textType) {
        this.token = token;
        this.textType = textType;
        this.sourList = (ArrayList<String>) this.txtTosourList();
    }

    public StringEntity singleAPIBody(){
        //Body参数
        JSONObject json = new JSONObject();
        json.put("token", this.token);
        json.put("textType",this.textType);
        json.put("sour", this.sour);
        StringEntity reqEntity = new StringEntity(json.toString(),"UTF-8");
        return reqEntity;
    }

    private StringEntity multiAPIBody() {

        //Body参数
        JSONObject json = new JSONObject();
        json.put("token", this.token);
        json.put("textType",this.textType);
        json.put("sourList", this.sourList.toArray());
        StringEntity reqEntity = new StringEntity(json.toString(),"UTF-8");
        return reqEntity;
    }

    public void singelContextCall(String url) throws IOException {

        // 创建用于发送http请求的客户端对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建POST请求对象，并设置访问的地址
        HttpPost httpPost = new HttpPost(url);

        //构造POST请求消息头
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("X-Apig-AppCode", "2fbd1dee3ec64bf3a35c860027f00d84faa45118659841f3a28153759f78e2cc");

        // 构建并设置POST请求消息实体
        StringEntity reqEntity = this.singleAPIBody();
        httpPost.setEntity(reqEntity);

        // 发起POST请求，并返回Response对象
        CloseableHttpResponse response = httpClient.execute(httpPost);
        this.resultPrint(response);
    }

    public void multiContextCall(String url) throws IOException {

        // 创建用于发送http请求的httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建POST请求对象，并设置访问的地址
        HttpPost httpPost = new HttpPost(url);

        //构造POST请求消息头
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("X-Apig-AppCode", "2fbd1dee3ec64bf3a35c860027f00d84faa45118659841f3a28153759f78e2cc");

        // 构建并设置POST请求消息实体
        StringEntity reqEntity = this.multiAPIBody();
        httpPost.setEntity(reqEntity);

        // 发起POST请求，并返回Response对象
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 打印返回结果
        this.resultPrint(response);
    }

    public List<String> txtTosourList(){
        List<String> sourList = null;
        try {
            sourList = Files
                    .lines(Paths.get(FILE_NAME), Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split("\n")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  sourList;

    }

    public void resultPrint(CloseableHttpResponse response){
        try {
            //输出状态码
            System.out.println(response.getStatusLine());

            //响应消息实体
            HttpEntity ResEntity = response.getEntity();
            System.out.println(EntityUtils.toString(ResEntity,"UTF-8"));

            //释放资源
            EntityUtils.consume(ResEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {

        ModTest singleCall = new ModTest("showdoc", "string", "阳光明媚，大好时光");
        singleCall.singelContextCall(SINGLE_CALL_URL);

        ModTest multiCall = new ModTest("showdoc","string");
        multiCall.multiContextCall(MULTI_CALL_URL);
    }
}
