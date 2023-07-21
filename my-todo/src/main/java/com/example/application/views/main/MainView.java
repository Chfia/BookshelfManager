package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

   private VerticalLayout menu;
  private VerticalLayout form;

    public MainView() {
        menu = new VerticalLayout();
        form = new VerticalLayout();


        createBookForm();

        add(
                new H1("Moja biblioteczka"),
                menu,
                form
        );

    }

    private void createBookForm() {
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
        bookLanguage.setItems("Polski", "Angielski",
                "Niemiecki", "Inny");
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
            BookData data = new BookData();
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

        //form
        form.add(title,bookCategory, dateRelease, bookLanguage, languageOther);
    }
}