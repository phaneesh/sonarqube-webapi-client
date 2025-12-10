package io.dyuti.sonarqube;

import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectSearchTest extends BaseTest {

    @Test
    void testProjectSearchWithSingleProject() {
        wireMockServer.stubFor(get("/api/projects/search?projects=project-key-1")
                .willReturn(ok("""
                        {
                            "paging": {
                                "pageIndex": 1,
                                "pageSize": 100,
                                "total": 1
                            },
                            "components": [
                                {
                                    "key": "project-key-1",
                                    "name": "Project Name 1",
                                    "qualifier": "TRK",
                                    "visibility": "public",
                                    "lastAnalysisDate": "2017-03-01T11:39:03+0300",
                                    "revision": "cfb82f55c6ef32e61828c4cb3db2da12795fd767",
                                    "managed": false
                                }
                            ]
                        }""")
                        .withHeader("Content-Type", "application/json")));
        var response = sonarClient.projectSearch("project-key-1");
        assertEquals(1, response.paging().total());
        assertEquals(100, response.paging().pageSize());
        assertEquals(1, response.paging().pageIndex());
        assertEquals(1, response.components().size());
    }

    @Test
    void testProjectSearchWithMultipleProjects() {
        wireMockServer.stubFor(get("/api/projects/search?projects=project-key-1%2Cproject-key-2")
                .willReturn(ok("""
                        {
                             "paging": {
                                 "pageIndex": 1,
                                 "pageSize": 100,
                                 "total": 2
                             },
                             "components": [
                                 {
                                     "key": "project-key-1",
                                     "name": "Project Name 1",
                                     "qualifier": "TRK",
                                     "visibility": "public",
                                     "lastAnalysisDate": "2017-03-01T11:39:03+0300",
                                     "revision": "cfb82f55c6ef32e61828c4cb3db2da12795fd767",
                                     "managed": false
                                 },
                                 {
                                     "key": "project-key-2",
                                     "name": "Project Name 2",
                                     "qualifier": "TRK",
                                     "visibility": "private",
                                     "lastAnalysisDate": "2017-03-02T15:21:47+0300",
                                     "revision": "7be96a94ac0c95a61ee6ee0ef9c6f808d386a355",
                                     "managed": false
                                 }
                             ]
                         }""")
                        .withHeader("Content-Type", "application/json")));
        var response = sonarClient.projectSearch("project-key-1,project-key-2");
        assertEquals(2, response.paging().total());
        assertEquals(100, response.paging().pageSize());
        assertEquals(1, response.paging().pageIndex());
        assertEquals(2, response.components().size());
    }
}
