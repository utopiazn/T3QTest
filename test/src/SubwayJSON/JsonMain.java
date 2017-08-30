package SubwayJSON;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonMain {
	
	//생성자
	public JsonMain(String subwayName, int No) throws Exception {
		
		JSONParser jsonparser = new JSONParser();
        
		//json을 파싱하는 로직
		JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl(subwayName, No));
        
        JSONObject json =  (JSONObject) jsonobject.get("errorMessage");
        JSONArray array = (JSONArray)jsonobject.get("realtimePositionList");
        
        String code = (String) json.get("code");
        System.out.println(code);
        
        for(int i = 0 ; i < array.size(); i++){
            
            JSONObject entity = (JSONObject)array.get(i);
            String trainNo = (String) entity.get("trainNo");
            System.out.println(trainNo);
        }
	}
	
	//URL에서 json으로 받아오는 로직
	private static String readUrl(String subwayName, int No) throws Exception {
        BufferedInputStream reader = null;
        
        int startNo = 0;
		int lastNo = No;
		String subwayNameR = subwayName;
		String Line =URLEncoder.encode(subwayNameR,"UTF-8");
		
		String addr = "http://swopenapi.seoul.go.kr/api/subway/";
		String serviceKey = "5479556e78796b6339336b4d786e56";
		String parameter = "/json/realtimePosition/";
		
		addr = addr + serviceKey + parameter + startNo + "/" + lastNo + "/" + Line;
		
        
        try {
            URL url = new URL(addr);
            reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader.read(b)) != -1){
              buffer.append(new String(b, 0, i));
            }
            
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
	
	public static void main(String[] args) {
        
		try {
            new JsonMain("1호선", 10);
        
		} catch (Exception e) {
        
			e.printStackTrace();
        
		}
    }
}
