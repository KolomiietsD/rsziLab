package com.example.rszilab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public String getMainPage(@RequestParam(name = "name", required = false) String name,
                              Model model){
        List<Person> personList;
        if (name != null && !name.isEmpty()){
            personList = personRepository.findByName(name);
        } else {
            personList = personRepository.findAll();
        }
        model.addAttribute("personList",personList);
        return "main";
    }
    @PostMapping
    public RedirectView addPerson(@RequestParam(name="name") String name,
                                  @RequestParam(name="surname") String surname,
                                  Model model){

        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        personRepository.save(person);

        return new RedirectView("/");
    }
}
