package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StudentModel;
import com.example.demo.repo.StudentRepo;

@Controller
@RestController
@RequestMapping("/api/mysql")
public class StudentController {

	@Autowired
	StudentRepo studentRepo;
	
	
	@PostMapping
	public String insert(@RequestBody StudentModel studentModel) {
		try {
			studentRepo.save(studentModel);
			return "Data Added";
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Data Not Added";
		}
	}
	
	@GetMapping
	public List<StudentModel> getData(){
		return studentRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<StudentModel> getDataBYId(@PathVariable int id) {
		return studentRepo.findById(id);
	}
	
	@PutMapping("/{id}")
	public String updateData(@PathVariable int id, @RequestBody StudentModel studentModel) {
		try {
			StudentModel std = studentRepo.findById(id).orElseThrow();
			
			std.setName(studentModel.getName());
			std.setRegNo(studentModel.getRegNo());
			std.setMobileNo(studentModel.getMobileNo());
			std.setAddress(studentModel.getAddress());
			studentRepo.save(std);
			
			return "Data as updated for the given id ="+ id;
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Data as not updated for the given id ="+ id;
		}
	}
	
	@DeleteMapping
	public String deleteAllData() {
		try {
			studentRepo.deleteAll();
			return "All data as been deleted from database";
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Data as not deleted from data base";
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteDataById(@PathVariable int id) {
		try {
			studentRepo.deleteById(id);
			return "Data as been deleted from the data base for the given id ="+id;
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Data as not deleted from the data base for the given id ="+id;
		}
	}
}
