package dev.fiks.repository;

import dev.fiks.model.Status;
import dev.fiks.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import dev.fiks.model.Content;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

	private final List<Content> contentList = new ArrayList<>();

	public ContentCollectionRepository() {
	}

	public List<Content> findAll() {
		return contentList;
	}

	public Optional<Content> findById(Integer id) {
		return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
	}

	public Content save(Content content) {
		contentList.removeIf(c -> c.id().equals(content.id()));
		contentList.add(content);
		return content;
	}

	public boolean existsById(Integer id) {
		return contentList.stream().anyMatch(c -> c.id().equals(id));
	}

	@PostConstruct
	private void init() {
		Content content = new Content(
				1,
				"first",
				"test",
				Status.IDEA,
				Type.ARTICLE,
				LocalDateTime.now(),
				null,
				""
		);

		contentList.add(content);
	}

}

