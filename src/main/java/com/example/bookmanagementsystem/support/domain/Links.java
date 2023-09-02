package com.example.bookmanagementsystem.support.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.hateoas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Links extends RepresentationModel {
    @Getter
    @JsonIgnoreProperties({"media", "hreflang", "title", "type", "deprecation"})
    private List<Link> links = new ArrayList<>();




    @Override
    public Optional getLink(String rel) {
        return links.stream().filter(l -> l.getRel().equals(rel)).findFirst();
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