package com.main.records;

import java.util.List;

public record Movie(String title, int year, List<String> genre, String director, Double rating) {

}
