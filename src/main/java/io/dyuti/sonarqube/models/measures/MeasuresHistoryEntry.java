package io.dyuti.sonarqube.models.measures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record MeasuresHistoryEntry(Date date, String value) {
}
