package org.sample.capstone.repository;
import org.sample.capstone.entity.AssetDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AssetDetailRepository extends JpaRepository<AssetDetail, Long> {
}
