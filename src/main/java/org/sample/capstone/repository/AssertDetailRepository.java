package org.sample.capstone.repository;
import org.sample.capstone.entity.AssertDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AssertDetailRepository extends JpaRepository<AssertDetail, Long> {
}
