package io.dyuti.sonarqube.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import io.dyuti.sonarqube.models.alm.AzureProjectResponse;
import io.dyuti.sonarqube.models.alm.AzureSearchRepositoryResponse;
import io.dyuti.sonarqube.models.alm.BitBucketProjectResponse;
import io.dyuti.sonarqube.models.alm.BitBucketSearchRepositoryResponse;
import io.dyuti.sonarqube.models.alm.GitLabSearchRepositoryResponse;
import io.dyuti.sonarqube.models.analyses.AnalysesResponse;
import io.dyuti.sonarqube.models.branch.BranchResponse;
import io.dyuti.sonarqube.models.measures.MeasuresSearchResponse;
import io.dyuti.sonarqube.models.project.ProjectSearchResponse;
import io.dyuti.sonarqube.models.qualitygate.QualityStatusResponse;

@Headers({
        "Accept: application/json"
})
public interface SonarClient {

    //ALM api
    @RequestLine("GET /api/alm_integrations/list_azure_projects?almSetting={almSetting}")
    AzureProjectResponse azureProjects(@Param("almSetting") String almSetting);

    @RequestLine("GET /api/alm_integrations/list_bitbucketserver_projects?almSetting={almSetting}&pageSize={pageSize}&start={start}")
    BitBucketProjectResponse bitBucketProjects(@Param("almSetting") String almSetting, @Param("pageSize") int pageSize, @Param("start") int start);

    @RequestLine("GET /api/alm_integrations/search_azure_repos?almSetting={almSetting}&projectName={projectName}&searchQuery={searchQuery}")
    AzureSearchRepositoryResponse searchAzureRepositories(@Param("almSetting") String almSetting, @Param("projectName") String projectName, @Param("searchQuery") String searchQuery);

    @RequestLine("GET /api/alm_integrations/search_bitbucketcloud_repos?almSetting={almSetting}&p={pageNumber}&ps={pageSize}&repositoryName={repositoryName}")
    BitBucketSearchRepositoryResponse searchBitBucketCloudRepositories(@Param("almSetting") String almSetting, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("repositoryName") String repositoryName);

    @RequestLine("GET /api/alm_integrations/search_bitbucketserver_repos?almSetting={almSetting}&start={pageNumber}&pageSize={pageSize}&repositoryName={repositoryName}&projectName={projectName}")
    BitBucketSearchRepositoryResponse searchBitBucketRepositories(@Param("almSetting") String almSetting, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("repositoryName") String repositoryName, @Param("projectName") String projectName);

    @RequestLine("GET /api/alm_integrations/search_gitlab_repos?almSetting={almSetting}&p={pageNumber}&ps={pageSize}&projectName={projectName}")
    GitLabSearchRepositoryResponse searchGitLabRepositories(@Param("almSetting") String almSetting, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("projectName") String projectName);

    //Project api
    @RequestLine("GET /api/projects/search?projects={projectKey}")
    ProjectSearchResponse projectSearch(@Param("projectKey") String projectKey);

    //Branch api
    @RequestLine("GET /api/project_branches/list?project={projectKey}")
    BranchResponse branchList(@Param("projectKey") String projectKey);

    //Analyses api
    @RequestLine("GET /api/project_analyses/search?ps=500&project={projectKey}&from={fromDate}&to={toDate}&category={category}")
    AnalysesResponse analyses(@Param("projectKey") String projectKey, @Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("category") String category);

    //Audit Log
    @RequestLine("GET /api/audit_logs/download?from={from}&to={to}")
    Response auditLog(@Param("from") String fromDate, @Param("to") String toDate);

    //QualityGate Status
    @RequestLine("GET /api/qualitygates/project_status?analysisId={analysisId}")
    QualityStatusResponse qualityGateStatus(@Param("analysisId") String analysisId);

    //Measure History
    @RequestLine("GET /api/measures/search_history?component={component}&metrics={metrics}&from={from}&to={to}&p=1&ps=1000")
    MeasuresSearchResponse measuresSearch(@Param("component") String component, @Param("metrics") String metrics, @Param("from") String from, @Param("to") String to);

}
