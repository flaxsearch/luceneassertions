package com.github.flaxsearch.luceneassert;

/*
 *   Copyright (c) 2017 Lemur Consulting Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.memory.MemoryIndex;
import org.apache.lucene.search.Query;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/**
 * Assertions for matching queries and documents
 */
public class QueryAssert extends AbstractAssert<QueryAssert, Query> {

    protected QueryAssert(Query actual) {
        super(actual, QueryAssert.class);
    }

    /**
     * Assert that a document matches the query
     */
    public QueryAssert matches(Document doc, Analyzer analyzer) {
        return matches(MemoryIndex.fromDocument(doc, analyzer));
    }

    /**
     * Assert that a document parsed into a MemoryIndex matches the query
     */
    public QueryAssert matches(MemoryIndex index) {
        isNotNull();
        Assertions.assertThat(index).isNotNull();
        Assertions.assertThat(index.search(actual))
                .overridingErrorMessage("Query %s did not match %s",
                        escapeForError(actual.toString()), escapeForError(index.toString()))
                .isNotEqualTo(0.0f);
        return this;
    }

    /**
     * Assert that a document does not match the query
     */
    public QueryAssert doesNotMatch(Document doc, Analyzer analyzer) {
        return doesNotMatch(MemoryIndex.fromDocument(doc, analyzer));
    }

    /**
     * Assert that a document parsed into a MemoryIndex does not match the query
     */
    public QueryAssert doesNotMatch(MemoryIndex index) {
        isNotNull();
        Assertions.assertThat(index).isNotNull();
        Assertions.assertThat(index.search(actual))
                .overridingErrorMessage("Query %s incorrectly matched %s!",
                        escapeForError(actual.toString()), escapeForError(index.toString()))
                .isEqualTo(0.0f);
        return this;
    }

    private static String escapeForError(String text) {
        return text.replaceAll("%", "%%");
    }
}
