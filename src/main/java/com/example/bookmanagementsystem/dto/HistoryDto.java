package com.example.bookmanagementsystem.dto;

import com.example.bookmanagementsystem.support.domain.HistoryType;
import com.example.bookmanagementsystem.support.domain.Links;
import com.example.bookmanagementsystem.support.dto.SelfDescription;
import com.example.bookmanagementsystem.web.ApiHistoryController;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import org.springframework.hateoas.Link;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


public class HistoryDto {

    private Long id;

    @Getter
    private Long userId;

    @Getter
    private Long bookId;

    @Getter
    private String historyType;

    @Getter
    private String createDate;

    @Getter
    @JsonUnwrapped
    private Links links = new Links();

    @Getter
    private String selfDescription = SelfDescription.HISTORIES.getDocs();


    public HistoryDto() {
    }

    public HistoryDto(Long id, Long userId, Long bookId, HistoryType historyType, String createDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.historyType = historyType.getName();
        this.createDate = createDate;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public HistoryDto addLink() {
        links.add((Iterable<Link>) linkTo(ApiHistoryController.class).slash(String.valueOf(id)).withSelfRel());
        return this;
    }

    public Optional getLink(String rel) {
        return links.getLink(rel);
    }


    @Override
    public String toString() {
        return "HistoryDto{" +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", historyType=" + historyType +
                ", links=" + links +
                ", createDate='" + createDate + '\'' +
                '}';
    }


}