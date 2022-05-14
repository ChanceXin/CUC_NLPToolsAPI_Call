import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class test {
    @Test
    public void tst(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a","c");
        System.out.println(jsonObject.toString());

    }
}
