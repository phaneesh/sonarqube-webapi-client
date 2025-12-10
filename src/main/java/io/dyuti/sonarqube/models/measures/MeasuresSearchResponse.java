package io.dyuti.sonarqube.models.measures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dyuti.sonarqube.models.common.Paging;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record MeasuresSearchResponse(Paging paging, List<MeasuresHistory> measures) {
}
