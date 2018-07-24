package naverapi;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.hottae.dao.MemberDAO;
import com.hottae.dto.MemberVO;

public class NaverAPI {

	public String setAccess(String access_token) {
		String token = access_token;		
        String header = "Bearer " + token;
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode == 200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer sbr = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
            	sbr.append(inputLine);
            }
            br.close();
            JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)parser.parse(sbr.toString());
			JSONObject res = (JSONObject) jsonData.get("response");
			String id = res.get("id").toString();
			String gender = "";
			if(res.get("gender").toString().equals("M")) {
				gender = "남";
			} else if(res.get("gender").toString().equals("F")) {
				gender = "여";
			}
			String email = res.get("email").toString();
			String name = res.get("name").toString();
			String nickname = res.get("nickname").toString();
			MemberDAO dao = MemberDAO.getInstance();
			MemberVO member = dao.info(id);
			if(member == null){
				MemberVO m = new MemberVO();
				m.setMemID(id);
				m.setMemGender(gender);
				m.setMemEmail(email);
				m.setMemName(name);
				m.setNickName(nickname);
				m.setMemPW("NaverID_Password");
				int a = dao.join(m);
				if(a == 1){
					System.out.println("회원가입 성공");
					return id;
				} else {
					System.out.println("DB에러");
					return null;
				}
			} else {
				System.out.println("이미 회원내역 존재");
				return id;
			}
        } catch (Exception e) {
            System.out.println(e);
        }
    	return null;
    }
	
	public ArrayList<NaverItems> naverSearch(String data, String select){
		ArrayList<NaverItems> list = new ArrayList<>();
		String clientId = "PosIlAzjN0LHllBr01fD";
        String clientSecret = "mCvxBuBBZ1";//
        try {
        	int display = 50; 
            String text = URLEncoder.encode(data, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/"+select+"?query="+ text + "&display=" + display;
            
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; //.xml 버전
            System.out.println("url : " + apiURL);
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            // System.out.println(response.toString());
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject)parser.parse(response.toString());
            // System.out.println(json);
            JSONArray items = (JSONArray)json.get("items");
            // System.out.println(items);
            if(select.equals("blog")) {
	            for(int i=0; i<items.size(); i++) {
	            	JSONObject i_data = (JSONObject) items.get(i);
	            	NaverItems ni = new NaverItems();
	            	ni.setTitle(i_data.get("title").toString());
	            	ni.setBloggerName(i_data.get("bloggername").toString());
	            	ni.setBloggerLink(i_data.get("bloggerlink").toString());
	            	ni.setDescription(i_data.get("description").toString());
	            	ni.setLink(i_data.get("link").toString());
	            	ni.setPostDate(i_data.get("postdate").toString());
	            	list.add(ni);
	            }
            } else if(select.equals("movie")) {
            	for(int i=0; i<items.size(); i++) {
            		JSONObject i_data = (JSONObject) items.get(i);
                	NaverItems ni = new NaverItems();
                	ni.setTitle(i_data.get("title").toString());
                	ni.setLink(i_data.get("link").toString());
                	ni.setImage(i_data.get("image").toString());
                	ni.setSubtitle(i_data.get("subtitle").toString());
                	ni.setPubDate(i_data.get("pubDate").toString());
                	ni.setDirector(i_data.get("director").toString());
                	ni.setActor(i_data.get("actor").toString());
                	ni.setUserRating(i_data.get("userRating").toString());
                	list.add(ni);
            	}
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e);
        }
		return list;
	}
}
