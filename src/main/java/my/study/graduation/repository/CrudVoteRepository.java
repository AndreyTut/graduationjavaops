package my.study.graduation.repository;


import my.study.graduation.model.Vote;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends Repository<Vote, Integer> {

    @Transactional
    Vote save(Vote vote);

    Optional<Vote> getByUserIdAndVotingDate(int userId, LocalDate votingDate);

    List<Vote> getByVotingDate(LocalDate votingDate);
}
