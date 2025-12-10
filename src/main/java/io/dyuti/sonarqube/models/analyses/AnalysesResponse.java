package io.dyuti.sonarqube.models.analyses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dyuti.sonarqube.models.common.Paging;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record AnalysesResponse(Paging paging, List<AnalysesInfo> analyses) {
}
