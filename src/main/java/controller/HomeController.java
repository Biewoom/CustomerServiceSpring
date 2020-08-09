package controller;

import controller.restAPI.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {
//    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
//        logger.info("Welcome home! The client locale is {}.", locale);
        System.out.println("Welcome home! The client locale");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);
        return "home";
    }

//    @RequestMapping(value = "/customer-query")
//    public String handleMyURL(Model model) {
//        return "forward:/customer-query";
//    }

}
