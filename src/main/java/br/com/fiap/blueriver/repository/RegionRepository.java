package br.com.fiap.blueriver.repository;

import br.com.fiap.blueriver.entity.Region;
import br.com.fiap.blueriver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {
    List<Region> findAllByRegion(String region);
}
