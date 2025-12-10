package io.dyuti.sonarqube.models.analyses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record AnalysesInfo(String key,
                           Date date,
                           String projectVersion, String buildString, String revision,
                           boolean manualNewCodePeriodBaseline, List<AnalysesEvent> events) {

}
