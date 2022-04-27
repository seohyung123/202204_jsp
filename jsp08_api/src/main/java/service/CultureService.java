package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class CultureService {
    public Map<String, Object> cultureParsing(String name) {
    	Map<String, Object> map = new HashMap<>();
    	try {
        	// 서울시 문화 위치정보 명칭 검색
        	// http://data.seoul.go.kr/dataList/OA-149/A/1/datasetView.do;jsessionid=CF46055498B5F83AB7E316580D799772.new_portal-svr-21
        	String serviceKey = "46637968524a746837324241436853";
        	StringBuilder sb = new StringBuilder();
        	sb.append("http://openAPI.seoul.go.kr:8088/");
        	sb.append(serviceKey);
        	sb.append("/json/");
        	sb.append("SearchCulturalFacilitiesNameService/1/5/");
        	sb.append(name);
        	System.out.println(sb.toString());
        	
        	// conn요청 + 데이터 받기 
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString()); //문자열 데이터
            
            // --------------------------------------------------
            // json파싱 :json-simple-1.1.1.jar
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(sb.toString());
            object = (JSONObject) object.get("SearchCulturalFacilitiesNameService");
            JSONArray array = (JSONArray) object.get("row");
            object = (JSONObject)array.get(0); // 값이 한개만 있다
            System.out.println(object);
            
            // object를 map에 넣기 
            
            map.put("FAC_CODE", object.get("FAC_CODE"));
            map.put("SUBJCODE", object.get("SUBJCODE"));
            map.put("FAC_NAME", object.get("FAC_NAME"));
            map.put("CODENAME", object.get("CODENAME"));
            map.put("ADDR", object.get("ADDR"));
            System.out.println("map: " + map);
            
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return map;
    	
    	

    }
}
