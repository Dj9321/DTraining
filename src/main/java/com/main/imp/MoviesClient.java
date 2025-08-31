package com.main.imp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.main.records.Movie;

public class MoviesClient {

	HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build();
//	HttpClient clientAsync = HttpClient.newHttpClient();
	// Use jwebserver --directory /home/Dheeraj/git for the following url to run
	public static final String MOVIES_URL = "http://127.0.0.1:8000/DTraining/src/main/resources/MovieList.json";
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

	public List<Movie> getMovies() {
		var request = requestBuilder(MOVIES_URL);
		// there is also sendAsync
		try {
			var response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Status Code: " + response.statusCode());
			// for list you can give TypReference for one single object you can just
			// directly give Movie.class
			List<Movie> movieList = objectMapper.readValue(response.body(), new TypeReference<List<Movie>>() {
			});
//			Movie movie1 = objectMapper.readValue(response.body(), Movie.class);
			System.out.println("Printing movie class " + movieList);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Movie> getMoviesAsync() {
		var request = requestBuilder(MOVIES_URL);
		// there is also sendAsync
		try {
			var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println("Status Code: " + response.statusCode());
			// for list you can give TypReference. for one single object you can just
			// directly give Movie.class
			// Here join is used to wait to get the response.
			var response1 = response.thenApply(HttpResponse::body).join();
			var listOfMovies = objectMapper.readValue(response1, new TypeReference<List<Movie>>() {
			});
			System.out.println("Async Movies List: " + listOfMovies);
			return listOfMovies;
//			response.thenApply(httpResponse -> {
//				System.out.println(httpResponse.statusCode());
//				objectMapper.readValue(httpResponse.body(), new TypeReference<List<Movie>>() {
//				});
//			});

//			Movie movie1 = objectMapper.readValue(response.body(), Movie.class);
//			System.out.println("Printing movie class " + movieList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HttpRequest requestBuilder(String url) {
		return HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

	}

}
