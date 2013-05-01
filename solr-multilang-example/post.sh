#!/bin/bash
set -e

source_dir=docs
solr_url="http://localhost:8983/solr"

if [ -z "$1" ]; then
    wiki=""
else
    wiki=$1
fi

find $source_dir -name "${wiki}*.xml" |\
while read f; do
    curl -s $solr_url/update/multilanguage --data-binary @$f -H 'Content-type:application/xml'
    curl -s $solr_url/update?commit=true # -F stream.body=' <commit />' -H 'Content-type:application/xml'
done
