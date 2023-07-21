package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {
  private List<String> languageList = List.of("Polski", "Angielski",
            "Niemiecki", "Inny");
   private VerticalLayout menu;
  private VerticalLayout form;
    private List<BookData> books = new ArrayList<>();

    public MainView() {
        menu = new VerticalLayout();
        form = new VerticalLayout();

        createMenu();

//        BookData data = new BookData();
//        data.setTitle("ggfh");
//        data.setBookLanguage("Polski");
//        data.setDateRelease(LocalDate.of(2020, 12 ,12 ));
//        data.setBookCategory("Nauka");
//        createBookForm(data);

        add(
                new H1("Moja biblioteczka"),
                menu,
                form
        );

    }

    private void createMenu() {
        Select<BookData> selectBook = new Select<>("Ksiazki", selectBookValueChangeEvent -> {
          System.out.println("selectBookValueChangeEvent" +selectBookValueChangeEvent);
           if (! selectBookValueChangeEvent.getHasValue().isEmpty()) {
               createBookForm(selectBookValueChangeEvent.getValue());
           } else {
               form.removeAll();
           }
        });
        
        selectBook.setTextRenderer(bookData -> {
            if(bookData.getTitle()!=null){
                return "Książka: "+bookData.getTitle();
            }
            return "Książka: #" +bookData.hashCode();
        });
        menu.add(selectBook);

        Button nextBook = new Button("Następna książka");
        menu.add(nextBook);
        nextBook.addClickListener(buttonClickEvent -> {
            BookData data = new BookData();
            books.add(data);
            selectBook.setItems(books);
            selectBook.setValue(data);
           // createBookForm(data);

        });
    }

    private void createBookForm(BookData data) {
        form.removeAll();
        //Title
        TextField title = new TextField("Podaj tytuł");

        // Category
        Select<String> bookCategory = new Select<>();
        bookCategory.setLabel("Wybierz kategorie: ");
        bookCategory.setItems("Fikcja", "Nauka", "Fantastyka", "Kryminał", "Romans", "Biografia", "Historyczna", "Horror", "Przygodowa", "Dramat", "Komedia", "Science fiction", "Literatura dziecięca", "Literatura młodzieżowa", "Edukacja", "Filozofia", "Sztuka", "Religia", "Psychologia", "Literatura klasyczna");

        //Release date
        DatePicker dateRelease = new DatePicker("Data wydania");
        add(dateRelease);

        // Language
        TextField languageOther = new TextField("Podaj język");
        languageOther.setVisible(false);

        Select<String> bookLanguage = new Select<>();
        bookLanguage.setLabel("Wybierz język: ");
        bookLanguage.setItems(languageList);
        bookLanguage.setValue("Polski");
        bookLanguage.addValueChangeListener(valueChangeEvent -> {
            if (valueChangeEvent.getValue().equals("Inny")) {
                languageOther.setVisible(true);
            } else {
                languageOther.setVisible(false);
            }
        });

        //Send
        Button addBook = new Button("Dodaj książkę");
        addBook.addClickListener(buttonClickEvent -> {

            //save book
            data.setTitle(title.getValue());
            data.setBookCategory(bookCategory.getValue());
            data.setDateRelease(dateRelease.getValue());

            if("Inny".equals(bookLanguage.getValue())) {
                data.setBookLanguage(languageOther.getValue());

            } else {
                data.setBookLanguage(bookLanguage.getValue());
            }
            System.out.println(data);
        });

        //load data
        if(data.getTitle() != null) {
            title.setValue(data.getTitle());
        }
        if(data.getBookCategory() != null) {
            bookCategory.setValue(data.getBookCategory());
        }
        if(data.getDateRelease() != null) {
            dateRelease.setValue(data.getDateRelease());
        }
        if(data.getBookLanguage() != null) {
           if (languageList.contains(data.getBookLanguage())){
               bookLanguage.setValue(data.getBookLanguage());
           } else {
               languageOther.setValue(data.getBookLanguage());
           }
        }

        //form
        form.add(title,bookCategory, dateRelease, bookLanguage, languageOther, addBook);
    }
}