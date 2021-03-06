import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlParsing_hospital {

	private static String getTagValue(String tag, Element eElement) {
		try{
	        String result = eElement.getElementsByTagName(tag).item(0).getTextContent();
	        return result;
	    } catch(NullPointerException e){
	        return "NullPointer Error";
	    } catch(Exception e){
	        return "Other Error";
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
				String spclAdmTyCd = "A0"; //기관코드 	A0: 국민안심병원 97: 코로나검사 실시기관 99: 코로나 선별진료소 운영기관
				String ServiceKey = "rl%2B8bqQgAXlgml1MRoJIqGc1YcMKT31NQdmV2graSOPOnxBBdSAAtnKp%2F7XR54yLXVpvKhTnv7UhUw%2FTBjqw9Q%3D%3D";
				String url = "http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?serviceKey=" 
				+ ServiceKey + "&pageNo=1&numOfRows=1&spclAdmTyCd=" + spclAdmTyCd;
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				NodeList nList1 = doc.getElementsByTagName("body");
				Element eElement = (Element) nList1.item(0);
				String getnum = getTagValue("totalCount", eElement);
				System.out.println(getnum);
				
				url = "http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?serviceKey=rl%2B8bqQgAXlgml1MRoJIqGc1YcMKT31NQdmV2graSOPOnxBBdSAAtnKp%2F7XR54yLXVpvKhTnv7UhUw%2FTBjqw9Q%3D%3D&pageNo=1&numOfRows="+ getnum +"&spclAdmTyCd=A0";

				doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();
				NodeList nList2 = doc.getElementsByTagName("item");
				
				for(int i = 0; i < nList2.getLength(); i++){		
					Node nNode = nList2.item(i);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
										
						Element eElement2 = (Element) nNode;
						System.out.println("######################");
						
						System.out.println("시/군/구 : " + getTagValue("sgguNm", eElement2));
						System.out.println("도 : " + getTagValue("sidoNm", eElement2));
						System.out.println("전화번호 : " + getTagValue("telno", eElement2));
						System.out.println("시설명  : " + getTagValue("yadmNm", eElement2));
					}
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
