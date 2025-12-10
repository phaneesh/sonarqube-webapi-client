SonarQube WebAPI Client
=======================

This repository provides a small Java client for SonarQube's WebAPI using Feign with OkHttp and Jackson.

Requirements
-------------
Java 17+

Supported APIs (methods on `io.dyuti.sonarqube.client.SonarClient`)
----------------------------------------------------------------

- ALM APIs
  - azureProjects(almSetting: String)
    - GET /api/alm_integrations/list_azure_projects?almSetting={almSetting}
    - Returns: io.dyuti.sonarqube.models.alm.AzureProjectResponse

  - bitBucketProjects(almSetting: String, pageSize: int, start: int)
    - GET /api/alm_integrations/list_bitbucketserver_projects?almSetting={almSetting}&pageSize={pageSize}&start={start}
    - Returns: io.dyuti.sonarqube.models.alm.BitBucketProjectResponse

  - searchAzureRepositories(almSetting: String, projectName: String, searchQuery: String)
    - GET /api/alm_integrations/search_azure_repos?almSetting={almSetting}&projectName={projectName}&searchQuery={searchQuery}
    - Returns: io.dyuti.sonarqube.models.alm.AzureSearchRepositoryResponse

  - searchBitBucketCloudRepositories(almSetting: String, pageNumber: int, pageSize: int, repositoryName: String)
    - GET /api/alm_integrations/search_bitbucketcloud_repos?almSetting={almSetting}&p={pageNumber}&ps={pageSize}&repositoryName={repositoryName}
    - Returns: io.dyuti.sonarqube.models.alm.BitBucketSearchRepositoryResponse

  - searchBitBucketRepositories(almSetting: String, pageNumber: int, pageSize: int, repositoryName: String, projectName: String)
    - GET /api/alm_integrations/search_bitbucketserver_repos?almSetting={almSetting}&start={pageNumber}&pageSize={pageSize}&repositoryName={repositoryName}&projectName={projectName}
    - Returns: io.dyuti.sonarqube.models.alm.BitBucketSearchRepositoryResponse

  - searchGitLabRepositories(almSetting: String, pageNumber: int, pageSize: int, projectName: String)
    - GET /api/alm_integrations/search_gitlab_repos?almSetting={almSetting}&p={pageNumber}&ps={pageSize}&projectName={projectName}
    - Returns: io.dyuti.sonarqube.models.alm.GitLabSearchRepositoryResponse

- Project API
  - projectSearch(projectKey: String)
    - GET /api/projects/search?projects={projectKey}
    - Returns: io.dyuti.sonarqube.models.project.ProjectSearchResponse

- Branch API
  - branchList(projectKey: String)
    - GET /api/project_branches/list?project={projectKey}
    - Returns: io.dyuti.sonarqube.models.branch.BranchResponse

- Analyses API
  - analyses(projectKey: String, fromDate: String, toDate: String)
    - GET /api/project_analyses/search?ps=500&project={projectKey}&from={fromDate}&to={toDate}
    - Returns: io.dyuti.sonarqube.models.analyses.AnalysesResponse
    - Note: `fromDate` and `toDate` are expected as ISO-8601 strings (e.g. 2025-04-01T00:00:00Z)

- Audit Log download
  - auditLog(from: String, to: String)
    - GET /api/audit_logs/download?from={from}&to={to}
    - Returns: feign.Response (caller can stream and save the JSON file)

How authentication and configuration works
------------------------------------------

- The `SonarClient` produced by `io.dyuti.sonarqube.SonarClientFactory#createSonarClient(String host, String apiKey)` is configured with:
  - Feign + OkHttp as the HTTP client
  - Jackson encoder/decoder (configured for Java Time module and ISO date formats)
  - A request interceptor that sets `Authorization: Bearer <apiKey>` on every request

Dependency
-------------
```xml
<dependency>
    <groupId>io.dyuti</groupId>
    <artificatId>sonarqube-webapi-client</artificatId>
    <version>1.0.0</version>
</dependency>
```

Usage example
-------------
Below is a minimal usage example demonstrating client creation, calling a simple API, and downloading the audit log to a file.

```java
import io.dyuti.sonarqube.SonarClientFactory;
import io.dyuti.sonarqube.client.SonarClient;
import feign.Response;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Example {
    public static void main(String[] args) throws Exception {
        String host = "https://sonarqube.example.com"; // SonarQube base URL
        String apiKey = "your_api_key_here";

        SonarClient client = SonarClientFactory.createSonarClient(host, apiKey);

        // Project search
        var search = client.projectSearch("project-key-1");
        System.out.println("Found components: " + (search != null ? search.components().size() : 0));

        // Analyses example using ISO 8601 dates (inclusive)
        String from = "2025-04-01T00:00:00Z";
        String to = "2025-11-30T23:59:59Z";
        var analyses = client.analyses("project-key-1", from, to);
        System.out.println("Analyses total: " + (analyses != null ? analyses.paging() : "n/a"));

        // Download audit log (streaming response to a file)
        Response resp = client.auditLog(from, to);
        if (resp != null && resp.status() == 200 && resp.body() != null) {
            Path out = Path.of("audit_logs.json");
            try (InputStream in = resp.body().asInputStream()) {
                Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("Audit logs saved to " + out.toAbsolutePath());
        } else {
            System.out.println("Failed to download audit logs, status=" + (resp != null ? resp.status() : "null"));
        }
    }
}
```

Notes and tips
--------------

- The client uses Jackson for JSON; model classes in this project are POJOs/records that the Jackson decoder maps into. Lombok is used in model classes where appropriate to reduce boilerplate.
- Date-time values use ISO-8601. The included ObjectMapper registers the JavaTimeModule and disables timestamps for dates.
- The `auditLog` method returns a `feign.Response` so callers can stream large payloads instead of loading them fully into memory.

Contributing
------------

Feel free to open issues or PRs. Keep API additions consistent with existing naming and return types (use model packages under `io.dyuti.sonarqube.models`).

