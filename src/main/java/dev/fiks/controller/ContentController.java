package dev.fiks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import dev.fiks.model.Content;
import dev.fiks.repository.ContentCollectionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

	private final ContentCollectionRepository repository;

	public ContentController(ContentCollectionRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	public List<Content> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Content findById(@PathVariable Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("Content with id %d not found", id))
		);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public void create(@RequestBody Content content) {
		repository.save(content);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable Integer id, @RequestBody Content content) {

		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Content with id %d not found", id));
		}

		repository.save(content);
	}
}
