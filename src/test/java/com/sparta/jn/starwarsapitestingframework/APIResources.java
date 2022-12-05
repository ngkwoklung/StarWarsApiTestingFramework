package com.sparta.jn.starwarsapitestingframework;

public enum APIResources {

    getPeopleAPI("/people/");
    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
