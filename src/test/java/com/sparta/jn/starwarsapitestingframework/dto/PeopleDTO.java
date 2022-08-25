package com.sparta.jn.starwarsapitestingframework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.jn.starwarsapitestingframework.connection.ConnectionManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PeopleDTO {
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("created")
    private String created;
    @JsonProperty("mass")
    private String mass;
    @JsonProperty("vehicles")
    private List<String> vehicles;
    @JsonProperty("url")
    private String url;
    @JsonProperty("hair_color")
    private String hairColor;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("eye_color")
    private String eyeColor;
    @JsonProperty("species")
    private List<String> species;
    @JsonProperty("starships")
    private List<String> starships;
    @JsonProperty("name")
    private String name;
    @JsonProperty("height")
    private String height;
    public List<String> getFilms(){
        return films;
    }
    public String getHomeworld(){
        return homeworld;
    }
    public String getGender(){
        return gender;
    }
    public String getSkinColor(){
        return skinColor;
    }
    public String getEdited(){
        return edited;
    }
    public String getCreated(){
        return created;
    }
    public String getMass(){
        return mass;
    }
    public List<String> getVehicles(){
        return vehicles;
    }
    public String getUrl(){
        return url;
    }
    public String getHairColor(){
        return hairColor;
    }
    public String getBirthYear(){
        return birthYear;
    }
    public String getEyeColor(){
        return eyeColor;
    }
    public List<String> getSpecies(){
        return species;
    }
    public List<String> getStarships(){
        return starships;
    }
    public String getName(){
        return name;
    }
    public String getHeight(){
        return height;
    }

    @Override
    public String toString(){
        return
                "StarWarsPojo{" +
                        "films = '" + films + '\'' +
                        ",homeworld = '" + homeworld + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",skin_color = '" + skinColor + '\'' +
                        ",edited = '" + edited + '\'' +
                        ",created = '" + created + '\'' +
                        ",mass = '" + mass + '\'' +
                        ",vehicles = '" + vehicles + '\'' +
                        ",url = '" + url + '\'' +
                        ",hair_color = '" + hairColor + '\'' +
                        ",birth_year = '" + birthYear + '\'' +
                        ",eye_color = '" + eyeColor + '\'' +
                        ",species = '" + species + '\'' +
                        ",starships = '" + starships + '\'' +
                        ",name = '" + name + '\'' +
                        ",height = '" + height + '\'' +
                        "}";
    }
    public boolean hasMassAboveZero() {
        return Integer.parseInt(getMass()) > 0;
    }


    public boolean hasGender() {
        return (getGender().equals("Male") || getGender().equals("Female") || getGender().equals("unknown") || getGender().equals("n/a"));
    }

    public boolean hasAttributeNotEmpty(String attribute) {
        return !attribute.isEmpty();
    }

    public boolean hasAttributeNotBlank(String attribute) {
        return !attribute.isBlank();
    }

    public boolean hasAttributeNotNull(String attribute) {
        return attribute != null;
    }

    public boolean hasBirthYearFormat() {

        return (getBirthYear().matches("[0-9]+BBY") || getBirthYear().matches("[0-9]+ABY") || getBirthYear().equals("unknown"));

    }

    public boolean hasMeasurementAboveZero(String measurement) {
        return Integer.parseInt(measurement) > 0;
    }

    public boolean hasMeasurementContainingNumbers(String measurement) {
        return measurement.matches("[0-9]+");
    }

    public boolean isURLStatusCode200(String url){

        ConnectionManager.getConnectionURL(url);
        int statusCode = ConnectionManager.getStatusCode();
        return statusCode == 200;
    }

    public boolean hasLoopWithURLStatusCode200(List<String> urls) {
        for(String url : urls){
            if(!isURLStatusCode200(url)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFilmEntry() {
        return !getFilms().isEmpty();
    }

    public boolean hasArrayContainsNoNullValues(List<String> list) { //returns true if contains null?
        return !list.contains(null);
    }

    public boolean hasCorrectURL(String category, String url){
        return url.matches("https://swapi.dev/api/" + category + "/[0-9]+");
    }

    public boolean hasArrayGotCorrectURL(String category, List<String> array){
        boolean correctUrl = true;
        for(String url : array) {
            if(!hasCorrectURL(category, url)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasPastDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnn'Z'");
        LocalDateTime createdDate = LocalDateTime.parse(getCreated(), formatter);
        return createdDate.isBefore(LocalDateTime.now());
    }
        public boolean hasLogicalEditedDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnn'Z'");
            LocalDateTime createdDate = LocalDateTime.parse(getCreated(), formatter);
            LocalDateTime editedDate  = LocalDateTime.parse(getEdited(), formatter);
            return (editedDate.isAfter(createdDate) && editedDate.isBefore(LocalDateTime.now()));
        }
}