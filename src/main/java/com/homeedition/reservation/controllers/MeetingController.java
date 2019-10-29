package com.homeedition.reservation.controllers;

import com.homeedition.reservation.exceptions.ReservationException;
import com.homeedition.reservation.forms.ReservationForm;
import com.homeedition.reservation.models.Reservation;
import com.homeedition.reservation.services.DateService;
import com.homeedition.reservation.models.User;
import com.homeedition.reservation.security.details.UserDetailsImpl;
import com.homeedition.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Controller for meeting page
 */
@Controller
public class MeetingController {

    @Autowired
    private final ReservationService reservationService;

    public MeetingController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Generates data for a page with a table
     */
    @GetMapping(path = {"/", "/{week}"})
    public String getMeetingPage(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable(name = "week", required = false) LocalDate week,
                                 ModelMap model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        //if week not set
        LocalDate currentWeek = (week == null) ? LocalDate.now() : week;

        //user from Authentication
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        User user = details.getUser();
        model.addAttribute("user", user);
        model.addAttribute("times", DateService.timesOfDay(false));

        ArrayList<LocalDate> days = DateService.daysOfWeek(currentWeek);
        model.addAttribute("prevWeek", currentWeek.minusWeeks(1));
        model.addAttribute("nextWeek", currentWeek.plusWeeks(1));
        model.addAttribute("curWeek", currentWeek);
        model.addAttribute("days", days);
        model.addAttribute("reservation", this.reservationService.findReservationInWeek(days));
        model.addAttribute("rooms", this.reservationService.getAllRoom());

        return "meeting";
    }

    /**
     * Reservation meeting room
     */
    @PostMapping(path = {"/reserve","/reserve/{week}"})
    public String reserve(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable(name = "week", required = false) LocalDate week,
            ReservationForm reservationForm, ModelMap model, Authentication authentication) {

        if (authentication == null) {
            return "redirect:/login";
        }
        try {
            //date validation
            this.reservationService.checkDateTime(reservationForm);

            Reservation reservation = Reservation.from(reservationForm);
            UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
            reservation.setUser(details.getUser());
            reservation.setRoom(reservationService.getRoomById(reservationForm.getRoomId()));
            this. reservationService.reserve(reservation);

        } catch (ReservationException e) {
            model.addAttribute("error", e.getMessage());
            return getMeetingPage(week, model, authentication);
        }
        return getMeetingPage(week, model, authentication);
    }

    @ModelAttribute
    public ReservationForm setUpForm() {
        ReservationForm form = new ReservationForm();
        form.setTimeFrom(LocalTime.of(9, 0));
        form.setTimeTo(LocalTime.of(10, 0));
        form.setRoomId(this.reservationService.getFirstRoom().getId());
        return form;
    }
}
