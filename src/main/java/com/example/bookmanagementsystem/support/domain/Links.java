package com.example.bookmanagementsystem.support.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;


import java.util.ArrayList;
import java.util.List;

public class Links extends RepresentationModel {
    @Getter
    @JsonIgnoreProperties({"media", "hreflang", "title", "type", "deprecation"})
    private List<Link> links = new ArrayList<>();


    @JsonIgnore
    public Link getLink(String rel) {
        return links.stream().filter(l -> l.getRel().equals(rel)).findFirst().orElse(null);
    }

    public RepresentationModel add(Link link) {
        links.add(link);
        return null;
    }

    @Override
    public String toString() {
        return "links{" +
                getLinks() +
                '}';
    }
}