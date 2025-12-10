package io.dyuti.sonarqube.models.analyses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record AnalysesEvent(String key, AnalysesCategory category, String name, String description) {
}
