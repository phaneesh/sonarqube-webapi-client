package io.dyuti.sonarqube.models.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record Project(
        String key,
        String name,
        String qualifier,
        String visibility,
        Date lastAnalysisDate,
        String revision,
        boolean managed
) {
}

