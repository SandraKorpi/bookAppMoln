package sandrakorpi.molnintegrationbookapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandrakorpi.molnintegrationbookapp.Models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}



