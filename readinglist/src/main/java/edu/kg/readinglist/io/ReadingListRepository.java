package edu.kg.readinglist.io;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);
}
