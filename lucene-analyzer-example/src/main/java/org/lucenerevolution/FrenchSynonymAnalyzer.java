/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lucenerevolution;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.fr.FrenchLightStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SolrSynonymParser;
import org.apache.lucene.analysis.synonym.SynonymFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

/**
 *
 */
public class FrenchSynonymAnalyzer extends Analyzer {

    protected final Version matchVersion;

    protected SynonymMap synonymMap;

    public FrenchSynonymAnalyzer(Version matchVersion, Reader synonyms) throws IOException, ParseException {
        this.matchVersion = matchVersion;
        initSynonyms(synonyms);
    }

    private void initSynonyms(Reader synonyms) throws IOException, ParseException {
        final SolrSynonymParser parser = new SolrSynonymParser(
            true, // Remove duplicates
            true, // Expand 
            new WhitespaceAnalyzer(Version.LUCENE_42) // Analyzer to use for parsing synonym entries
        );

        parser.add(synonyms);
        synonymMap = parser.build();
    }

    @Override
    protected Analyzer.TokenStreamComponents createComponents(String fieldName, Reader reader) {
        final Tokenizer source = new StandardTokenizer(matchVersion, reader);
        TokenStream result = new LowerCaseFilter(matchVersion, source);
        result = new SynonymFilter(result, synonymMap, false); // Synonyms
        result = new FrenchLightStemFilter(result);
        return new TokenStreamComponents(source, result);
    }
}
