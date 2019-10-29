package com.homeedition.reservation.controllers;

import com.homeedition.reservation.models.User;
import com.homeedition.reservation.security.details.UserDetailsImpl;
import com.homeedition.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller for description page
 */
@Controller
public class DescriptionController {

    @Autowired
    ReservationService reservationService;


    @GetMapping("/description/{id}")
    public String getDescriptionPage(@PathVariable("id") Long reservationId,
                               Authentication authentication, ModelMap model) {
        if (authentication == null) {
            return "redirect:/login";
        }
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        User user = details.getUser();
        model.addAttribute("user", user);
        model.addAttribute("resn", this.reservationService.getReservationById(reservationId));

        return "description";
    }
}
