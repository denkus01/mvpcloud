package com.clrkmicro.search.service

import com.clrkmicro.model.Document
import com.clrkmicro.search.config.Config
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject

@ContextConfiguration(classes = Config)
class SearchDocumentServiceTest extends Specification {
    @Subject
    SearchDocumentService sut

    void setup() {
        sut = new SearchDocumentService()
    }

    def "should return a document"() {
        given:
        final searchName = 'Ivan'

        when:
        List<Document> result = sut.findDocuments(searchName)
        then:
        assert result != null
    }
}
