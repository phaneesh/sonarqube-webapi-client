package io.dyuti.sonarqube;

import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BranchListTest extends BaseTest {

    @Test
    void testBranchList() {
        wireMockServer.stubFor(get("/api/project_branches/list?project=project-key-1")
                .willReturn(ok("""
                        {
                          "branches": [
                            {
                              "name": "feature/foo",
                              "isMain": false,
                              "type": "BRANCH",
                              "status": {
                                "qualityGateStatus": "OK"
                              },
                              "analysisDate": "2017-04-03T13:37:00+0100",
                              "excludedFromPurge": false,
                              "branchId": "ac312cc6-26a2-4e2c-9eff-1072358f2017"
                            },
                            {
                              "name": "main",
                              "isMain": true,
                              "type": "BRANCH",
                              "status": {
                                "qualityGateStatus": "ERROR"
                              },
                              "analysisDate": "2017-04-01T01:15:42+0100",
                              "excludedFromPurge": true,
                              "branchId": "57f02458-65db-4e7f-a144-20122af12a4c]"
                            }
                          ]
                        }""")
                        .withHeader("Content-Type", "application/json")));
        var response = sonarClient.branchList("project-key-1");
        assertEquals(2, response.branches().size());
    }
}
