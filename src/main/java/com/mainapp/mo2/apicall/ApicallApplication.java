package com.mainapp.mo2.apicall;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainapp.mo2.apicall.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class ApicallApplication {

	public static void main(String[] args) throws URISyntaxException {

		SpringApplication.run(ApicallApplication.class, args);
		ObjectMapper objectMapper = new ObjectMapper();
		Transcript transcript = new Transcript();
		transcript.setAudio_url("https://bit.ly/3yxKEIY");
		String urlJson ="";
		try {
			urlJson = objectMapper.writeValueAsString(transcript);
			System.out.println(urlJson);
		} catch (Exception e){
			System.out.println("OBJECT MAPPER FARTED :) ");
		}



		HttpRequest postReq  = HttpRequest.newBuilder()
				.uri(new URI("https://api.assemblyai.com/v2/transcript"))
				.header("Authorization", Constants.API_KEY)
				.POST(HttpRequest.BodyPublishers.ofString(urlJson))
				 .build();
		HttpClient httpClient = HttpClient.newHttpClient();
		try {
			HttpResponse<String> response =httpClient.send(postReq, HttpResponse.BodyHandlers.ofString());
//			transcript = objectMapper.readValue(response.body(),Transcript.class);
			System.out.println(response.statusCode());
		} catch (Exception e) {
			System.out.println("ObjectMapper just FARTED");
		}

	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Transcript{
		private String audio_url;
		private String id;
	}

}
