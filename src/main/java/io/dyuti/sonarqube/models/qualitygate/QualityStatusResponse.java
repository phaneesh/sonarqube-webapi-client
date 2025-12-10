package io.dyuti.sonarqube.models.qualitygate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record QualityStatusResponse(String status, boolean ignoredConditions, String caycStatus, List<ConditionInfo> conditions) {
}
