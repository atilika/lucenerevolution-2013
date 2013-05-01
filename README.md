Lucene Revolution 2013 - linguistics-demo
=========================================

Demo examples for linguistics in Lucene and Solr

The demo consists of the following modules:

- lucene-analyzer-example
- opennlp-example
- solr-multilang-example

Each example demo can be run as described below.

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

The Solr multilanguage example demonstrates how 

Download and unpack Solr (we are using 4.2.1 in this example)

  $ cd solr-multilang-example

  $ tar zxvf solr-4.2.1.tgz

Copy the demo schema.xml and solrconfig.xml to Solr's
example config as follows

  $ cp cp conf/schema.xml \
       conf/solrconfig.xml \
       solr-4.2.1/example/solr/collection1/conf/

Start up Solr

  $ cd solr-4.2.1/example

  $ java -jar start.jar

In a different directory, post the Wikipedia documents

  $ ./posh.sh

The below query gives an overview of the documents now
searchable from the various Wikipedia language editions

  $ curl 'http://localhost:8983/solr/collection1/select?q=*%3A*&rows=0&wt=xml&indent=true&facet=true&facet.field=wiki

The below query gives the distribution of languages detected

  $ curl 'http://localhost:8983/solr/collection1/select?q=*%3A*&rows=0&wt=xml&indent=true&facet=true&facet.field=languages

The below query gives the distribution of languages detected
in the Japanese Wikipedia

  $ curl 'http://localhost:8983/solr/collection1/select?q=wiki%3Ajawiki&rows=0&wt=xml&indent=true&facet=true&facet.field=language'


Contact us
----------

Contact us on hello@atilika.com if you have questions or problems.
