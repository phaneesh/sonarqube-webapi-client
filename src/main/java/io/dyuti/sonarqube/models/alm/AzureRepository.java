package io.dyuti.sonarqube.models.alm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record AzureRepository(String name, String projectName) {
}
