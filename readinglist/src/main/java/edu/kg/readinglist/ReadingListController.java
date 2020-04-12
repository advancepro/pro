package edu.kg.readinglist;

import edu.kg.readinglist.io.Customer;
import edu.kg.readinglist.io.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/readingList")
public class ReadingListController {
    private static final String lastName = "polat";

    @Autowired
    ReadingListRepository readingListRepository;

    @GetMapping
    public String readersBooks(Model model) {

        List<Customer> readingList = readingListRepository.findByLastName(lastName);
        if (readingList != null) {
            model.addAttribute("customers", readingList);
        }
        return "readingList";
    }

    @PostMapping
    public String addToReadingList(Customer customer) {
        customer.setLastName(lastName);
        readingListRepository.save(customer);
        return "redirect:/readingList";
    }

}
