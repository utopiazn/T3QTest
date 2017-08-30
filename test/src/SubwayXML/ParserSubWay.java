package SubwayXML;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class ParserSubWay {
	
	//subwayName에는 원하는 호선 입력, No에는 알아보고싶은 열차 수
	public ArrayList<HashMap<String, Object>> parserSubWay(String subwayName, int No) throws ParserConfigurationException, UnsupportedEncodingException{
		
		ArrayList<HashMap<String, Object>> listSubway = new ArrayList<HashMap<String, Object>>();
		
		int startNo = 0;
		int lastNo = No;
		String subwayNameR = subwayName;
		String Line =URLEncoder.encode(subwayNameR,"UTF-8");
		
		String addr = "http://swopenapi.seoul.go.kr/api/subway/";
		String serviceKey = "5479556e78796b6339336b4d786e56";
		String parameter = "/xml/realtimePosition/";
		
		addr = addr + serviceKey + parameter + startNo + "/" + lastNo + "/" + Line;
		
		
		
		//URL정보로 xml파일을 가져오는 로직
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = f.newDocumentBuilder();
		
		Document xmlDoc = null;
		String paseUrl = addr;

		try {
			xmlDoc = parser.parse(paseUrl);
		}catch(SAXException | IOException e){
			e.printStackTrace();
		}
		
		//가져온 xml을 List<Map>에 넣는 로직
		Element root = xmlDoc.getDocumentElement();
		
		Node code = root.getElementsByTagName("code").item(0);
		String codeS = code.getTextContent();
		Node message = root.getElementsByTagName("message").item(0);
		
		HashMap<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("code", code.getTextContent());
		codeMap.put("message", message.getTextContent());
		codeMap.put("searchDate", new Date());
		codeMap.put("searchURL", paseUrl);
		codeMap.put("division", "실시간 지하철 위치");

		listSubway.add(codeMap);

		if(codeS.equals("INFO-000")){
			int length = root.getElementsByTagName("rowNum").getLength();
			
			for(int i=0; i<length; i++) {
				
				Node rowNum = root.getElementsByTagName("rowNum").item(i);
				Node selectedCount = root.getElementsByTagName("selectedCount").item(i);
				Node totalCount = root.getElementsByTagName("totalCount").item(i);
				Node subwayId = root.getElementsByTagName("subwayId").item(i);
				Node subwayNm = root.getElementsByTagName("subwayNm").item(i);
				Node statnId = root.getElementsByTagName("statnId").item(i);
				Node statnNm = root.getElementsByTagName("statnNm").item(i);
				Node trainNo = root.getElementsByTagName("trainNo").item(i);
				Node lastRecptnDt = root.getElementsByTagName("lastRecptnDt").item(i);
				Node recptnDt = root.getElementsByTagName("recptnDt").item(i);
				Node updnLine = root.getElementsByTagName("updnLine").item(i);
				Node statnTid = root.getElementsByTagName("statnTid").item(i);
				Node statnTnm = root.getElementsByTagName("statnTnm").item(i);
				Node trainSttus = root.getElementsByTagName("trainSttus").item(i);
				Node directAt = root.getElementsByTagName("directAt").item(i);
				Node lstcarAt = root.getElementsByTagName("lstcarAt").item(i);
				
				HashMap<String, Object> paerseSubway = new HashMap<String, Object>();
				paerseSubway.put("rowNum", rowNum.getTextContent());
				paerseSubway.put("selectedCount", selectedCount.getTextContent());
				paerseSubway.put("totalCount", totalCount.getTextContent());
				paerseSubway.put("subwayId", subwayId.getTextContent());
				paerseSubway.put("subwayNm", subwayNm.getTextContent());
				paerseSubway.put("statnId", statnId.getTextContent());
				paerseSubway.put("statnNm", statnNm.getTextContent());
				paerseSubway.put("trainNo", trainNo.getTextContent());
				paerseSubway.put("lastRecptnDt", lastRecptnDt.getTextContent());
				paerseSubway.put("recptnDt", recptnDt.getTextContent());
				paerseSubway.put("updnLine", updnLine.getTextContent());
				paerseSubway.put("statnTid", statnTid.getTextContent());
				paerseSubway.put("statnTnm", statnTnm.getTextContent());
				paerseSubway.put("trainSttus", trainSttus.getTextContent());
				paerseSubway.put("directAt", directAt.getTextContent());
				paerseSubway.put("lstcarAt", lstcarAt.getTextContent());
	
				listSubway.add(paerseSubway);
				
			}
		}else {
			System.out.println("pass");
		}
		
		//실행 완료 후 List<Map>을 리턴
		return listSubway;
	}
	
	
}
