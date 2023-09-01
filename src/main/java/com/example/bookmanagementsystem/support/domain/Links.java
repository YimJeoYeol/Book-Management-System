package com.example.bookmanagementsystem.support.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;


import java.util.ArrayList;
import java.util.List;

public class Links extends RepresentationModel {
    @JsonIgnoreProperties({"media", "hreflang", "title", "type", "deprecation"})
    private List<Link> links = new ArrayList<>();

    public org.springframework.hateoas.Links getLinks() {
        return (org.springframework.hateoas.Links) links;
    }


    @Override
    public Link getLink(String rel) {
        return links.stream().filter(l -> l.getRel().equals(rel)).findFirst().orElse(null);
    }

    public void add(Link link) {
        links.add(link);
    }

    @Override
    public String toString() {
        return "links{" +
                getLinks() +
                '}';
    }
}