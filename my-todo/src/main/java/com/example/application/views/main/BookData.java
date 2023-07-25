package com.example.application.views.main;

import java.time.LocalDate;

public class BookData {

    private String title;
    private String bookCategory;
    private LocalDate dateRelease;
    private String bookLanguage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "title='" + title + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                ", dateRelease=" + dateRelease +
                ", bookLanguage='" + bookLanguage + '\'' +
                '}';
    }
}
