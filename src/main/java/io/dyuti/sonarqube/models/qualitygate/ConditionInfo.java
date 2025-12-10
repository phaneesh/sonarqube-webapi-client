package io.dyuti.sonarqube.models.qualitygate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record ConditionInfo(String status, String metricKey, String comparator, String errorThreshold, String actualValue) {
}
