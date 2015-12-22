package com.capitalone.dashboard.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;

import com.capitalone.dashboard.model.ScopeOwnerCollectorItem;

/**
 * CollectorItem repository for {@link ScopeOwnerCollectorItem}.
 */
public interface ScopeOwnerRepository extends
BaseCollectorItemRepository<ScopeOwnerCollectorItem> {
	@Query(value="{ 'collectorId' : ?0, options.teamId : ?1, options.name : ?2}")
	ScopeOwnerCollectorItem findTeamCollector(ObjectId collectorId, String teamId, String name);

    @Query(value="{ 'collectorId' : ?0, options.teamId : ?1, enabled: true}")
    List<ScopeOwnerCollectorItem> findEnabledTeamCollectors(ObjectId collectorId, String teamId);

	@Query(value = "{ 'collectorId' : ?0, 'options.changeDate' : {$gt: ?1}, '_class' : 'com.capitalone.dashboard.model.ScopeOwnerCollectorItem'}", fields="{'options.changeDate' : 1, '_id' : 0}")
	List<ScopeOwnerCollectorItem> findTopByOrderByChangeDateDesc(ObjectId collectorId, String lastChangeDate);

	@Query(value = "{'options.teamId' : ?0}", fields = "{'options.teamId' : 1}")
	List<ScopeOwnerCollectorItem> getTeamIdById(String teamId);
}
