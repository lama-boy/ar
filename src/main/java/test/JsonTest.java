package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class JsonTest {
	public static void main(String[] args) throws FileNotFoundException {
		//Gson 에 자동 줄바꿈과 들여쓰기 기능을 추가합니다.
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
//		"red"
//		{
//		  "colorsArray": [
//		    {
//		      "colorName": "red",
//		      "hexValue": "#f00"
//		    },
//		    {
//		      "colorName": "green",
//		      "hexValue": "#0f0"
//		    },
//		    {
//		      "colorName": "blue",
//		      "hexValue": "#00f"
//		    }
//		  ]
//		}
		
		JsonReader reader = new JsonReader(new FileReader("src/main/resources/sample.json"));
		
		//여기서 넣는 클래스는 Member 와 같은 클래스도 될수 있습니다.
		//JsonElement 의 이름과 Field 명이 일치하면 자동으로 파싱됩니다.
		//일치하지 않는다면 해당 필드에 @SerializeName("name") 어노테이션을 붙여서 정의합니다.
		JsonObject json = gson.fromJson(reader, JsonObject.class); 
		Colors json2 = gson.fromJson(json, Colors.class); 
		
		
		
		System.out.println(gson.toJson(json));
		System.out.println(gson.toJson(json2));
	}
}

@Getter
@Setter
@ToString
class Colors {
	List<Color> colorsArray;
}

@Getter
@Setter
@ToString
class Color {
	String colorName, hexValue;
}