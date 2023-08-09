package com.system.tennis_management_system.Controller;

import com.system.tennis_management_system.Pojo.BookingPojo;
import com.system.tennis_management_system.Pojo.TennisPojo;
import com.system.tennis_management_system.Service.BookingService;
import com.system.tennis_management_system.Service.TennisService;
import com.system.tennis_management_system.Service.UserService;
import com.system.tennis_management_system.entity.Tennis;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ball")
public class TennisController {
    private final TennisService tennisService;
    private final UserService userService;
    private final BookingService bookingService;
//    @GetMapping("/product")
//    public String product() {
//
//        return "booktennis";
//    }

    @GetMapping("/product/{id}")
    public String getTennisProfiile(@PathVariable("id") Integer id, Model model, Principal principal){
        Tennis tennis = tennisService.fetchById(id);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        model.addAttribute("savebooking", new BookingPojo() );

        model.addAttribute("tenniss", new TennisPojo(tennis));
//
        model.addAttribute("clickedtennis", tennis);

        return "booktennis";
    }

    @PostMapping("/sbooking")
    public String savebooking(@Valid BookingPojo bookingPojo){
        if (!bookingService.bookedTime(Date.valueOf(bookingPojo.getDate()), bookingPojo.getFid()).contains(bookingPojo.getStarting())) {
            bookingService.saveOrder(bookingPojo);
            return "redirect:/home/homepage";
        } else return "redirect:/ball/product/"+bookingPojo.getFid();
    }

    @GetMapping("/addtennis")
    public String createTennis(Model model) {
        model.addAttribute("tennis", new TennisPojo());

        return "tennis";
    }
    @PostMapping("/save")
    public String saveTennis(@Valid TennisPojo tennisPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException{
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addtennis";
        }
        tennisService.savetennis(tennisPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/dashboard";
    }

    private Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }
    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/images/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }












}
