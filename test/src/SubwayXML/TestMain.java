package SubwayXML;

import java.util.ArrayList;
import java.util.HashMap;

public class TestMain {
	
	
	
	/*xml에는 모든 정보가 1열로 나열됨.
	 * test에 가져온 열차정보를 map객체로 저장함.
	 * 
	 * 
	 *
	 */
	
	public static void main(String[] args) throws Exception {
		
		HashMap<String, Object> subway;   //가져온 정보를 담을 멥
		String subwayLine = "1호선";       //알고싶은 호선 입력
		int No = 10;                      //알고싶은 객차 수
		
		//실험예제(무시해도 됨)
		/*Test t = new Test();
		String xml = t.resrClient(subway, No);
		System.out.println(xml);*/
		
		//파싱하는 객체 생성후 메소드 호출
		ParserSubWay paser = new ParserSubWay();
		ArrayList<HashMap<String, Object>> testList = paser.parserSubWay(subwayLine, No);
		
		//콘솔 출력
		for(int i=0; i<testList.size(); i++) {
			subway = testList.get(i);
			if(subway.get("rowNum")!=null) {
				System.out.println("no : "+subway.get("rowNum")+
									"\ntrain : "+subway.get("trainNo")+
									"\nstatnNm : "+subway.get("statnNm"));
			}else {
				System.out.println("Code : "+subway.get("code"));
				System.out.println("message : "+subway.get("message"));
				System.out.println("searchDate : "+subway.get("searchDate"));
				System.out.println("searchURL : "+subway.get("searchURL"));
				System.out.println("division : "+subway.get("division"));
			}
		}
	}

}
