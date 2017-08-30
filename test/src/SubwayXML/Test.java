package SubwayXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class Test {
	//subwayName에는 원하는 호선 입력, No에는 알아보고싶은 열차 수
	public String resrClient(String subwayName, int No) throws Exception{
		
		int startNo = 0;
		int lastNo = No;
		String subwayNm = subwayName;
		
		String addr = "http://swopenapi.seoul.go.kr/api/subway/";
		String serviceKey = "5479556e78796b6339336b4d786e56";
		String parameter = "/xml/realtimePosition/";
		
		
		String Line =URLEncoder.encode(subwayNm,"UTF-8");
		
		addr = addr + serviceKey + parameter + startNo + "/" + lastNo + "/" + Line;
		
		String inLine;
		String xml = "";
		
		try{
			URL url = new URL(addr);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			while((inLine = in.readLine()) != null)
				xml = inLine;
			in.close();
		}catch(IOException e) {
			System.out.println("오류");
		}
		
		
		
		
		
		return xml;
	}
	
}
