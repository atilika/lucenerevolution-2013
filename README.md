Lucene Revolution 2013 - linguistics-demo
=========================================

Demo examples for linguistics in Lucene and Solr

The demo consists of the following modules.

- lucene-analyzer-example
- opennlp-example
- solr-multilang-example

Each example demo can be run as follows:

lucene-analyzer-example
-----------------------

The Lucene analyzer example consists of two demos,
AnalyzerExampleTest and FrenchSynonymExampleTest.

Run both demos with mvn test.

  $ cd lucene-analyzer-example

  $ mvn test

The demos can be run individually as well. For example:

  $ mvn -Dtest=AnalyzerExampleTest test


opennlp-example
---------------

The OpenNLP example consists of examples demonstrating 
sentence segmentation, tokenization, person name extraction 
as well as part-of-speech tagging.

Execute the following commands to run the examples.

  $ cd opennlp-example

  $ mvn -Dget-models test


solr-multilang-example
----------------------

