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

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.Query;
import org.assertj.core.api.Assertions;

/**
 * Assertions for testing lucene queries, documents and token streams
 */
public class LuceneAssertions extends Assertions {

    public static QueryAssert assertThat(Query actual) {
        return new QueryAssert(actual);
    }

    public static TokenStreamAssert assertThat(TokenStream actual) throws IOException {
        return new TokenStreamAssert(actual);
    }
}
