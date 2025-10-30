package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // ✅ Home page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // ✅ Show appointment form
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "form";
    }

    // ✅ Save or update appointment
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment, RedirectAttributes redirectAttributes) {
        boolean isUpdate = appointment.getId() != null;
        service.save(appointment);
        redirectAttributes.addFlashAttribute("success", isUpdate ? "Appointment updated successfully!" : "Appointment booked successfully!");
        return "redirect:/appointments";
    }

    // ✅ Show all appointments
    @GetMapping("/appointments")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", service.getAll());
        return "appointments";
    }

    // ✅ Edit appointment
    @GetMapping("/appointments/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Appointment appointment = service.getById(id);
        if (appointment == null) {
            redirectAttributes.addFlashAttribute("error", "Appointment not found!");
            return "redirect:/appointments";
        }
        model.addAttribute("appointment", appointment);
        return "form";
    }

    // ✅ Delete appointment
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Appointment deleted successfully!");
        return "redirect:/appointments";
    }
}
