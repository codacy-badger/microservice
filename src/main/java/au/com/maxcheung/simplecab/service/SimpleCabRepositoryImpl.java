package au.com.maxcheung.simplecab.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleCabRepositoryImpl implements SimpleCabRepository {

    private static final String COUNT_BY_MEDALLION_AND_PICKUP_DATE = "SELECT count(*) FROM cab_trip_data WHERE medallion = ? and date(pickup_datetime) = ? ";

    private static final Logger log = LoggerFactory.getLogger(SimpleCabRepositoryImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable("cabTrips")
    public Integer getCountByMedallionAndPickupDatetime(String medallionId, Date pickupDate) {
        log.info("getCountByMedallionAndPickupDatetime medallionId : {} pickupDate : {} ", medallionId, pickupDate);
        return jdbcTemplate.queryForObject(COUNT_BY_MEDALLION_AND_PICKUP_DATE, new Object[] { medallionId, pickupDate },
                Integer.class);
    }

    @Override
    @CacheEvict(value = "cabTrips", allEntries = true)
    public void resetAllEntries() {
        // Intentionally blank
    }


}
