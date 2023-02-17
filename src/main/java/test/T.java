package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import gui.Gui;


public class T {
public static void main(String[] args) throws Exception {
		String str = Files.readString(new File("src/main/resources/text.txt").toPath());
//		System.out.println(str);
        

		String reg = "(<!-------- 게시판리스트시작 /------->)([\\s\\n\\S]+)(<!-------- 게시판리스트끝 /------->)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);

		String str2 = null;
		String air2 = "https://www.airportal.go.kr/life/airinfo/RaSkeFrmMain.jsp";

		
		if (matcher.find()) {
		    String matchedGroup = matcher.group(2);
		    System.out.println(matchedGroup);
		    
		    JEditorPane e = new JEditorPane();
//		    e.setContentType("text/HTML");
//		    e.setText(matchedGroup);
		    Gui.createFrame(new JLabel(matchedGroup));
		} else {
		    System.out.println("No match found.");
		}
		
		JTextField f = new JTextField(str);
		
		
//		String air = "https://www.airportal.go.kr/life/airinfo/RbHanList.jsp?gubun=c_getList&depArr=A&current_date=20230217&airport=RKSI&al_icao=&fp_id=";
//		String air2 = "https://www.airportal.go.kr/life/airinfo/RaSkeFrmMain.jsp";
		
//		URL url = new URL(air2);

//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
////        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//
//        int status = con.getResponseCode();
//
//        if (status == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            con.disconnect();
//            Matcher mt = pattern.matcher(content.toString());
//            System.out.println(content.toString());
//            if (mt.find()) {
//    		    String matchedGroup = matcher.group(2);
//    		    System.out.println(matchedGroup);
//    		} else {
//    		    System.out.println("No match found.");
//    		}
//            
//        } else {
//            System.out.println("HTTP error code: " + status);
//        }
        
        
    }
}