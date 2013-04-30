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

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;

public class EnglishExtractorExample {

    private SentenceModel sentenceModel;

    private TokenizerModel tokenizerModel;

    private TokenNameFinderModel nameFinderModel;

    private POSModel partOfSpeechModel;

    private SentenceDetectorME sentenceDetector;

    private TokenizerME tokenizer;

    private NameFinderME nameFinder;

    private POSTaggerME partOfSpeechTagger;


    public EnglishExtractorExample() throws IOException {
        initModels();

        tokenizer = new TokenizerME(tokenizerModel);
        nameFinder = new NameFinderME(nameFinderModel);
        partOfSpeechTagger = new POSTaggerME(partOfSpeechModel);


        sentenceDetector =
            new SentenceDetectorME(
                new SentenceModel(getInputStream("/models/en-sent.bin")));
    }

    private void initModels() throws IOException {
        InputStream sentenceModelStream = getInputStream("/models/en-sent.bin");
        InputStream tokenizereModelStream = getInputStream("/models/en-token.bin");
        InputStream nameFinderModelStream = getInputStream("/models/en-ner-person.bin");
        InputStream partOfSpeechModelStream = getInputStream("/models/en-pos-maxent.bin");

        sentenceModel = new SentenceModel(sentenceModelStream);
        tokenizerModel = new TokenizerModel(tokenizereModelStream);
        nameFinderModel = new TokenNameFinderModel(nameFinderModelStream);
        partOfSpeechModel = new POSModel(partOfSpeechModelStream);
    }

    private InputStream getInputStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }

    public String[] segmentSentences(String document) {
        return sentenceDetector.sentDetect(document);
    }

    public String[] tokenizeSentence(String sentence) {
        return tokenizer.tokenize(sentence);
    }

    public Span[] findNames(String[] tokens) {
        return nameFinder.find(tokens);
    }

    public String[] tagPartOfSpeech(String[] tokens) {
        return partOfSpeechTagger.tag(tokens);
    }

    public double[] getPartOfSpeechProbabilities() {
        return partOfSpeechTagger.probs();
    }
}