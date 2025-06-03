package br.com.fiap.safequake_api.repository;

import br.com.fiap.safequake_api.model.EarthquakeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EarthquakeEventRepository extends JpaRepository<EarthquakeEvent, Long> {

    Optional<EarthquakeEvent> findByExternalId(String externalId);

}