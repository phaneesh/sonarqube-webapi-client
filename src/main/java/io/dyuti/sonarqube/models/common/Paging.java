package io.dyuti.sonarqube.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record Paging(int pageIndex, int pageSize, int total) {
}
