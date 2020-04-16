package com.ae.bullhorn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "messageList";
    }

    @RequestMapping("/add")
    public String addMessage(Model model){
        model.addAttribute("message", new Message());
        return "addMessage";
    }
    @PostMapping("/process")
    public String processMessage(@Valid Message message, BindingResult result){
        if (result.hasErrors()){
            return "addMessage";
        }
        messageRepository.save(message);
        return "redirect:/";
    }
}
