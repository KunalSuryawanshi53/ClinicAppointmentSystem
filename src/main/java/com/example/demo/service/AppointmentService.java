package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) {
        this.repo = repo;
    }

    public void save(Appointment appointment) {
        repo.save(appointment); 
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public Appointment getById(Long id) {
        Optional<Appointment> optional = repo.findById(id);
        return optional.orElse(null);
    }

    public List<Appointment> getAll() {
        return repo.findAll();
    }
}
