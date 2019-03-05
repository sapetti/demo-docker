package com.softtek.demodocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping
	public List<User> getAll() {
		return repository.findAll();
	}

	@GetMapping(value = "/{name}")
	public User getOne(@PathVariable String name) {
		return repository
				.findByName(name)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}

	@PostMapping
	public User save(@RequestBody User user) {
		return repository.save(user);
	}

	@PutMapping(value = "/{name}")
	public User update(@PathVariable String name,
					   @RequestBody User user) {
		return repository
				.findByName(name)
				.map(repository::save)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}

	@DeleteMapping(value = "/{name}")
	public void delete(@PathVariable String name) {
		repository.deleteByName(name);
	}
}
