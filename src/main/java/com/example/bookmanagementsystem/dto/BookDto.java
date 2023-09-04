package com.example.bookmanagementsystem.dto;import com.example.bookmanagementsystem.domain.Book;import com.example.bookmanagementsystem.domain.ItemInfo;import com.example.bookmanagementsystem.domain.User;import com.example.bookmanagementsystem.support.domain.Links;import com.example.bookmanagementsystem.support.dto.SelfDescription;import com.example.bookmanagementsystem.web.ApiBookController;import com.example.bookmanagementsystem.web.ApiUserController;import com.fasterxml.jackson.annotation.JsonUnwrapped;import lombok.Getter;import org.springframework.hateoas.Link;import javax.validation.constraints.NotNull;import javax.validation.constraints.Positive;import javax.validation.constraints.Size;import java.time.LocalDate;import java.util.Optional;import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;public class BookDto {    @Getter    private Long id;    @Getter    @Size(min = 3, max = 20)    private String title;    @Getter    @Size(min = 3)    private String author;    @Getter    private boolean rentable = true;    private boolean isDeleted = false;    @JsonUnwrapped    private Links links = new Links();    @Getter    private String selfDescription = SelfDescription.BOOKS.getDocs();    @Getter    @NotNull    private LocalDate releaseDate;    @Getter    @NotNull    @Positive    private int pageNumber;    @NotNull    @Positive    private int weight;    public BookDto() {    }    public BookDto(String title, String author, LocalDate releaseDate, int pageNumber, int weight) {        this.title = title;        this.author = author;        this.releaseDate = releaseDate;        this.pageNumber = pageNumber;        this.weight = weight;    }    public BookDto(Long id, String title, String author, boolean rentable, boolean isDeleted, ItemInfo itemInfo) {        this(title, author, itemInfo.getReleaseDate(), itemInfo.getPageNumber(), itemInfo.getWeight());        this.id = id;        this.rentable = rentable;        this.isDeleted = isDeleted;    }    public BookDto(String id, String title, String author, boolean rentable, Boolean isDeleted, ItemInfo itemInfo) {    }    public Link getLink(String rel) {        return links.getLink(rel);    }    public void setLinks(Links links) {        this.links = links;    }    public void setTitle(String title) {        this.title = title;    }    public void setAuthor(String author) {        this.author = author;    }    public boolean isDeleted() {        return isDeleted;    }    public void setDeleted(boolean deleted) {        isDeleted = deleted;    }    public void setRentable(boolean rentable) {        this.rentable = rentable;    }    public void setReleaseDate(LocalDate releaseDate) {        this.releaseDate = releaseDate;    }    public void setPageNumber(int pageNumber) {        this.pageNumber = pageNumber;    }    public void setWeight(int weight) {        this.weight = weight;    }    public Book toBook() {        return new Book(title, author, convertItemInfo());    }    public BookDto addLink(Optional<User> render) {        links.add(linkTo(ApiBookController.class).slash(id).withSelfRel());        render.ifPresent(act -> links.add(linkTo(ApiUserController.class).slash(render.get().getId()).withRel("render")));        /*        if (!render.isPresent()) {            links.add(linkTo(ApiUserController.class).slash(render.get().getId()).withRel("render"));        }*/        return this;    }    public ItemInfo convertItemInfo() {        return new ItemInfo(releaseDate, pageNumber, weight);    }    @Override    public boolean equals(Object o) {        if (this == o) return true;        if (o == null || getClass() != o.getClass()) return false;        BookDto bookDto = (BookDto) o;        if (rentable != bookDto.rentable) return false;        if (isDeleted != bookDto.isDeleted) return false;        if (pageNumber != bookDto.pageNumber) return false;        if (weight != bookDto.weight) return false;        if (title != null ? !title.equals(bookDto.title) : bookDto.title != null) return false;        if (author != null ? !author.equals(bookDto.author) : bookDto.author != null) return false;        if (links != null ? !links.equals(bookDto.links) : bookDto.links != null) return false;        return releaseDate != null ? releaseDate.equals(bookDto.releaseDate) : bookDto.releaseDate == null;    }    @Override    public int hashCode() {        int result = title != null ? title.hashCode() : 0;        result = 31 * result + (author != null ? author.hashCode() : 0);        result = 31 * result + (rentable ? 1 : 0);        result = 31 * result + (isDeleted ? 1 : 0);        result = 31 * result + (links != null ? links.hashCode() : 0);        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);        result = 31 * result + pageNumber;        result = 31 * result + weight;        return result;    }    @Override    public String toString() {        return "BookDto{" +                "id=" + id +                ", title='" + title + '\'' +                ", author='" + author + '\'' +                ", rentable=" + rentable +                ", isDeleted=" + isDeleted +                ", links=" + links +                '}';    }}