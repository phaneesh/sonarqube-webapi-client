package io.dyuti.sonarqube.models.branch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public record BranchInfo(String name, boolean isMain, String type, BranchStatus status,
                         Date analysisDate,
                         boolean excludedFromPurge, String branchId) {
}
