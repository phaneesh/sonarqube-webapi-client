package io.dyuti.sonarqube.models.measures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record MeasuresHistory(String metric, List<MeasuresHistoryEntry> history) {
}
